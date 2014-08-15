package org.arkham.cs.commands;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.cosmetics.Portal;
import org.arkham.cs.cosmetics.PortalLink;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class PortalCommand implements CommandExecutor {

	Portal p1 = null, p2 = null;
	
	public PortalCommand(CosmeticSuite cs){
		cs.getCommand("portal").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length != 1){
			sender.sendMessage(help());
			return true;
		}
		if(!args[0].equalsIgnoreCase("create")){
			sender.sendMessage(help());
			return true;
		}
		Player player = (Player) sender;
		if(player.hasMetadata("created2")){
			if(p1 == null){
				sender.sendMessage(help());
				return true;
			}
			if(p2 == null){
				sender.sendMessage(help());
				return true;
			}
			new PortalLink(p1, p2, player);
			player.sendMessage(CosmeticSuite.PREFIX + "Linked the portals!");
			player.removeMetadata("created", CosmeticSuite.getInstance());
			player.removeMetadata("created2", CosmeticSuite.getInstance());
			return true;
		}
		if(!player.hasMetadata("created")){
			//first creation
			p1 = new Portal(player);
			player.sendMessage(CosmeticSuite.PREFIX + "Set the first location!");
			player.setMetadata("created", new FixedMetadataValue(CosmeticSuite.getInstance(), ""));
		} else {
			//first creation
			p2 = new Portal(player);
			player.sendMessage(CosmeticSuite.PREFIX + "Set the second location!");
			player.removeMetadata("created", CosmeticSuite.getInstance());
			player.setMetadata("created2", new FixedMetadataValue(CosmeticSuite.getInstance(), ""));
		} 
		//Second creation
		return false;
	}


	public String[] help(){
		String prefix = CosmeticSuite.PREFIX;
		String[] help = { ChatColor.YELLOW + "====================" + prefix + "==========================", prefix + "/portal - Shows the help menu.",
				"  " + ChatColor.RED + "/portal create [First Execution] | Set the first location of your portal!",
				"  " + ChatColor.RED + "/portal create [Second Execution] | Set the second location of your portal, and 2 portals will appear",
				"  " + ChatColor.RED + "/portal create [Third Execution] | Light the portals.",
				ChatColor.YELLOW + "====================" + prefix + "==========================", };
		return help;
	}

}
