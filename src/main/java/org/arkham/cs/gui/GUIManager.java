package org.arkham.cs.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.hats.Hat;
import org.arkham.cs.interfaces.GUIButton;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class GUIManager implements Listener {

	private Inventory main;
	private HashMap<Category, GUIPage> pages = new HashMap<>();

	public GUIManager(){
		main = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Arkham Cosmetics");
		main.setItem(3, BaseItems.hats().getItem());
		main.setItem(4, BaseItems.fireworks().getItem());
		main.setItem(5, BaseItems.effects().getItem());
	}

	public void loadPages(){
		loadPagesFromYML();
		for(Category cat :  Category.values()){
			for(GUIPage page :  GUIPage.getPages().values()){
				if(page.getCategory() == cat){
					pages.put(cat, page);
				}
			}
		}
		Hat.populate();
	}

	private void loadPagesFromYML(){
		ConfigurationSection hats = CosmeticSuite.getInstance().getFileHandler().getHatConfig().getConfigurationSection("hats");
		ConfigurationSection effects = CosmeticSuite.getInstance().getFileHandler().getHatConfig().getConfigurationSection("effects");
		ConfigurationSection fireworks = CosmeticSuite.getInstance().getFileHandler().getHatConfig().getConfigurationSection("fireworks");
		if(hats != null){
			new GUIPage(ChatColor.AQUA  + "Hats", Category.HATS);
			int slot = 0;
			for(String s : hats.getKeys(false)){
				List<String> lore = hats.getStringList(s + ".lore");
				Material mat = Material.matchMaterial(hats.getString(s + ".item"));
				String permission = hats.getString(s + ".permission");
				ItemStack item = new ItemStack(mat);
				new Hat(slot, item, s, permission, lore);
				slot++;
			}
		}
	}
	
	public GUIPage getPages(Category cat){
		return pages.get(cat);
	}

	@EventHandler
	public void onClick(InventoryClickEvent event){
		if(event.getInventory() == null){
			return;
		}
		if(event.getCurrentItem() == null){
			return;
		}
		ItemStack item = event.getCurrentItem();
		if(ClickableItem.fromItem(item) == null){
			GUIButton button = GUIButton.fromSlot(event.getRawSlot());
			if(button == null){
				System.out.println("Null button on slot " + event.getRawSlot());
				return;
			}
			event.setCancelled(true);
			button.onClick((Player) event.getWhoClicked());
			return;
		}
		event.setCancelled(true);
		ClickableItem cItem = ClickableItem.fromItem(item);
		cItem.doClick((Player) event.getWhoClicked());
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		final Player player = event.getPlayer();
		new BukkitRunnable() {
			@Override
			public void run() {
				player.openInventory(main);
			}
		}.runTaskLater(CosmeticSuite.getInstance(), 5L);
	}

}
