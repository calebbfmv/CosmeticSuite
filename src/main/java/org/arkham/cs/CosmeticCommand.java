package org.arkham.cs;

import org.arkham.cs.gui.Category;
import org.arkham.cs.gui.GUIManager;
import org.arkham.cs.gui.GUIPage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CosmeticCommand implements CommandExecutor {
	
	public CosmeticCommand(){
		CosmeticSuite cs = CosmeticSuite.getInstance();
		cs.getCommand("cosmetics").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		if (args.length == 0) {
			Inventory inv = CosmeticSuite.getInstance().getGuiManager().getMain();
			player.openInventory(inv);
			return true;
		}
		if (args.length == 1) {
			switch (args[0].toLowerCase()) {
			case "-e":
				openEffects(player);
				break;
			case "-h":
				openHats(player);
				break;
			case "-f":
				openFireworks(player);
				break;
			case "help":
				sendHelp(player);
				break;
			default:
				Inventory inv = CosmeticSuite.getInstance().getGuiManager().getMain();
				player.openInventory(inv);
				break;
			}
			return true;
		}
		return false;
	}

	public void sendHelp(Player player){
		String[] help = {
				ChatColor.GREEN + "==============================================",
				ChatColor.GOLD + "/cosmetics - " + ChatColor.GRAY + " Opens the main GUI.",
				ChatColor.GOLD + "Arguments: ",
				"  " + ChatColor.RED + "-h - " + ChatColor.GRAY + "Open the Hat's GUI",
				"  " + ChatColor.RED + "-e - " + ChatColor.GRAY + "Open the Effects's GUI",
				"  " + ChatColor.RED + "-f - " + ChatColor.GRAY + "Open the Fireworks's GUI",
				ChatColor.GREEN + "=============================================="
		};
		player.sendMessage(help);
	}

	public void openEffects(Player player) {
		GUIManager manager = CosmeticSuite.getInstance().getGuiManager();
		GUIPage page = manager.getPages(Category.EFFECTS);
		player.openInventory(page.getInv());
	}

	public void openHats(Player player) {
		GUIManager manager = CosmeticSuite.getInstance().getGuiManager();
		GUIPage page = manager.getPages(Category.HATS);
		player.openInventory(page.getInv());
	}

	public void openFireworks(Player player) {
		GUIManager manager = CosmeticSuite.getInstance().getGuiManager();
		GUIPage page = manager.getPages(Category.FIREWORKS);
		player.openInventory(page.getInv());
	}

}
