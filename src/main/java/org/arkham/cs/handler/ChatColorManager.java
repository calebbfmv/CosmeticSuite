package org.arkham.cs.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.arkham.cs.db.SQLConnectionThread;
import org.arkham.cs.db.SQLQueryThread;
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
				res.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setColor(Player player, ChatColor color){
		colors.put(player.getUniqueId(), color);
		SQLQueryThread.addQuery("INSERT INTO `colors` VALUES('" + player.getUniqueId().toString() + "', '" + color.getChar() + "') ON DUPLICATE KEY UPDATE `code`='" + color.getChar() + "'");
	}

	public boolean hasColor(Player player){
		if(colors.get(player.getUniqueId()) != null){
			return true;
		}
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
	
	public ChatColor getColor(Player player){
		if(!hasColor(player)){
			sync(player);
		}
		return colors.get(player.getUniqueId());
	}

}
