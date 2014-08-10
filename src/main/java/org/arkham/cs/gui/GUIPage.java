package org.arkham.cs.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.handler.PurchaseHandler;
import org.arkham.cs.interfaces.Button;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUIPage {

	private int id;
	private String title;
	private Inventory inv;
	private Category cat;

	private static HashMap<Integer, GUIPage> pages = new HashMap<>();
	private static List<Button> addedButtons = new ArrayList<>();

	public GUIPage(String title, Category cat) {
		this.title = title;
		if (!pages.isEmpty()) {
			this.id = Collections.max(pages.keySet()) + 1;
		} else {
			this.id = 1;
		}
		pages.put(id, this);
		title = ChatColor.translateAlternateColorCodes('&', title);
		this.inv = Bukkit.createInventory(null, 45, title);
		this.cat = cat;
		inv.setItem(0, BaseItems.back().getItem());
		inv.setItem(44, BaseItems.next().getItem());
	}

	public int firstEmptySlot(boolean space) {
		int first = 0;
		for (int i = 0; i < 45; i++) {
			if (inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR) {
				if(!space){
					return inv.firstEmpty();
				}
				if((i-1) <= -1){
					return inv.firstEmpty();
				}
				if((i + 1) > 44){
					return inv.firstEmpty();
				}
				ItemStack b4 = inv.getItem(i - 1);
				ItemStack after = inv.getItem(i + 1);
				if(b4 == null && after == null){
					return i;
				}
			}
		}
		return first;
	}

	public Category getCategory() {
		return cat;
	}

	public GUIPage prev() {
		return pages.get(id - 1);
	}

	public GUIPage next() {
		return pages.get(id + 1);
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Inventory getInv() {
		return inv;
	}

	public static HashMap<Integer, GUIPage> getPages() {
		return pages;
	}

	public static GUIPage first() {
		return pages.get(1);
	}

	public static GUIPage getCurrent(Player player) {
		if (player.getOpenInventory() == null) {
			return null;
		}
		String title = player.getOpenInventory().getTitle();
		for (GUIPage page : pages.values()) {
			if (page.getTitle().equalsIgnoreCase(title)) {
				return page;
			}
		}
		return null;
	}

	public static void addButton(Button button, Category cat, Player player){
		List<GUIPage> pages = CosmeticSuite.getInstance().getGuiManager().getPages(cat);
		if(addedButtons.contains(button)){
			return;
		}
		for(GUIPage page : pages){
			if(page.getInv().getItem(43) != null && page.getInv().getItem(43).getType() != Material.AIR){
				continue;
			}
			int firstEmtpy = page.getInv().firstEmpty();
			if(button.getSlot() != firstEmtpy){
				button.setSlot(firstEmtpy);
			}
			ItemStack display = !PurchaseHandler.hasPurchased(player, button) ? button.noPermissionItem().getItem() : button.getDisplay();
			page.getInv().setItem(firstEmtpy, display);
			addedButtons.add(button);
			break;
		}
	}
}
