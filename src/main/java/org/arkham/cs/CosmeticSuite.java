package org.arkham.cs;

import org.arkham.cs.gui.GUIManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CosmeticSuite extends JavaPlugin {
	
	private static CosmeticSuite instance;
	private GUIManager guiManager;
	
	public void onEnable(){
		instance = this;
		guiManager = new GUIManager();
	}
	
	public static CosmeticSuite getInstance(){
		return instance;
	}
	
	public GUIManager getGuiManager(){
		return guiManager;
	}

}
