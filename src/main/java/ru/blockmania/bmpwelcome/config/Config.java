package ru.blockmania.bmpwelcome.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config {
    private static JavaPlugin plugin;
    private static FileConfiguration config;

    public Config(JavaPlugin plugin) {
        this.plugin = plugin;
        this.plugin.saveDefaultConfig();
        this.config = plugin.getConfig();

    }

    public static FileConfiguration getConfig() {
        return config;

    }
    public static void reloadCfg() {
        config = plugin.getConfig();
        plugin.reloadConfig();
    }


}
