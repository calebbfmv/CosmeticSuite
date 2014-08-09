package org.arkham.cs.hats;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.v1_7_R4.PacketPlayOutEntityEquipment;

import org.arkham.cs.CosmeticSuite;
import org.arkham.cs.gui.Category;
import org.arkham.cs.gui.GUIManager;
import org.arkham.cs.gui.GUIPage;
import org.arkham.cs.handler.PurchaseHandler;
import org.arkham.cs.interfaces.Button;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Hat extends Button {

	private ItemStack item;
	private static List<Hat> hats = new ArrayList<>();

	/**
	 * @param slot
	 * @param item
	 * @param name
	 * @param lore
	 */
	public Hat(int slot, ItemStack item, Category name, String permission) {
		super(slot, name, permission);
		this.item = item;
		hats.add(this);
	}

	@Override
	public ItemStack getDisplay() {
		return item;
	}


	@Override
	public void onClick(Player player) {
		player.getInventory().setHelmet(getDisplay());
		PacketPlayOutEntityEquipment equip = new PacketPlayOutEntityEquipment(player.getEntityId(), 3, CraftItemStack.asNMSCopy(new ItemStack(Material.AIR)));
		for(Player p :  Bukkit.getOnlinePlayers()){
			if(!p.getName().equalsIgnoreCase(player.getName())){
			} else { 
				((CraftPlayer)p).getHandle().playerConnection.sendPacket(equip);
			}
		}
		player.closeInventory();
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
		List<GUIPage> pages = manager.getPages(Category.HATS);
		GUIPage page = pages.get(0);
		if(page == null){
			return;
		}
		for(Hat hat : hats){
			if(PurchaseHandler.hasPurchased(player, hat)){
				page.getInv().setItem(hat.getSlot(), hat.noPermissionItem().getItem());
			} else {
				page.getInv().setItem(hat.getSlot(), hat.getDisplay());
			}
		}
	}

}