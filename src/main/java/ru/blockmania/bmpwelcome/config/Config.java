package ru.blockmania.bmpwelcome.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config {
    private final JavaPlugin plugin;
    private FileConfiguration config;

    public Config(JavaPlugin plugin) {
        this.plugin = plugin;
        this.plugin.saveDefaultConfig();
        this.config = plugin.getConfig();

    }

    public FileConfiguration getConfig() {
        return config;
    }
}
