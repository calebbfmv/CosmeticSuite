package org.arkham.cs.utils;

import org.arkham.cs.CosmeticSuite;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class FlyListener implements Listener  {
	
	public FlyListener(){
		CosmeticSuite cs = CosmeticSuite.getInstance();
		cs.getServer().getPluginManager().registerEvents(this, cs);
	}
	
	@EventHandler
	public void onFly(PlayerToggleFlightEvent event){
		Player player = event.getPlayer();
		if(player.isOp()){
			return;
		}
		if(player.hasPermission("cosmetics.*")){
			return;
		}
		if(player.hasMetadata("cosmetics-fly")){
			return;
		}
		event.setCancelled(true);
	}

}
