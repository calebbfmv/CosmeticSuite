package org.arkham.cs.handler;

import org.arkham.cs.interfaces.Button;
import org.bukkit.entity.Player;

public class PlayerHandler {
	
	public static boolean canPurchase(Button button, Player player){
		if(player.hasPermission(button.getPermission())){
			return true;
		}
		if(PurchaseHandler.hasPurchased(player, button)){
			return true;
		}
		return false;
	}
	
	public static void purchase(Button button, Player player){
		PurchaseHandler.addPurchase(player, button);
	}

}
