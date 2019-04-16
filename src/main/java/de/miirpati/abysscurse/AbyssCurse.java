package de.miirpati.abysscurse;

import de.miirpati.abysscurse.events.EffectListener;
import de.miirpati.abysscurse.tools.Tools;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.SplittableRandom;
import org.bukkit.*;

public class AbyssCurse extends JavaPlugin {
    File configFile;
    FileConfiguration config;
    File universeConfigFile;
    FileConfiguration universe;

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new EffectListener(), this);
        //register listeners
        //register commands
        //load effects
        //handle players

        if (Tools.isMineInAbyss) onEnableHooked(); //checks whether running with mineinabyss
        else onEnableStandalone();
    }

    @Override
    public void onDisable(){
        getLogger().info("Shutting down, saving");

        getServer().getServicesManager().unregisterAll(this);
        Bukkit.getScheduler().cancelTasks(this);
    }


    private void onEnableStandalone(){
        getLogger().info("Running standalone");

    }

    private void onEnableHooked(){
        getLogger().info("Running with MineInAbyss");

    }


    private void createUniverseConfigFile() { //not when running standalone
        universeConfigFile = new File(getDataFolder(), "universe.yml");
        if (!universeConfigFile.exists()) {
            universeConfigFile.getParentFile().mkdirs();
            saveResource("universe.yml", false);
        }

        universe = new YamlConfiguration();
        try {
            universe.load(universeConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void createConfig(Plugin plugin) {
        configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            this.saveDefaultConfig();
        }

        config = new YamlConfiguration();
        try {
            config.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}