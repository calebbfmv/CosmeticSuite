package org.arkham.cs;

import java.io.IOException;

import org.arkham.cs.db.Authentication;
import org.arkham.cs.db.SQLQueryThread;
import org.arkham.cs.effects.EffectManager;
import org.arkham.cs.gui.GUIManager;
import org.arkham.cs.handler.FileHandler;
import org.arkham.cs.interfaces.Button;
import org.arkham.cs.utils.BubbleListener;
import org.arkham.cs.utils.ChatColorManager;
import org.arkham.cs.utils.FlyListener;
import org.arkham.cs.utils.MoveListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class CosmeticSuite extends JavaPlugin {
	
	private static CosmeticSuite instance;
	private GUIManager guiManager;
	private EffectManager effectManager;
	private FileHandler fileHandler;
	private CosmeticCommand cCommand;
	private ChatColorManager cManager;
	
	public static final String PREFIX = ChatColor.AQUA + "[ArkhamCosmetics] " + ChatColor.YELLOW;
	
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
		cManager = new ChatColorManager();
		new BubbleListener();
		new FlyListener();
		new SQLQueryThread();
		new MoveListener();
		new ColorCommand(this);
		new PortalCommand();
		GUIManager.setUpHeroHats();
		GUIManager.setUpSuperHeroHats();
		GUIManager.setUpHeroCurseBlocks();
		SQLQueryThread.addQuery("CREATE DATABASE IF NOT EXISTS " + Authentication.sqldb);
		SQLQueryThread.addQuery("CREATE TABLE IF NOT EXISTS `purchases` (`player` varchar(64) PRIMARY KEY , `buttons` longtext)");
		SQLQueryThread.addQuery("CREATE TABLE IF NOT EXISTS `colors` (`player` varchar(64) PRIMARY KEY , `code` varchar(2)");
		getServer().getPluginManager().registerEvents(guiManager, instance);
//		getLogger().info("Printing Permiossion nodes now: ");
		StringBuilder builder = new StringBuilder();
		for(Button button : Button.allButtons){
			builder.append(button.getPermission() + "  \n");
		}
		getLogger().info("===========================");
//		getLogger().info("\n" + builder.toString());
		getLogger().info("===========================");
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
	
	public ChatColorManager getChatColorManager(){
		return cManager;
	}

}
