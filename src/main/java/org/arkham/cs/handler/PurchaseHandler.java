package org.arkham.cs.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.arkham.cs.db.SQLConnectionThread;
import org.arkham.cs.db.SQLQueryThread;
import org.arkham.cs.interfaces.Button;
import org.bukkit.entity.Player;

public class PurchaseHandler {
	
	private static HashMap<UUID, List<Button>> purchases = new HashMap<>();
	
	public static void addPurchase(Player player, Button button){
		String name = "'" + player.getUniqueId().toString() + "'";
		int id = button.getId();
		String query = "INSERT INTO `purchases`(`buttons`) VALUES (" + id + ") WHERE `player`=" + name;
		SQLQueryThread.addQuery(query);
	}
	
	public static boolean hasPurchased(Player player, Button button){
		UUID uuid = player.getUniqueId();
		if(purchases.get(uuid) == null){
			List<Button> buttons = new ArrayList<>();
			String query = "SELECT * FROM `purchases WHERE `player` ='" + uuid + "'";
			ResultSet res = SQLConnectionThread.getResultSet(query);
			try {
				while(res.next()){
					int id = res.getInt("buttons");
					Button nButton = Button.fromId(id);
					if(nButton == null){
						throw new IllegalArgumentException("Tried accessing a null button from the ID in MySQL : WTF");
					}
					buttons.add(nButton);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			purchases.put(uuid, buttons);
		}
		return purchases.get(uuid).contains(button);
	}

}
