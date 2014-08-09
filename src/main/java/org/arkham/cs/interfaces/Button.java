package org.arkham.cs.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

	public abstract void onClick(Player player);

	private int slot, id;
	private Category cat;
	private String permission;
	private static HashMap<Category, HashMap<Integer, Button>> buttons = new HashMap<>();
	private static HashMap<String, Button> buttonPerms = new HashMap<>();

	public Button(int slot, Category cat, String permission){
		this.slot = slot;		
		if(!buttons.isEmpty()){
			id = buttons.size() + 1;
		} else {
			id = 1;
		}
		this.cat = cat;
		this.permission = permission;
		HashMap<Integer, Button> bs = buttons.get(cat) == null ? new HashMap<Integer, Button>() : buttons.get(cat);
		bs.put(slot, this);
		buttons.put(cat, bs);
		buttonPerms.put(permission, this);
	}

	public String getPermission() {
		return permission;
	}

	public ClickableItem noPermissionItem(){
		ItemStack item = ItemFactory.create(Material.STAINED_GLASS_PANE, ChatColor.RED + name(getDisplay()), 1, (byte) 14, ChatColor.RED + "You do not own this item");
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
	
	private String name(ItemStack item){
		String name = item.getItemMeta().getDisplayName();
		if(name == null){
			StringBuilder builder = new StringBuilder();
			String[] str = item.getType().name().split("_");
			for(int i = 0; i < str.length; i++){
				String n = str[i];
				n = n.substring(0, 1).toUpperCase() + n.substring(1).toLowerCase();
				builder.append(n + " ");
			}
			name = builder.toString();
		}
		return name;
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
	
	public static Button fromPermission(String perm){
		return buttonPerms.get(perm.toLowerCase());
	}
	
	public static String serialze(List<Button> buttons){
		StringBuilder builder = new StringBuilder();
		int buttonsSize = buttons.size();
		for(int i = 0; i < buttonsSize; i++){
			Button button = buttons.get(i);
			if(i == (buttonsSize - 1)){
				builder.append(button.getPermission());
			} else {
				builder.append(button.getPermission() + ",");
			}
		}
		return builder.toString();
	}
	
	public static List<Button> deserialize(String permissions){
		String[] str = permissions.split(",");
		List<Button> buttons = new ArrayList<>();
		for(int i = 0; i < str.length; i++){
			String perm = str[i];
			Button button = fromPermission(perm);
			buttons.add(button);
		}
		return buttons;
	}

}
