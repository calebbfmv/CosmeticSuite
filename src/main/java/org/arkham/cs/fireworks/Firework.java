package org.arkham.cs.fireworks;

import java.util.List;

import org.arkham.cs.interfaces.GUIButton;
import org.bukkit.ChatColor;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

public class Firework extends GUIButton {
	
	private String permission;
	private String name;
	private List<FireworkEffect> effects;

	public Firework(int slot, String permission, String name, List<FireworkEffect> effects) {
		super(slot);
		this.permission = permission;
		this.effects = effects;
		this.name = name;
		name = ChatColor.translateAlternateColorCodes('&', name);
	}

	@Override
	public ItemStack getDisplay() {
		ItemStack item = new ItemStack(Material.FIREWORK);
		FireworkMeta meta = (FireworkMeta) item.getItemMeta();
		meta.setDisplayName(name);
		for(FireworkEffect effect : effects){
			meta.addEffect(effect);
		}
		item.setItemMeta(meta);
		return item;
	}

	@Override
	public String getPermission() {
		return permission;
	}

	@Override
	public void onClick(Player player) {
		player.getInventory().addItem(getDisplay());
	}

}
