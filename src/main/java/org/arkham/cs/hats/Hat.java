package org.arkham.cs.hats;

import java.util.ArrayList;
import java.util.List;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.gui.Category;
import org.arkham.cs.gui.GUIManager;
import org.arkham.cs.gui.GUIPage;
import org.arkham.cs.interfaces.Button;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Hat extends Button {

	private ItemStack item;
	private String permission;
	private static List<Hat> hats = new ArrayList<>();

	/**
	 * @param slot
	 * @param item
	 * @param name
	 * @param lore
	 */
	public Hat(int slot, ItemStack item, Category name) {
		super(slot, name);
		this.item = item;
		this.permission = "cosmetics.hats." + item.getType().name().toLowerCase();
		hats.add(this);
	}

	@Override
	public ItemStack getDisplay() {
		return item;
	}

	@Override
	public String getPermission() {
		return permission;
	}


	@Override
	public void onClick(Player player) {
		player.getInventory().setHelmet(getDisplay());
		player.closeInventory();
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
		List<GUIPage> pages = manager.getPages(Category.HATS);
		GUIPage page = pages.get(0);
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