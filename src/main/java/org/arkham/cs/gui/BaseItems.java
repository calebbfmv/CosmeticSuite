package org.arkham.cs.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BaseItems {
	
	public static ItemStack hats(){
		return ItemFactory.create(Material.BUCKET, ChatColor.AQUA + "Hats", ChatColor.GREEN + "Click to open the hat GUI");
	}
	
	public static ItemStack effects(){
		return ItemFactory.create(Material.BUCKET, ChatColor.MAGIC + "<>" + ChatColor.RESET + ChatColor.GOLD + "Effects", ChatColor.GREEN + "Click to open the effects GUI");
	}
	
	public static ItemStack back(){
		return ItemFactory.create(Material.REDSTONE, ChatColor.RED + ChatColor.BOLD.toString() + "Go Back");
	}
	
	public static ItemStack next(){
		return ItemFactory.create(Material.GOLD_INGOT, ChatColor.GREEN + ChatColor.BOLD.toString() + "Proceed");
	}
	
	public static ItemStack fireworks(){
		return ItemFactory.create(Material.BUCKET, ChatColor.RED + "Fi" + ChatColor.YELLOW + "re" + ChatColor.BLUE + "works", ChatColor.GREEN + "Click to open the firework GUI");
	}

}
