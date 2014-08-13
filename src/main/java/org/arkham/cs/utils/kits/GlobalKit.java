package org.arkham.cs.utils.kits;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface GlobalKit {
	
	public ItemStack[] getItems();
	
	public void giveItems(Player player);

	public long getCooldown();
}
