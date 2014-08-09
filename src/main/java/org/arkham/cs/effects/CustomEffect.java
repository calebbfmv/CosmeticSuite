package org.arkham.cs.effects;

import java.util.ArrayList;
import java.util.List;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.gui.Category;
import org.arkham.cs.gui.GUIManager;
import org.arkham.cs.gui.GUIPage;
import org.arkham.cs.interfaces.Button;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CustomEffect extends Button {

	private ParticleEffect effect;
	private Material display;
	private int amount;
	private static ArrayList<CustomEffect> effects = new ArrayList<>();

	/**
	 * @param slot
	 * @param cat
	 * @param effect
	 * @param permission
	 * @param display
	 * @param amount
	 */
	public CustomEffect(int slot, Category cat, ParticleEffect effect, String permission, Material display, int amount) {
		super(slot, cat, permission);
		this.effect = effect;
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
		return item;
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
		CosmeticSuite suite =  CosmeticSuite.getInstance();
		if(suite == null){
			return;
		}
		GUIManager manager = suite.getGuiManager();
		if(manager == null){
			return;
		}
		List<GUIPage> pages = manager.getPages(Category.EFFECTS);
		GUIPage page = pages.get(0);
		if(page == null){
			return;
		}
		for(CustomEffect effect : effects){
			if(!player.hasPermission(effect.getPermission())){
				page.getInv().setItem(effect.getSlot(), effect.noPermissionItem().getItem());
			} else {
				page.getInv().setItem(effect.getSlot(), effect.getDisplay());
			}
		}
	}
}