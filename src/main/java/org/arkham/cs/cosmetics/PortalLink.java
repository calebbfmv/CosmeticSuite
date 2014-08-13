package org.arkham.cs.cosmetics;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

public class PortalLink {
	
	private Portal portal_1, portal_2;
	private Player player;
	private static HashMap<UUID, PortalLink> links = new HashMap<>();
	
	public PortalLink(Portal p1, Portal p2, Player player) {
		this.portal_1 = p1;
		this.portal_2 = p2;
		this.player = player;
		links.put(player.getUniqueId(), this);
		spark();
	}
	
	public void spark(){
		portal_1.spark();
		portal_2.spark();
	}

	/**
	 * @return the portal_1
	 */
	public Portal getPortal_1() {
		return portal_1;
	}

	/**
	 * @param portal_1 set the portal_1
	 */
	public void setPortal_1(Portal portal_1) {
		this.portal_1 = portal_1;
	}

	/**
	 * @return the portal_2
	 */
	public Portal getPortal_2() {
		return portal_2;
	}

	/**
	 * @param portal_2 set the portal_2
	 */
	public void setPortal_2(Portal portal_2) {
		this.portal_2 = portal_2;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}
	
}
