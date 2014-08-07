package org.arkham.cs.effects;

import java.util.ArrayList;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.gui.Category;
import org.arkham.cs.gui.GUIPage;
import org.arkham.cs.gui.ItemFactory;
import org.arkham.cs.interfaces.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CustomEffect extends Button {
	
	private ParticleEffect effect;
	private String permission;
	private Material display;
	private int amount;
	private static ArrayList<CustomEffect> effects = new ArrayList<>();
	
	/**
	 * @param slot
	 * @param effect
	 * @param permission
	 * @param display
	 */
	public CustomEffect(int slot, String name, ParticleEffect effect, String permission, Material display, int amount) {
		super(slot, name);
		this.effect = effect;
		this.permission = permission;
		this.display = display;
		this.amount = amount;
		effects.add(this);
	}
	
	public int getAmount(){
		return amount;
	}

	@Override
	public ItemStack getDisplay() {
		ItemStack item = new ItemStack(display);
		item = ItemFactory.create(display, getName()); 
		return item;
	}

	@Override
	public String getPermission() {
		return permission;
	}


	@Override
	public void onClick(Player player) {
		System.out.println("Called bby");
		EffectManager manager = CosmeticSuite.getInstance().getEffectManager();
		manager.setEffect(player, this);
		player.closeInventory();
	}
	
	public ParticleEffect getEffect(){
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