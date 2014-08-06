package org.arkham.cs;

import java.io.IOException;

import org.arkham.cs.effects.EffectManager;
import org.arkham.cs.gui.GUIManager;
import org.arkham.cs.handler.FileHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class CosmeticSuite extends JavaPlugin {
	
	private static CosmeticSuite instance;
	private GUIManager guiManager;
	private EffectManager effectManager;
	private FileHandler fileHandler;
	
	public void onEnable(){
		instance = this;
		try {
			fileHandler = new FileHandler(getDataFolder());
		} catch (IOException e) {
			e.printStackTrace();
		}
		guiManager = new GUIManager();
		guiManager.loadPages();
		effectManager = new EffectManager();
		new CosmeticCommand();
		getServer().getPluginManager().registerEvents(guiManager, instance);
	}
	
	public FileHandler getFileHandler() {
		return fileHandler;
	}

	public static CosmeticSuite getInstance(){
		return instance;
	}
	
	public GUIManager getGuiManager(){
		return guiManager;
	}
	
	public EffectManager getEffectManager(){
		return effectManager;
	}

}
