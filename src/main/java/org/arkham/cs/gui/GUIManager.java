package org.arkham.cs.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class GUIManager {

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


}
