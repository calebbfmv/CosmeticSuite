package org.arkham.cs.handler;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileHandler {
	
	private File hats, effects, fireworks;
	private FileConfiguration hatConfig, effectConfig, fireworkConfig;
	
	public FileHandler(File src) throws IOException{
		if(!src.exists()){
			src.mkdirs();
		}
		hats = new File(src, "hats.yml");
		effects = new File(src, "effects.yml");
		fireworks = new File(src, "fireworks.yml");
		if(!hats.exists()){
			hats.createNewFile();
		}
		if(!effects.exists()){
			effects.createNewFile();
		}
		if(!fireworks.exists()){
			fireworks.createNewFile();
		}
		hatConfig = YamlConfiguration.loadConfiguration(hats);
		effectConfig = YamlConfiguration.loadConfiguration(effects);
		fireworkConfig = YamlConfiguration.loadConfiguration(fireworks);
	}

	public FileConfiguration getHatConfig() {
		return hatConfig;
	}

	public FileConfiguration getEffectConfig() {
		return effectConfig;
	}

	public FileConfiguration getFireworkConfig() {
		return fireworkConfig;
	}
	
	public void saveHats(){
		try {
			hatConfig.save(hats);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveFireworks(){
		try {
			fireworkConfig.save(fireworks);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveEffects(){
		try {
			effectConfig.save(effects);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
