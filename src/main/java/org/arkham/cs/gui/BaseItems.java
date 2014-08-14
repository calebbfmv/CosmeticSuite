package org.arkham.cs.gui;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.utils.PlayerMetaDataUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class BaseItems {

	public static ClickableItem hats(){
		return new ClickableItem(ItemFactory.create(Material.BUCKET, ChatColor.YELLOW + ChatColor.BOLD.toString() + "Hats", ChatColor.AQUA + "Click to open the hat GUI")) {
			@Override
			public void doClick(Player player) {
				PlayerMetaDataUtil.setSwitchPage(player);
				CosmeticSuite.getInstance().getCommand().openHats(player);
			}
		};
	}

	public static ClickableItem effects(){
		return new ClickableItem(ItemFactory.create(Material.PORTAL,  ChatColor.YELLOW + ChatColor.BOLD.toString() + "Effects", 1, (byte) 90, ChatColor.AQUA + "Click to open the effects GUI")) {
			@Override
			public void doClick(Player player) {
				PlayerMetaDataUtil.setSwitchPage(player);
				CosmeticSuite.getInstance().getCommand().openEffects(player);
			}
		};
	}

	public static ClickableItem back(){
		return new ClickableItem(ItemFactory.create(Material.BLAZE_ROD, ChatColor.YELLOW + ChatColor.BOLD.toString() + "Go Back")){
			@Override
			public void doClick(Player player) {
				GUIPage cpage = GUIPage.getCurrent(player);
				GUIPage page = cpage.prev();
				if(page == null){
					player.openInventory(CosmeticSuite.getInstance().getGuiManager().getMain());
					return;
				}
				if(cpage.getCategory() != page.getCategory()){
					player.openInventory(CosmeticSuite.getInstance().getGuiManager().getMain());
					return;
				}
				PlayerMetaDataUtil.setSwitchPage(player);
				player.openInventory(page.getInv());
			}
		};
	}

	public static ClickableItem next(){
		return new ClickableItem(ItemFactory.create(Material.ARROW, ChatColor.YELLOW + ChatColor.BOLD.toString() + "Next Page")){
			@Override
			public void doClick(Player player) {
				GUIPage cpage = GUIPage.getCurrent(player);
				GUIPage page = cpage.next();
				if(page == null){
					player.openInventory(CosmeticSuite.getInstance().getGuiManager().getMain());
					return;
				}
				if(cpage.getCategory() != page.getCategory()){
					player.openInventory(CosmeticSuite.getInstance().getGuiManager().getMain());
					return;
				}
				PlayerMetaDataUtil.setSwitchPage(player);
				player.openInventory(page.getInv());
			}
		};
	}

	public static ClickableItem blocks(){
		return new ClickableItem(ItemFactory.create(Material.SEEDS, ChatColor.YELLOW + ChatColor.BOLD.toString() + "Block Trails", ChatColor.AQUA + "Click to open the Blocks Trails GUI")) {
			@Override
			public void doClick(Player player) {
				PlayerMetaDataUtil.setSwitchPage(player);
				CosmeticSuite.getInstance().getCommand().openWalkingBlocks(player);
			}
		};
	}
	
	public static ClickableItem kits(){
		return new ClickableItem(ItemFactory.create(Material.DIAMOND_CHESTPLATE, ChatColor.YELLOW + ChatColor.BOLD.toString() + "Kits", ChatColor.AQUA + "Click to open the Kits GUI")) {
			@Override
			public void doClick(Player player) {
				PlayerMetaDataUtil.setSwitchPage(player);
				CosmeticSuite.getInstance().getCommand().openKits(player);
			}
		};
	}
}
