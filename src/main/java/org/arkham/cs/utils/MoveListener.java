package org.arkham.cs.utils;

import org.arkham.cs.CosmeticSuite;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener {
	
	public MoveListener(){
		CosmeticSuite cs = CosmeticSuite.getInstance();
		cs.getServer().getPluginManager().registerEvents(this, cs);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent event){
		if(event.getTo().getBlockX() == event.getFrom().getBlockX() && event.getTo().getBlockZ() == event.getFrom().getBlockZ()){
			return;
		}
		Player player = event.getPlayer();
		Location loc = player.getLocation();
		CurseBlock cb = CurseBlock.get(player);
		if(cb == null){
			return;
		}
		Block below = loc.getBlock().getRelative(BlockFace.DOWN);
		below.setType(cb.getDisplay().getType());
		below.setData(cb.getDisplay().getData().getData());
	}

}
