package org.arkham.cs;

import org.arkham.cs.utils.ChatColorManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ColorCommand implements CommandExecutor {

	public ColorCommand(CosmeticSuite cs){
		cs.getCommand("color").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)){
			return false;
		}
		Player player = (Player) sender;
		String prefix = CosmeticSuite.PREFIX;
		ChatColorManager manager = CosmeticSuite.getInstance().getChatColorManager();
		if(args.length != 2){
			player.sendMessage(prefix + "Your current color is " + (manager.getColor(player) == null ? "none" : "&" + manager.getColor(player) + " and it looks like this!"));
			player.sendMessage(prefix + "Please do /color set &<colorcode>");
			return true;
		}
		if(!args[0].equalsIgnoreCase("set")){
			player.sendMessage(prefix + "Your current color is " + (manager.getColor(player) == null ? "none" : "&" + manager.getColor(player) + " and it looks like this!"));
			player.sendMessage(prefix + "Please do /color set &<colorcode>");
			return true;
		}
		String cha_r = args[1].split("&")[0];
		if(ChatColor.getByChar(cha_r) == null){
			player.sendMessage(prefix + "That color is not known!");
			return true;
		}
		manager.setColor(player, ChatColor.getByChar(cha_r));
		player.sendMessage(prefix + "Your new chat color is now &" + cha_r);
		return false;
	}

}
