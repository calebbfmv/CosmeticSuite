package org.arkham.cs.handler;

import org.arkham.cs.interfaces.GUIButton;
import org.bukkit.entity.Player;

public class PlayerHandler {
	
	private Player player;
	
	public PlayerHandler(Player player){
		this.player = player;
	}
	
	public boolean canPurchase(GUIButton button){
		if(player.hasPermission(button.getPermission())){
			return true;
		}
		if(PurchaseHandler.hasPurchased(player, button)){
			return true;
		}
		return false;
	}
	
	public void purchase(GUIButton button){
		
		PurchaseHandler.addPurchase(player, button);
	}

}
