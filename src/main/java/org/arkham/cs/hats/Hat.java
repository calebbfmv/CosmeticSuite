package org.arkham.cs.hats;

import java.util.ArrayList;
import java.util.List;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.gui.Category;
import org.arkham.cs.gui.GUIManager;
import org.arkham.cs.gui.GUIPage;
import org.arkham.cs.interfaces.Button;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Hat extends Button {

	private ItemStack item;
	private String permission;
	private List<String> lore;
	private static List<Hat> hats = new ArrayList<>();
	/**
	 * @param slot
	 * @param item
	 * @param name
	 * @param lore
	 */
	public Hat(int slot, ItemStack item, String name, String permission, List<String> lore) {
		super(slot, name);
		this.item = item;
		this.lore = lore;
		this.permission = permission;
		hats.add(this);
	}

	@Override
	public ItemStack getDisplay() {
		ItemMeta meta = item.getItemMeta();
		List<String> lore1 = new ArrayList<>();
		for(String s : lore){
			lore1.add(ChatColor.translateAlternateColorCodes('&', s));
		}
		meta.setDisplayName(getName());
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
		System.out.println("CLICKED");
		player.getInventory().setHelmet(getDisplay());
		player.closeInventory();
		System.out.println("ID: " + this.getId());
	}

	public static void populate(Player player){
		CosmeticSuite suite =  CosmeticSuite.getInstance();
		if(suite == null){
			return;
		}
		GUIManager manager = suite.getGuiManager();
		if(manager == null){
			return;
		}
		GUIPage page = manager.getPages(Category.HATS);
		if(page == null){
			return;
		}
		for(Hat hat : hats){
			if(!player.hasPermission(hat.getPermission())){
				page.getInv().setItem(hat.getSlot(), hat.noPermissionItem().getItem());
			} else {
				page.getInv().setItem(hat.getSlot(), hat.getDisplay());
			}
		}
	}
}