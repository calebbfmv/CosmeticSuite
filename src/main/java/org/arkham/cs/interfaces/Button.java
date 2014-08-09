package org.arkham.cs.interfaces;

import java.util.HashMap;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.gui.Category;
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
	private Category cat;
	private static HashMap<Category, HashMap<Integer, Button>> buttons = new HashMap<>();

	public Button(int slot, Category cat){
		this.slot = slot;		
		if(!buttons.isEmpty()){
			id = buttons.size() + 1;
		} else {
			id = 1;
		}
		this.cat = cat;
		HashMap<Integer, Button> bs = buttons.get(cat) == null ? new HashMap<Integer, Button>() : buttons.get(cat);
		bs.put(slot, this);
		buttons.put(cat, bs);
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

	public static Button fromId(int id){
		return null;
	}

	public int getId(){
		return id;
	}

	public Category getCategory() {
		return cat;
	}
	
	public static Button getButton(Category cat, int slot){
		return buttons.get(cat).get(slot);
	}

}
