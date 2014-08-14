package org.arkham.cs.cosmetics;

import java.util.ArrayList;
import java.util.List;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.effects.EffectManager;
import org.arkham.cs.gui.Category;
import org.arkham.cs.gui.ItemFactory;
import org.arkham.cs.handler.ParticleLibManager.FancyEffects;
import org.arkham.cs.interfaces.Button;
import org.arkham.cs.utils.PlayerMetaDataUtil;
import org.arkham.cs.utils.Rank;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class CustomEffect extends Button {

	private FancyEffects effect;
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
	public CustomEffect(int slot, Category cat, FancyEffects effect, String permission, Material display, int amount, Rank rank) {
		super(slot, cat, permission, ItemFactory.create(display, effect.name()));
		this.effect = effect;
		this.amount = amount;
		effects.add(this);
	}

	public int getAmount(){
		return amount;
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
	
	public static List<CustomEffect> getEffects(){
		return effects;
	}

}