package org.arkham.cs.cosmetics;

import java.util.ArrayList;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.handler.PlayerHandler;
import org.arkham.cs.utils.Rank;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Hero - Rename / Color of armor and blocks
 *  SuperHero Rename / Color weapons, armor, blocks, tools  /lore /itemcolor
 * 
 * @author calebbfmv Aug 13, 2014
 * 
 */
public class ItemColoring {

	private static ArrayList<Material> heroItems = new ArrayList<>();
	private static ArrayList<Material> superHeroItems = new ArrayList<>();


	static {
		for(Material mat : Material.values()){
			if(mat.name().toLowerCase().contains("_helmet") || mat.name().toLowerCase().contains("_chestplate") ||mat.name().toLowerCase().contains("_leggings")  || mat.name().toLowerCase().contains("_boots")){
				heroItems.add(mat);
			}
			String name = mat.name().toLowerCase();
			if(name.contains("_pickaxe") || name.contains("_axe") || name.contains("_spade") || name.contains("_hoe") || name.contains("_sword")){
				superHeroItems.add(mat);
				continue;
			} 
			heroItems.add(mat);
		}
		superHeroItems.addAll(heroItems);
	}

	public static void rename(Player player, String name){
		ItemStack item = player.getItemInHand();
		if(PlayerHandler.isNothingSpecial(player)){
			player.sendMessage(CosmeticSuite.PREFIX + "You do not have access to this!");
			return;
		}
		if(item == null || item.getType() == Material.AIR){
			player.sendMessage(CosmeticSuite.PREFIX + "Pleas have the item, that you wish to change, in your hand.");
			return;
		}
		if(name.length() > 64){
			player.sendMessage(CosmeticSuite.PREFIX + "The given name was longer than the max allowed (Your Length: " + name.length() + " Max Allowed: 64");
			return;
		}
		Rank rank = PlayerHandler.getRank(player);
		if(rank == Rank.HERO){
			if(!heroItems.contains(item.getType())){
				return;
			}
		}
		if(rank == Rank.SUPERHERO){
			if(!superHeroItems.contains(item.getType())){
				return;
			}
		}
		ItemMeta meta = item.getItemMeta();
		StringBuilder builder = new StringBuilder();
		builder.append(ChatColor.translateAlternateColorCodes('&', name));
		meta.setDisplayName(builder.toString());
	}

}