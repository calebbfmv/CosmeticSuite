package org.arkham.cs.gui;

import java.util.List;

import org.arkham.cs.CosmeticSuite;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BaseItems {
	
	public static ClickableItem hats(){
		return new ClickableItem(ItemFactory.create(Material.BUCKET, ChatColor.AQUA + "Hats", ChatColor.GREEN + "Click to open the hat GUI")) {
			@Override
			public void doClick(Player player) {
				List<GUIPage> pages = CosmeticSuite.getInstance().getGuiManager().getPages(Category.HATS);
				GUIPage page = pages.get(0);
				player.openInventory(page.getInv());
			}
		};
	}
	
	public static ItemStack effects(){
		return ItemFactory.create(Material.BUCKET, ChatColor.MAGIC + "<>" + ChatColor.RESET + ChatColor.GOLD + "Effects", ChatColor.GREEN + "Click to open the effects GUI");
	}
	
	public static ItemStack back(){
		return ItemFactory.create(Material.REDSTONE, ChatColor.RED + ChatColor.BOLD.toString() + "Go Back");
	}
	
	public static ItemStack next(){
		return ItemFactory.create(Material.GOLD_INGOT, ChatColor.GREEN + ChatColor.BOLD.toString() + "Proceed");
	}
	
	public static ItemStack fireworks(){
		return ItemFactory.create(Material.BUCKET, ChatColor.RED + "Fi" + ChatColor.YELLOW + "re" + ChatColor.BLUE + "works", ChatColor.GREEN + "Click to open the firework GUI");
	}

}
