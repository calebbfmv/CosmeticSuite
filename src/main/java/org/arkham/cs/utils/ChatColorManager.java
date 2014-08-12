package org.arkham.cs.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.arkham.cs.db.SQLConnectionThread;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatColorManager {

	private static HashMap<UUID, ChatColor> colors = new HashMap<>();

	public ChatColorManager(){

	}

	public void sync(Player player){
		String uuid = "'" + player.getUniqueId() + "'";
		String resultSet = "SELECT `code` FROM `colors` WHERE `player`=" + uuid;
		ResultSet res = SQLConnectionThread.getResultSet(resultSet);
		try {
			if(res.next()){
				String colorCode = res.getString("code");
				ChatColor color = ChatColor.getByChar(colorCode);
				colors.put(player.getUniqueId(), color);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setColor(Player player, ChatColor color){
		colors.put(player.getUniqueId(), color);

	}

	public boolean hasColor(Player player){
		String uuid = "'" + player.getUniqueId() + "'";
		String resultSet = "SELECT `code` FROM `colors` WHERE `player`=" + uuid;
		ResultSet res = SQLConnectionThread.getResultSet(resultSet);
		try {
			return res.next();
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}

}
