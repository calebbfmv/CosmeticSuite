package org.arkham.cs.gui;

import java.util.Collections;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;

public class GUIPage {
	
	private int id;
	private String title;
	private Inventory inv;
	private Category cat;
	
	private static HashMap<Integer, GUIPage> pages = new HashMap<>();
	
	public GUIPage(String title, Category cat){
		this.title = title;
		if(!pages.isEmpty()){
			this.id = Collections.max(pages.keySet()) + 1;
		}  else {
			this.id = 1;
		}
		pages.put(id, this);
		title = ChatColor.translateAlternateColorCodes('&', title);
		title += ": " + id;
		this.inv = Bukkit.createInventory(null, 45, title);
		this.cat = cat;
	}
	
	public Category getCategory(){
		return cat;
	}
	
	public GUIPage prev(){
		return pages.get(id -1);
	}
	
	public GUIPage next(){
		return pages.get(id + 1);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Inventory getInv() {
		return inv;
	}

	public static HashMap<Integer, GUIPage> getPages() {
		return pages;
	}

	public static GUIPage first(){
		return pages.get(1);
	}
}
