package org.arkham.cs.utils;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.handler.PlayerHandler;
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
		if(player.isFlying()){
			TrailingBlock tb = TrailingBlock.get(player);
			if(tb == null){
				return;
			}
			if(!tb.isInUse()){
				return;
			}
			tb.setInUse(false);
			return;
		}
		if(player.isOp()){
			return;
		}
		if(player.hasPermission("cosmetics.*")){
			return;
		}
		boolean serverIsInFly = CosmeticSuite.getInstance().getConfig().getBoolean("flyable", false);
		if(serverIsInFly){
			if(PlayerHandler.isHero(player) || PlayerHandler.isSuperHero(player)){
				return;
			}
			if(PlayerHandler.isNothingSpecial(player)){
				event.setCancelled(true);
			}
			return;
		}
		if(player.hasMetadata("cosmetics-fly")){
			return;
		}
		event.setCancelled(true);
	}

}
