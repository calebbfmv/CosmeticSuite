package org.arkham.cs.cosmetics;

import org.arkham.cs.gui.Category;
import org.arkham.cs.handler.KitManager;
import org.arkham.cs.interfaces.Button;
import org.arkham.cs.utils.PlayerMetaDataUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HeroKit extends Button implements GlobalKit {

	private long cooldown = 24;
	private ItemStack[] items;

	public HeroKit() {
		super(0, Category.KITS, "cosmetics.kits.hero");
		// 2x Gray Dye, 2x LightGray Dye, 2x Cyan Dye, 2x Purple Dye.
		items = new ItemStack[] {
				new ItemStack(Material.INK_SACK, 2, (byte) 8),
				new ItemStack(Material.INK_SACK, 2, (byte) 7),
				new ItemStack(Material.INK_SACK, 2, (byte) 6),
				new ItemStack(Material.INK_SACK, 2, (byte) 5)
		};
	}
	
	@Override
	public ItemStack[] getItems() {
		return items;
	}

	@Override
	public void giveItems(Player player) {
		player.getInventory().addItem(items);
	}
	
	@Override
	public long getCooldown(){
		return cooldown;
	}

	@Override
	public ItemStack getDisplay() {
		ItemStack item = new ItemStack(Material.INK_SACK, 1, (byte) 8);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA + "Kit Hero");
		item.setItemMeta(meta);
		return item;
	}

	@Override
	public void onClick(Player player) {
		PlayerMetaDataUtil.removeFromSwitching(player);
		KitManager.giveKit(player, this);
	}
}
