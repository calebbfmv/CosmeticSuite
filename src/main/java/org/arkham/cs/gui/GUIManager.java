package org.arkham.cs.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.arkham.cs.interfaces.GUIButton;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUIManager implements Listener {

	private Inventory main;
	private HashMap<Category, List<GUIPage>> pages = new HashMap<>();

	public GUIManager(){
		main = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Arkham Cosmetics");
		main.setItem(2, BaseItems.hats().getItem());
		main.setItem(3, BaseItems.fireworks());
		main.setItem(4, BaseItems.effects());
		loadPages();
	}

	private void loadPages(){
		loadPagesFromYML();
		for(Category cat :  Category.values()){
			List<GUIPage> guiPages = new ArrayList<>();
			for(GUIPage page :  GUIPage.getPages().values()){
				if(page.getCategory() == cat){
					guiPages.add(page);
				}
			}
			pages.put(cat, guiPages);
		}
	}

	private void loadPagesFromYML(){

	}
	
	public List<GUIPage> getPages(Category cat){
		return pages.get(cat);
	}

	@EventHandler
	public void onClick(InventoryClickEvent event){
		if(event.getInventory() == null){
			return;
		}
		if(event.getCurrentItem() == null){
			GUIButton button = GUIButton.fromSlot(event.getRawSlot());
			if(button == null){
				return;
			}
			button.onClick((Player) event.getWhoClicked());
			return;
		}
		ItemStack item = event.getCurrentItem();
		if(ClickableItem.fromItem(item) == null){
			return;
		}
		ClickableItem cItem = ClickableItem.fromItem(item);
		cItem.doClick((Player) event.getWhoClicked());
	}

}
