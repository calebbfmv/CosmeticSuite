package org.arkham.cs.utils;

import java.util.ArrayList;

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

	public Portal(Location loc, Player player){
		this.loc = loc;
		this.player = player;
		portals.add(loc);
	}

	public Portal(Player player){
		this(player.getLocation(), player);
	}

	public Player getPlayer(){
		return player;
	}

	public Location getLocation(){
		return loc;
	}

	public boolean checkPerms(Player player) {
		WorldGuardPlugin api = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
		ApplicableRegionSet regions = api.getRegionManager(loc.getWorld()).getApplicableRegions(loc);
		LocalPlayer lPlayer = api.wrapPlayer(player);
		if (regions.size() == 0){
			return true;
		}
		return (regions.allows(DefaultFlag.BUILD, lPlayer) ? true : false);
	}

	public void spark(){
		if(!checkPerms(player)){
			return;
		}
		Material portal = Material.PORTAL;
		loc = loc.clone().add(0, 1, 0);
		Location above = loc.clone().add(0,1,0);
		above.getBlock().setType(portal);
		portals.add(above);
		loc.getBlock().setType(portal);
	}

}
