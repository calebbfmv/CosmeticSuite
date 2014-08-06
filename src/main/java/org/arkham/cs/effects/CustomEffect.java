package org.arkham.cs.effects;

import java.util.ArrayList;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.gui.Category;
import org.arkham.cs.gui.GUIPage;
import org.arkham.cs.gui.ItemFactory;
import org.arkham.cs.interfaces.Button;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CustomEffect extends Button {
	
	private Effect effect;
	private String permission;
	private Material display;
	private static ArrayList<CustomEffect> effects = new ArrayList<>();
	
	/**
	 * @param slot
	 * @param effect
	 * @param permission
	 * @param display
	 */
	public CustomEffect(int slot, Effect effect, String permission, Material display) {
		super(slot);
		this.effect = effect;
		this.permission = permission;
		this.display = display;
		effects.add(this);
	}

	@Override
	public ItemStack getDisplay() {
		ItemStack item = new ItemStack(display);
		String displayName = effect.name();
		StringBuilder builder = new StringBuilder();
		if(displayName.contains("_")){
			String[] str = displayName.split("_");
			String second = str[1];
			displayName = str[0];
			builder.append(displayName.substring(0, 1).toUpperCase());
			builder.append(displayName.substring(1).toLowerCase());
			builder.append(" ");
			builder.append(second.substring(0, 1).toUpperCase());
			builder.append(second.substring(1).toLowerCase());
		} else {
			builder.append(displayName.substring(0, 1).toUpperCase());
			builder.append(displayName.substring(1).toLowerCase());
		}
		item = ItemFactory.create(display, builder.toString()); 
		return item;
	}

	@Override
	public String getPermission() {
		return permission;
	}


	@Override
	public void onClick(Player player) {
		EffectManager manager = CosmeticSuite.getInstance().getEffectManager();
		manager.setEffect(player, this);
		player.closeInventory();
	}
	
	public Effect getEffect(){
		return effect;
	}
	
	public static void populate(Player player){
		GUIPage page = CosmeticSuite.getInstance().getGuiManager().getPages(Category.EFFECTS);
		for(CustomEffect effect : effects){
			if(!player.hasPermission(effect.getPermission())){
				page.getInv().setItem(effect.getSlot(), effect.noPermissionItem().getItem());
			} else {
				page.getInv().setItem(effect.getSlot(), effect.getDisplay());
			}
		}
	}
}