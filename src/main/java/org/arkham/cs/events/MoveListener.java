package org.arkham.cs.events;

import java.util.HashMap;

import net.minecraft.server.v1_7_R4.Packet;
import net.minecraft.server.v1_7_R4.PacketPlayOutWorldEvent;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.cosmetics.CurseBlock;
import org.arkham.cs.cosmetics.TrailingBlock;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.block.Furnace;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class MoveListener implements Listener {

	private HashMap<Location, Material> blocks = new HashMap<>();

	public MoveListener(){
		CosmeticSuite cs = CosmeticSuite.getInstance();
		cs.getServer().getPluginManager().registerEvents(this, cs);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent event){
		if(event.getTo().getBlockX() == event.getFrom().getBlockX() && event.getTo().getBlockZ() == event.getFrom().getBlockZ()){
			return;
		}
		Player player = event.getPlayer();
		Location loc = player.getLocation();
		if(player.isFlying()){
			TrailingBlock tb = TrailingBlock.get(player);
			if(tb == null){
				return;
			}
			tb.run(player);
			return;
		}
		CurseBlock cb = CurseBlock.get(player);
		if(cb == null){
			return;
		}
		if(loc.getBlock().getRelative(BlockFace.DOWN).getType() == Material.AIR){
			return;
		}
		final Block below = loc.getBlock().getRelative(BlockFace.DOWN);
		if(below.getState() instanceof Chest){
			return;
		}
		if(below.getState() instanceof Furnace){
			return;
		}
		blocks.put(below.getLocation(), below.getType());
		below.setType(cb.getDisplay().getType());
		below.setData(cb.getDisplay().getData().getData());
		play(below.getLocation(), below.getType());
		new BukkitRunnable() {
			@Override
			public void run() {
				Location l = below.getLocation();
				l.getBlock().setType(blocks.get(l));
				play(l, blocks.get(l));
			}
		}.runTaskLaterAsynchronously(CosmeticSuite.getInstance(), 20L * 5);
	}
	
	public void play(final Location l, final Material m){
		CosmeticSuite.getInstance().getServer().getScheduler().runTaskAsynchronously(CosmeticSuite.getInstance(), new Runnable(){
             @SuppressWarnings("deprecation")
			@Override
             public void run(){
                 int particle_id = m.getId();
                 Packet particles = new PacketPlayOutWorldEvent(2001, Math.round(l.getBlockX()), Math.round(l.getBlockY()), Math.round(l.getBlockZ()), particle_id, false);
                 ((CraftServer) CosmeticSuite.getInstance().getServer()).getServer().getPlayerList().sendPacketNearby(l.getBlockX(), l.getBlockY(), l.getBlockZ(), 16, ((CraftWorld) l.getWorld()).getHandle().dimension, particles);
             }
         });
	}
}