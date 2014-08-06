package org.arkham.cs.gui;

import java.util.HashMap;
import java.util.List;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.effects.CustomEffect;
import org.arkham.cs.hats.Hat;
import org.arkham.cs.interfaces.Button;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
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
	}

	@SuppressWarnings("unused")
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
				System.out.println(permission + " :: " + lore + " :: " + mat.name().toLowerCase());
				ItemStack item = new ItemStack(mat);
				new Hat(slot, item, s, permission, lore);
				slot++;
			}
		}
		if(effects != null){
			new GUIPage(ChatColor.RED + "Effects", Category.EFFECTS);
			int slot = 0;
			for(String s : effects.getKeys(false)){
				String e = effects.getString(s + ".effectType");
				e = e.toUpperCase();
				String permission = effects.getString(s + ".permission");
				Material display = Material.matchMaterial(effects.getString(s + ".item"));
				Effect effect = Effect.valueOf(e);
				new CustomEffect(slot, effect, permission, display);
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
			Button button = Button.fromSlot(event.getRawSlot());
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
				Hat.populate(player);
				CustomEffect.populate(player);
			}
		}.runTaskLater(CosmeticSuite.getInstance(), 5L);
	}
	
	public Inventory getMain(){
		return main;
	}
}
