package org.arkham.cs.gui;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.utils.Rank;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class BaseItems {

	public static ClickableItem hats(){
		return new ClickableItem(ItemFactory.create(Material.BUCKET, ChatColor.AQUA + "Hats", ChatColor.GREEN + "Click to open the hat GUI")) {
			@Override
			public void doClick(Player player) {
				CosmeticSuite.getInstance().getCommand().openHatsFor(Rank.HERO, player);
			}
		};
	}

	public static ClickableItem effects(){
		return new ClickableItem(ItemFactory.create(Material.FEATHER, ChatColor.MAGIC + "<>" + ChatColor.RESET + ChatColor.GOLD + "Effects", ChatColor.GREEN + "Click to open the effects GUI")) {
			@Override
			public void doClick(Player player) {
				CosmeticSuite.getInstance().getCommand().openEffects(player);
			}
		};
	}

	public static ClickableItem back(){
		return new ClickableItem(ItemFactory.create(Material.REDSTONE, ChatColor.RED + ChatColor.BOLD.toString() + "Go Back")){
			@Override
			public void doClick(Player player) {
				GUIPage page = GUIPage.getCurrent(player).prev();
				if(page == null){
					player.closeInventory();
					return;
				}
				player.openInventory(page.getInv());
			}
		};
	}

	public static ClickableItem next(){
		return new ClickableItem(ItemFactory.create(Material.GOLD_INGOT, ChatColor.GREEN + ChatColor.BOLD.toString() + "Proceed")){
			@Override
			public void doClick(Player player) {
				if(GUIPage.getCurrent(player) == null){
					return;
				}
				GUIPage page = GUIPage.getCurrent(player).next();
				if(page == null){
					player.closeInventory();
					return;
				}
				player.openInventory(page.getInv());
			}
		};
	}

	public static ClickableItem fireworks(){
		return new ClickableItem(ItemFactory.create(Material.FIREWORK, ChatColor.RED + "Fi" + ChatColor.YELLOW + "re" + ChatColor.BLUE + "works", ChatColor.GREEN + "Click to open the firework GUI")) {
			@Override
			public void doClick(Player player) {
				CosmeticSuite.getInstance().getCommand().openFireworks(player);
			}
		};
	}
}
