package org.arkham.cs.hats;

import org.arkham.cs.interfaces.GUIButton;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Hat extends GUIButton {

	public Hat(int slot) {
		super(slot);
	}


	@Override
	public ItemStack getDisplay() {
		return null;
	}

	@Override
	public String getPermission() {
		return null;
	}


	@Override
	public void onClick(Player player) {
		
	}

}
