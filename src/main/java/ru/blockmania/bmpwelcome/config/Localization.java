package ru.blockmania.bmpwelcome.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Localization {
    private static JavaPlugin plugin;
    private static YamlConfiguration local;
    private static File configFile;

    public Localization(JavaPlugin plugin) {
        this.plugin = plugin;
        this.configFile = new File(plugin.getDataFolder(), "localization.yml");
        local = YamlConfiguration.loadConfiguration(configFile);
        loadLocalization();
    }



    public static void loadLocalization() {
        if (!configFile.exists()) {
            plugin.saveResource("localization.yml", false);
        } else {
            try {
                local.load(configFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void saveLocalization() {
        try {
            local.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getLocalizedString(String key) {
        return local.getString(key);
    }


    public void setLocalizedString(String key, String value) {
        local.set(key, value);
        saveLocalization();
    }
}
