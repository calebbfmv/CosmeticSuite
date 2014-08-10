package org.arkham.cs;

import java.util.List;

import org.arkham.cs.gui.Category;
import org.arkham.cs.gui.GUIManager;
import org.arkham.cs.gui.GUIPage;
import org.arkham.cs.handler.PurchaseHandler;
import org.arkham.cs.hats.Hat;
import org.arkham.cs.utils.Rank;
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
				"  " + ChatColor.RED + "-h | " + ChatColor.GRAY + "Open the Hat's GUI",
				"  " + ChatColor.RED + "-e | " + ChatColor.GRAY + "Open the Effects's GUI",
				"  " + ChatColor.RED + "-f | " + ChatColor.GRAY + "Open the Fireworks's GUI",
				ChatColor.GREEN + "=============================================="
		};
		player.sendMessage(help);
	}

	public void openEffects(Player player) {
		GUIManager manager = CosmeticSuite.getInstance().getGuiManager();
		List<GUIPage> pages = manager.getPages(Category.EFFECTS);
		GUIPage page = pages.get(0);
		if(page == null){
			return;
		}
		player.openInventory(page.getInv());
	}

	public void openHats(Player player) {
		GUIManager manager = CosmeticSuite.getInstance().getGuiManager();
		List<GUIPage> pages = manager.getPages(Category.HATS);
		GUIPage page = pages.get(0);
		if(page == null){
			return;
		}
		player.openInventory(page.getInv());
	}

	public void openFireworks(Player player) {
		GUIManager manager = CosmeticSuite.getInstance().getGuiManager();
		List<GUIPage> pages = manager.getPages(Category.FIREWORKS);
		GUIPage page = pages.get(0);
		if(page == null){
			return;
		}
		player.openInventory(page.getInv());
	}

	public void openHatsFor(Rank rank, Player player){
		PurchaseHandler.setUpPurchases(player);
		for(Hat hat : Hat.getHats(rank)){
			if(!PurchaseHandler.hasPurchased(player, hat)){
				PurchaseHandler.addPurchase(player, hat);
			}
			GUIPage.addButton(hat, Category.HATS, player);
		}
		openHats(player);
	}

}
