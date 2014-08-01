package org.arkham.cs.hats;

import java.util.ArrayList;
import java.util.List;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.gui.Category;
import org.arkham.cs.gui.GUIManager;
import org.arkham.cs.gui.GUIPage;
import org.arkham.cs.interfaces.GUIButton;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Hat extends GUIButton {
	
	private ItemStack item;
	private String name, permission;
	private List<String> lore;
	private static List<Hat> hats = new ArrayList<>();
	/**
	 * @param slot
	 * @param item
	 * @param name
	 * @param lore
	 */
	public Hat(int slot, ItemStack item, String name, String permission, List<String> lore) {
		super(slot);
		this.item = item;
		this.name = name;
		this.lore = lore;
		hats.add(this);
	}

	@Override
	public ItemStack getDisplay() {
		ItemMeta meta = item.getItemMeta();
		name = ChatColor.translateAlternateColorCodes('&', name);
		List<String> lore1 = new ArrayList<>();
		for(String s : lore){
			lore1.add(ChatColor.translateAlternateColorCodes('&', s));
		}
		meta.setDisplayName(name);
		meta.setLore(lore1);
		item.setItemMeta(meta);
		return item;
	}

	@Override
	public String getPermission() {
		return permission;
	}


	@Override
	public void onClick(Player player) {
		System.out.println("Clickedddd");
		player.getInventory().setHelmet(getDisplay());
		player.closeInventory();
	}
	
	public static void populate(){
		CosmeticSuite suite =  CosmeticSuite.getInstance();
		if(suite == null){
			System.out.println("null instance");
			return;
		}
		GUIManager manager = suite.getGuiManager();
		if(manager == null){
			System.out.println("null managaer");
			return;
		}
		GUIPage page = manager.getPages(Category.HATS);
		if(page == null){
			System.out.println("null page");
			return;
		}
		for(Hat hat : hats){
			page.getInv().addItem(hat.getDisplay());
		}
	}
}