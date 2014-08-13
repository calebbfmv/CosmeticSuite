package org.arkham.cs.cosmetics;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.DefaultFlag;

public class Portal {

	private Location loc;
	private Player player;
	private static ArrayList<Location> portals = new ArrayList<>();
	private static HashMap<UUID, Entry<Portal, Portal>> madePortals = new HashMap<>();

	public Portal(Location loc, Player player) {
		this.loc = loc;
		loc = loc.clone().add(0, 1, 0);
		this.player = player;
		portals.add(loc);
		Entry<Portal, Portal> ps = madePortals.get(player.getUniqueId());
		if(ps == null){
			ps = new SimpleEntry<>(this, null);
			madePortals.put(player.getUniqueId(), ps);
			return;
		}
		Portal portal1 = madePortals.get(player.getUniqueId()).getKey();
		if(portal1 == null){
			ps.setValue(this);
			madePortals.put(player.getUniqueId(), ps);
			return;
		}
		Portal portal2 = madePortals.get(player.getUniqueId()).getValue();
		if(portal2 != null){
			portal1.getLocation().getBlock().setType(Material.AIR);
			Location p1n = portal1.getLocation().clone().add(0, 1, 0);
			p1n.getBlock().setType(Material.AIR);
			portals.remove(p1n);
			portals.remove(loc);
			portal2.getLocation().getBlock().setType(Material.AIR);
			Location p2n = portal2.getLocation().clone().add(0, 1, 0);
			p2n.getBlock().setType(Material.AIR);
			madePortals.remove(player.getUniqueId());
			portals.remove(loc);
			portals.remove(p2n);	
		}
	}

	public Portal(Player player) {
		this(player.getLocation(), player);
	}

	public Player getPlayer() {
		return player;
	}

	public Location getLocation() {
		return loc;
	}

	public boolean checkPerms(Player player) {
		WorldGuardPlugin api = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
		ApplicableRegionSet regions = api.getRegionManager(loc.getWorld()).getApplicableRegions(loc);
		LocalPlayer lPlayer = api.wrapPlayer(player);
		if (regions.size() == 0) {
			return true;
		}
		return (regions.allows(DefaultFlag.BUILD, lPlayer) ? true : false);
	}

	public void spark() {
		if (!checkPerms(player)) {
			return;
		}
		Material portal = Material.PORTAL;
		Location above = loc.clone().add(0, 1, 0);
		above.getBlock().setType(portal);
		portals.add(above);
		loc.getBlock().setType(portal);
	}

}
