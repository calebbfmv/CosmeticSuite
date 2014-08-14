package org.arkham.cs.cosmetics;

import java.util.ArrayList;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.effects.EffectManager;
import org.arkham.cs.gui.Category;
import org.arkham.cs.handler.ParticleLibManager.FancyEffects;
import org.arkham.cs.interfaces.Button;
import org.arkham.cs.utils.PlayerMetaDataUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CustomEffect extends Button {

	private FancyEffects effect;
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
	public CustomEffect(int slot, Category cat, FancyEffects effect, String permission, Material display, int amount) {
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
		PlayerMetaDataUtil.removeFromSwitching(player);
		EffectManager manager = CosmeticSuite.getInstance().getEffectManager();
		manager.setEffect(player, this);
		player.closeInventory();
	}

	public FancyEffects getEffect(){
		return effect;
	}

}