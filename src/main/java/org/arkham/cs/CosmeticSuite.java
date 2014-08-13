package org.arkham.cs;

import org.arkham.cs.commands.ColorCommand;
import org.arkham.cs.commands.CosmeticCommand;
import org.arkham.cs.commands.PortalCommand;
import org.arkham.cs.db.Authentication;
import org.arkham.cs.db.SQLQueryThread;
import org.arkham.cs.effects.EffectManager;
import org.arkham.cs.events.BleedListener;
import org.arkham.cs.events.BubbleListener;
import org.arkham.cs.events.FlyListener;
import org.arkham.cs.events.MoveListener;
import org.arkham.cs.gui.GUIManager;
import org.arkham.cs.handler.ChatColorManager;
import org.arkham.cs.interfaces.Button;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class CosmeticSuite extends JavaPlugin {
	
	private static CosmeticSuite instance;
	private GUIManager guiManager;
	private EffectManager effectManager;
	private CosmeticCommand cCommand;
	private ChatColorManager cManager;
	
	public static final String PREFIX = ChatColor.AQUA + "[ArkhamCosmetics] " + ChatColor.YELLOW;
	
	public void onEnable(){
		instance = this;
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
		new BleedListener(this);
		GUIManager.setUp();
		SQLQueryThread.addQuery("CREATE DATABASE IF NOT EXISTS " + Authentication.sqldb);
		SQLQueryThread.addQuery("CREATE TABLE IF NOT EXISTS `purchases` (`player` varchar(64) PRIMARY KEY , `buttons` longtext)");
		SQLQueryThread.addQuery("CREATE TABLE IF NOT EXISTS `colors` (`player` varchar(64) PRIMARY KEY , `code` varchar(2)");
		SQLQueryThread.addQuery("CREATE TABLE IF NOT EXISTS `globalkits` (`player` varchar(64) PRIMARY KEY , id int, `time` long");
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
