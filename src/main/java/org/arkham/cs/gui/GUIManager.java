package org.arkham.cs.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.hats.Hat;
import org.arkham.cs.interfaces.Button;
import org.arkham.cs.utils.Rank;
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
	private HashMap<Category, List<GUIPage>> pages = new HashMap<>();

	public GUIManager(){
		main = Bukkit.createInventory(null, 9, ChatColor.DARK_PURPLE + "Arkham Cosmetics");
		main.setItem(3, BaseItems.hats().getItem());
		main.setItem(4, BaseItems.fireworks().getItem());
		main.setItem(5, BaseItems.effects().getItem());
	}

	public void loadPages(){
		loadPagesFromYML();
		for(Category cat :  Category.values()){
			List<GUIPage> pages = new ArrayList<>();
			for(GUIPage page :  GUIPage.getPages().values()){
				if(page.getCategory() == cat){
					pages.add(page);
				}
			}
			this.pages.put(cat, pages);
		}
	}

	@SuppressWarnings("unused")
	private void loadPagesFromYML(){
		ConfigurationSection effects = CosmeticSuite.getInstance().getFileHandler().getEffectConfig().getConfigurationSection("effects");
		ConfigurationSection fireworks = CosmeticSuite.getInstance().getFileHandler().getFireworkConfig().getConfigurationSection("fireworks");
		{
			int slot = 0;
			int created = 1;
			GUIPage page = new GUIPage(ChatColor.YELLOW  + "Hats", Category.HATS);
			for(Material mat : Material.values()){
				slot++;
				if(slot >= 45){
					created++;
					new GUIPage(ChatColor.YELLOW  + "Hats: " + ChatColor.RED + (created), Category.HATS);
					slot = 0;
				}
			}
		}
		//		if(effects != null){
		//			new GUIPage(ChatColor.RED + "Effects", Category.EFFECTS);
		//			int slot = 0;
		//			for(String s : effects.getKeys(false)){
		//				String e = effects.getString(s + ".effectType");
		//				String permission = "cosmetics.effects." + s; 
		//				Material display = Material.matchMaterial(effects.getString(s + ".item"));
		//				ParticleEffect effect = ParticleEffect.fromName(e);
		//				int amount = effects.getInt(s + ".amount", 15);
		//				String displayName = effect.name();
		//				StringBuilder builder = new StringBuilder();
		//				builder.append(ChatColor.GOLD + ChatColor.BOLD.toString());
		//				if(displayName.contains("_")){
		//					String[] str = displayName.split("_");
		//					for(int i = 0; i < str.length; i++){
		//						String name = str[i];
		//						builder.append(name.substring(0, 1).toUpperCase());
		//						builder.append(name.substring(1).toLowerCase());
		//						builder.append(" ");
		//					}
		//				} else {
		//					builder.append(displayName.substring(0, 1).toUpperCase());
		//					builder.append(displayName.substring(1).toLowerCase());
		//				}
		//				new CustomEffect(slot, Category.EFFECTS, effect, permission, display, amount);
		//				slot++;
		//			}
		//		}
	}

	private static String[] herohats() {
		String stuff = "Dirt, Stone, Grass, Podzol, Cobblestone, Sandstone, Glass, Sand, Log, Wood, Iron_block, Gold_block, Diamond_block, Emerald_block, Glowstone, Ice, Pumpkin, Clay, Snow_block, diamond_ore, gold_ore, iron_ore, coal_ore, redstone_ore, lapis_ore, emerald_ore, Netherrack, Netherbrick, StoneBrick, Melon, Quartz, Hay, Coal, PackedIce, Leaves, Chest, CraftingTable, Anvil, Enderchest, Furnace, EnchantmentTable, EndFrame, Cactus, Fence, Jukebox, Redstone, TnT, Beacon, RedstoneLamp, Dispenser, NoteBlock";
		return stuff.split(", ");
	}

	public static void setUpHeroHats() {
		String[] hats = herohats();
		for (int i = 0; i < hats.length; i++) {
			String s = hats[i];
			s = s.toUpperCase();
			if(s.equalsIgnoreCase("Netherbrick")){
				break;
			}
			if(s.equalsIgnoreCase("podzol")){
				ItemStack item = new ItemStack(Material.DIRT, 1, (byte) 2);
				new Hat(i, item, Rank.HERO);
			} else {
				Material mat = Material.valueOf(s);
				new Hat(mat, i, Rank.HERO);
			}
		}
	}

	public List<GUIPage> getPages(Category cat){
		return pages.get(cat);
	}

	@EventHandler
	public void onClick(InventoryClickEvent event){
		Player player = (Player) event.getWhoClicked();
		if(event.getInventory() == null){
			return;
		}
		if(event.getCurrentItem() == null){
			return;
		}
		ItemStack item = event.getCurrentItem();
		if(ClickableItem.fromItem(item) == null){
			if(GUIPage.getCurrent(player) == null){
				return;
			}
			Button button = Button.getButton(GUIPage.getCurrent((Player) event.getWhoClicked()).getCategory(), event.getRawSlot());
			if(button == null){
				System.out.println("Null button");
				return;
			}
			button.onClick(player);
			event.setCancelled(true);
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
		}.runTaskLater(CosmeticSuite.getInstance(), 40L);
	}

	public Inventory getMain(){
		return main;
	}
}
