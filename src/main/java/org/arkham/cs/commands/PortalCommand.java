package org.arkham.cs.commands;

import org.arkham.cs.CosmeticSuite;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PortalCommand implements CommandExecutor {
	
	public PortalCommand(CosmeticSuite cs){
		cs.getCommand("portal").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		return false;
	}

}
