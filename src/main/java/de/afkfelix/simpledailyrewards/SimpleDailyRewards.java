package de.afkfelix.simpledailyrewards;

import de.afkfelix.simpledailyrewards.commands.DailyRewardCommand;
import de.afkfelix.simpledailyrewards.commands.StatsCommand;
import de.afkfelix.simpledailyrewards.listeners.GuiClickListener;
import de.afkfelix.simpledailyrewards.listeners.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class SimpleDailyRewards extends JavaPlugin {

    private File configFile;
    private FileConfiguration config;

    @Override
    public void onEnable() {
        createConfig();

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new GuiClickListener(this), this);

        this.getCommand("stats").setExecutor(new StatsCommand(this));
        this.getCommand("dailyreward").setExecutor(new DailyRewardCommand(this));
    }

    @Override
    public void onDisable() {
        saveConfigFile();
        getLogger().info("SimpleDailyRewards disabled!");
    }


    private void createConfig() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void saveConfigFile() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfigData() {
        return this.config;
    }
}