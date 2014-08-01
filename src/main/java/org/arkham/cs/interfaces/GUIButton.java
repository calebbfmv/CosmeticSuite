package org.arkham.cs.interfaces;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class GUIButton {
	
	public abstract void doClick(Player player);
	
	public abstract ItemStack getDisplay();
	
	private int slot;
	private static HashMap<Integer, GUIButton> buttons = new HashMap<>();
	
	public GUIButton(int slot){
		this.slot = slot;
		buttons.put(slot, this);
	}
	
	public int getSlot(){
		return slot;
	}
	
	public static GUIButton fromSlot(int slot){
		return buttons.get(slot);
	}
	
	
	
	

}
