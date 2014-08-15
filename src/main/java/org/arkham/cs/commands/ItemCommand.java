package org.arkham.cs.commands;

import org.arkham.cs.CosmeticSuite;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ItemCommand implements CommandExecutor {
	
	public ItemCommand(CosmeticSuite cs){
		cs.getCommand("itemedit").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0){
			return true;
		}
		return false;
	}
	
	//Select = Green
	//Gray = Available
	//Red = Locked
	
	public String[] help(){
		String prefix = CosmeticSuite.PREFIX;
		String[] help = {
				ChatColor.YELLOW + "====================" + prefix + "==========================",
				prefix + "/itemedit - Shows the help menu.",
				prefix + "Arguments: ",
				"  " + ChatColor.RED + "name <ItemName> | Name the item in your hand! " + ChatColor.GRAY + "(Supports ChatColors, Max length: 64)",
				"  " + ChatColor.RED + "color <ItemColor> | Recolor the item in your hand! " + ChatColor.GRAY + "(Only works with Leather Armor)",
				ChatColor.YELLOW + "====================" + prefix + "==========================",
		};
		return help;
	}
	
	

}
