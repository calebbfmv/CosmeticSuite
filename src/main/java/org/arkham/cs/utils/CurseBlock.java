package org.arkham.cs.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.arkham.cs.gui.Category;
import org.arkham.cs.interfaces.Button;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CurseBlock extends Button {
	
	private ItemStack item;
	private Rank rank;
	private static HashMap<UUID, CurseBlock> blocks = new HashMap<>();
	private static HashMap<Rank, List<CurseBlock>> blocksByRank = new HashMap<>();
	
	/**
	 * 
	 * @param slot
	 * @param permission
	 * @param item
	 */
	public CurseBlock(int slot, String permission, ItemStack item, Rank rank){
		super(slot, Category.CURSE_BLOCKS, permission);
		this.item = item;
		this.rank = rank;
		List<CurseBlock> cbs = blocksByRank.get(rank);
		if(cbs == null){
			cbs = new ArrayList<>();
		}
		cbs.add(this);
		if(rank == Rank.SUPERHERO){
			cbs.addAll(blocksByRank.get(Rank.HERO));
		}
		blocksByRank.put(rank, cbs);
	}
	
	public Rank getRank(){
		return rank;
	}
	
	@Override
	public ItemStack getDisplay() {
		return item;
	}

	@Override
	public void onClick(Player player) {
		blocks.put(player.getUniqueId(), this);
		player.closeInventory();
		String item = "bob";
		String[] str = this.getPermission().split(".");
		str = NameUtils.formatAndReturn(str, " ", true);
		item = str[2];
		player.sendMessage(ChatColor.AQUA + "[ArkhamCosmetics] " + ChatColor.YELLOW + "You will now walk on " + item);
	}
	
	public static CurseBlock get(Player player){
		return blocks.get(player.getUniqueId());
	}
	
	public static List<CurseBlock> getByRank(Rank rank){
		return blocksByRank.get(rank);
	}
}
