package org.arkham.cs.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.cosmetics.GlobalKit;
import org.arkham.cs.cosmetics.HeroKit;
import org.arkham.cs.db.SQLConnectionThread;
import org.arkham.cs.db.SQLQueryThread;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class KitManager {

	public static void giveKit(Player player, GlobalKit kit) {
		if (!canUse(player, kit)) {
			return;
		}
		kit.giveItems(player);
		long current = System.currentTimeMillis();
		int id = (kit instanceof HeroKit) ? 1 : 2;
		SQLQueryThread.addQuery("INSERT INTO `globalkits` VALUES('" + player.getUniqueId().toString() + "', " + id + ", " + current + ")");
	}

	public static boolean canUse(Player player, GlobalKit kit) {
		int id = (kit instanceof HeroKit) ? 1 : 2;
		String uuid = "'" + player.getUniqueId() + "'";
		String query = "SELECT `time` FROM `globalkits` WHERE `player`=" + uuid + " AND `id`=" + id;
		ResultSet res = SQLConnectionThread.getResultSet(query);
		try {
			if (res.next()) {
				String msg = getTimeRemaining(player, kit);
				if(msg.equalsIgnoreCase("hecanuse")){
					SQLQueryThread.addQuery("DELETE * FROM `globalkits` WHERE `player` =" + uuid + " AND `id`=" + id);
					return true;
				}
				player.sendMessage(msg);
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static String getTimeRemaining(Player player, GlobalKit kit) {
		int id = (kit instanceof HeroKit) ? 1 : 2;
		long time = -69;
		StringBuilder builder = new StringBuilder();
		String uuid = "'" + player.getUniqueId() + "'";
		String query = "SELECT `time` FROM `globalkits` WHERE `player`=" + uuid + " AND `id`=" + id;
		ResultSet res = SQLConnectionThread.getResultSet(query);
		try {
			if (res.next()) {
				time = res.getInt("time");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		long minutes = 0;
		long seconds = 0;
		long hours = 0;
		time = System.currentTimeMillis() - time;
		if(time <= 0){
			builder.append("HeCanUse");
			return builder.toString();
		}
		builder.append(CosmeticSuite.PREFIX + "Please wait another ");
		seconds = time / 1000;
		minutes = time / 1000 / 60;
		hours = time / 1000 / 60 / 60;
		if(hours > 0){
			builder.append(hours + "h, " );
		}
		if(minutes > 0){
			builder.append(minutes + "m, ");
		}
		if(seconds > 0){
			builder.append(minutes + "s");
		}
		builder.append(ChatColor.YELLOW + " before using this kit again");
		return builder.toString();
	}
}