package org.arkham.cs.gui;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemFactory {
		
	public static ItemStack create(Material mat, String displayName, int amount, byte data,  String... lore){
		ItemStack item =  new ItemStack(mat, amount, data);
		ItemMeta meta = item.getItemMeta();
		displayName = ChatColor.translateAlternateColorCodes('&', displayName);
		meta.setDisplayName(displayName);
		List<String> lore1 = new ArrayList<>();
		if(lore.equals("")){
			item.setItemMeta(meta);
			return item;
		}
		lore1.add(" ");
		for(String s : lore){
			lore1.add(ChatColor.translateAlternateColorCodes('&', s));
		}
		meta.setLore(lore1);
		return item;
	}
	
	public static ItemStack create(Material mat, String displayName){
		return create(mat, displayName, 1, (byte) 0, "");
	}
	
	public static ItemStack create(Material mat, String displayName, String... lore){
		return create(mat, displayName, 1, (byte) 0, lore);
	}
	
	public static ItemStack create(Material mat, String displayName, List<String> lore){
		return create(mat, displayName, 1, (byte) 0, (String[]) lore.toArray()); 
	}

}
