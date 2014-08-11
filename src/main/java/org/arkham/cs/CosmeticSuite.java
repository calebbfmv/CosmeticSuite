package org.arkham.cs;

import java.io.IOException;

import org.arkham.cs.db.Authentication;
import org.arkham.cs.db.SQLQueryThread;
import org.arkham.cs.effects.EffectManager;
import org.arkham.cs.gui.GUIManager;
import org.arkham.cs.handler.FileHandler;
import org.arkham.cs.utils.BubbleListener;
import org.arkham.cs.utils.FlyListener;
import org.bukkit.plugin.java.JavaPlugin;

public class CosmeticSuite extends JavaPlugin {
	
	private static CosmeticSuite instance;
	private GUIManager guiManager;
	private EffectManager effectManager;
	private FileHandler fileHandler;
	private CosmeticCommand cCommand;
	
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
		cCommand = new CosmeticCommand();
		new BubbleListener();
		new FlyListener();
		new SQLQueryThread();
		GUIManager.setUpHeroHats();
		GUIManager.setUpSuperHeroHats();
		SQLQueryThread.addQuery("CREATE DATABASE IF NOT EXISTS " + Authentication.sqldb);
		SQLQueryThread.addQuery("CREATE TABLE IF NOT EXISTS `purchases` (`player` varchar(64) PRIMARY KEY , `buttons` longtext)");
		getServer().getPluginManager().registerEvents(guiManager, instance);
	}
	
	public CosmeticCommand getCommand(){
		return cCommand;
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
