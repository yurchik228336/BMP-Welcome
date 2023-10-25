package ru.blockmania.bmpwelcome;

import org.bukkit.plugin.java.JavaPlugin;
import ru.blockmania.bmpwelcome.config.Config;
import ru.blockmania.bmpwelcome.listeners.Join;

public final class BMP_Welcome extends JavaPlugin {

    @Override
    public void onEnable() {
        Config configManager = new Config(this);
        getServer().getPluginManager().registerEvents(new Join(configManager.getConfig(), this), this);
        saveConfig();
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
