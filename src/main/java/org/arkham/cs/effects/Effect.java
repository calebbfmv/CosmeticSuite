package org.arkham.cs.effects;

import org.arkham.cs.interfaces.GUIButton;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Effect extends GUIButton {

	public Effect(int slot) {
		super(slot);
	}

	@Override
	public void doClick(Player player) {
		
	}

	@Override
	public ItemStack getDisplay() {
		return null;
	}

}
