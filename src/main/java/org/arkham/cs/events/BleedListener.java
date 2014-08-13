package org.arkham.cs.events;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.handler.PlayerHandler;
import org.arkham.cs.utils.Rank;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class BleedListener implements Listener {
	
	public BleedListener(CosmeticSuite cs){
		cs.getServer().getPluginManager().registerEvents(this, cs);
	}

	@EventHandler
	public void damage(EntityDamageByEntityEvent event) {
		if(!(event.getEntity() instanceof Player)){
			return;
		}
		if(!(event.getDamager() instanceof Player)){
			return;
		}
		Player hit = (Player) event.getEntity();
		Player damager = (Player) event.getDamager();
		if(PlayerHandler.getRank(damager) != Rank.SUPERHERO){
			return;
		}
		play(hit.getLocation(), 5, 0.5, Material.REDSTONE, (byte) 0);
	}

	public void play(final Location loc, final int particles, final double velMult, final Material type, final byte data) {
		for (int i = 0; i < particles; ++i) {
			final Item item = loc.getWorld().dropItem(loc, new ItemStack(type, 1, data));
			item.setVelocity(new Vector((Math.random() - 0.5) * velMult, Math.random() * velMult, (Math.random() - 0.5) * velMult));
		}
	}

}
