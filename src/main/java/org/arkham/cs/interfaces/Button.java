package org.arkham.cs.interfaces;

import java.util.HashMap;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.gui.ClickableItem;
import org.arkham.cs.gui.ItemFactory;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Button {
		
	public abstract ItemStack getDisplay();
	
	public abstract String getPermission();
	
	
	public abstract void onClick(Player player);
		
	private int slot, id;
	private String name;
	private static HashMap<String, Button> buttons = new HashMap<>();
	private static HashMap<Integer, Button> buttonIds = new HashMap<>();
	
	public Button(int slot, String name){
		this.slot = slot;		
		if(!buttons.isEmpty()){
			id = buttons.size() + 1;
		} else {
			id = 1;
		}
		this.name = name;
		name = ChatColor.translateAlternateColorCodes('&', name);
		buttons.put(getName(), this);
		buttonIds.put(id, this);
	}
	
	public ClickableItem noPermissionItem(){
		ItemStack item = ItemFactory.create(Material.STAINED_GLASS_PANE, ChatColor.RED + getDisplay().getItemMeta().getDisplayName(), 1, (byte) 14, ChatColor.RED + "You do not own this item");
		return new ClickableItem(item) {
			@Override
			public void doClick(Player player) {		
				CosmeticSuite cs = CosmeticSuite.getInstance();
				FileConfiguration config = cs.getConfig();

				String link = config.getString("buy-link", "https://buy.arkhamnetwork.org");
				link = ChatColor.translateAlternateColorCodes('&', link);
				player.sendMessage(link);
				player.closeInventory();
			}
		};
	}
	
	public int getSlot(){
		return slot;
	}
	
	public static Button fromName(String slot){
		return buttons.get(slot);
	}
	
	public static Button fromId(int id){
		return buttonIds.get(id);
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
}
