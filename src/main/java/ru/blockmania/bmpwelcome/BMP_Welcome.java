package ru.blockmania.bmpwelcome;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


import ru.blockmania.bmpwelcome.commands.Reload;
import ru.blockmania.bmpwelcome.commands.TabCompleter.ReloadCompleter;
import ru.blockmania.bmpwelcome.config.Config;
import ru.blockmania.bmpwelcome.config.Localization;
import ru.blockmania.bmpwelcome.listeners.Join;

public final class BMP_Welcome extends JavaPlugin {

    @Override
    public void onEnable() {
        Localization localization = new Localization(this);

        Config configManager = new Config(this);
        getCommand("welcome").setExecutor(new Reload(this));
        getCommand("welcome").setTabCompleter(new ReloadCompleter());
        getServer().getPluginManager().registerEvents(new Join(this), this);

    }


    @Override
    public void onDisable() {

    }
}
