package org.arkham.cs.utils;

import java.util.HashMap;
import java.util.UUID;

import org.arkham.cs.handler.PlayerHandler;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;

@SuppressWarnings("deprecation")
public class TrailingBlock {

	public FallingBlock block;
	private boolean inUse;
	private Rank rank;

	private static HashMap<UUID, TrailingBlock> tbs = new HashMap<>();

	public TrailingBlock(Player player) {
		UUID uuid = player.getUniqueId();
		this.inUse = true;
		this.rank = PlayerHandler.getRank(player);
		switch (rank) {
		case DEFAULT:
			break;
		case HERO:
			this.block = player.getWorld().spawnFallingBlock(player.getLocation(), Material.WATER, (byte) 0);
			block.setDropItem(false);
			break;
		case SUPERHERO:
			this.block = player.getWorld().spawnFallingBlock(player.getLocation(), Material.LAVA, (byte) 0);
			block.setDropItem(false);
			break;
		}
		tbs.put(uuid, this);
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	public FallingBlock getBlock() {
		return block;
	}

	public Rank getRank() {
		return rank;
	}

	public void run(Player player) {
		player.getWorld().spawnFallingBlock(player.getLocation(), block.getMaterial(), (byte) 0);
	}

	public static TrailingBlock get(Player player) {
		UUID uuid = player.getUniqueId();
		return tbs.get(uuid);
	}
}