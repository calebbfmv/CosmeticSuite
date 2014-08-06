package org.arkham.cs.interfaces;

import java.util.Collections;
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
	private static HashMap<Integer, Button> buttons = new HashMap<>();
	
	public Button(int slot){
		this.slot = slot;
		buttons.put(slot, this);
		if(!buttons.isEmpty()){
			id = Collections.max(buttons.keySet()) + 1;
		} else {
			id = 1;
		}
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
	
	public static Button fromSlot(int slot){
		return buttons.get(slot);
	}
	
	public int getId(){
		return id;
	}
	
}
