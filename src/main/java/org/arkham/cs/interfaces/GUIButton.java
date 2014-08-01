package org.arkham.cs.interfaces;

import java.util.Collections;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class GUIButton {
		
	public abstract ItemStack getDisplay();
	
	public abstract String getPermission();
	
	public abstract void onClick(Player player);
		
	private int slot, id;
	private static HashMap<Integer, GUIButton> buttons = new HashMap<>();
	
	public GUIButton(int slot){
		this.slot = slot;
		buttons.put(slot, this);
		if(!buttons.isEmpty()){
			id = Collections.max(buttons.keySet()) + 1;
		} else {
			id = 1;
		}
	}
	
	public int getSlot(){
		return slot;
	}
	
	public static GUIButton fromSlot(int slot){
		return buttons.get(slot);
	}
	
	public int getId(){
		return id;
	}
	
}
