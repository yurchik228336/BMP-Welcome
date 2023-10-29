package ru.blockmania.bmpwelcome.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import ru.blockmania.bmpwelcome.config.Config;
import ru.blockmania.bmpwelcome.config.Localization;
import ru.blockmania.bmpwelcome.utils.Chat;



public class Reload implements CommandExecutor {
    private JavaPlugin plugin;


    public Reload(JavaPlugin plugin){

        this.plugin = plugin;

    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission("welcome.admin")){
            String notperm = Localization.getLocalizedString("welcome_notperm");
            notperm = Chat.color(notperm);
            sender.sendMessage(Chat.papi((Player) sender,notperm));
            return true;
        }
        if (args.length==0){
            String ussage = Localization.getLocalizedString("welcome_ussage");
            if (!ussage.isEmpty()){
                ussage = Chat.papi((Player) sender, ussage);
                sender.sendMessage(Chat.color(ussage));


            }
            return false;
        }

            switch (args[0]){
                case "reload":
                    Config.reloadCfg();
                    Localization.loadLocalization();
                    String succes = Localization.getLocalizedString("succes_reload");

                    if (!succes.isEmpty()){
                        succes = Chat.papi((Player)sender,succes);
                        sender.sendMessage(Chat.color(succes));
                    }

                    break;
                default:

                    String error = Localization.getLocalizedString("error_reload");
                    if (!error.isEmpty()){
                        error = Chat.papi((Player)sender,error);
                        sender.sendMessage(Chat.color(error));
                    }

                    break;
            }


        return false;
    }
}
