package org.arkham.cs.utils;

import org.arkham.cs.CosmeticSuite;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class PlayerMetaDataUtil {
	
	public static void assignFly(Player player){
		if(player.hasMetadata("cosmetics-fly")){
			return;
		}
		player.setMetadata("cosmetics-fly", new FixedMetadataValue(CosmeticSuite.getInstance(), true));
	}
	
	public static void removeFly(Player player){
		if(!player.hasMetadata("cosmetics-fly")){
			return;
		}
		player.removeMetadata("cosmetics-fly", CosmeticSuite.getInstance());
	}
	
	

}
