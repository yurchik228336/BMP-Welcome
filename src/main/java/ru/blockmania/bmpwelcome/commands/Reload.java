package ru.blockmania.bmpwelcome.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import ru.blockmania.bmpwelcome.config.Config;
import ru.blockmania.bmpwelcome.config.Localization;
import ru.blockmania.bmpwelcome.utils.Formating;

import java.text.Normalizer;

public class Reload implements CommandExecutor {
    private JavaPlugin plugin;


    public Reload(JavaPlugin plugin){

        this.plugin = plugin;

    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (!sender.hasPermission("welcome.admin")){
            String notperm = Localization.getLocalizedString("welcome_notperm");
            notperm = Formating.fullformat((Player) sender,notperm);
            sender.sendMessage(notperm);
            return true;
        }
        if (args.length==0){
            String ussage = Localization.getLocalizedString("welcome_ussage");
            if (!ussage.isEmpty()){
                ussage = Formating.fullformat((Player) sender,ussage);
                sender.sendMessage(ussage);


            }
            return false;
        }

            switch (args[0]){
                case "reload":
                    Config.reloadCfg();
                    Localization.loadLocalization();
                    String succes = Localization.getLocalizedString("succes_reload");

                    if (!succes.isEmpty()){
                        succes = Formating.fullformat((Player) sender,succes);
                        sender.sendMessage(succes);
                    }
                    break;

                default:

                    String error = Localization.getLocalizedString("error_reload");
                    if (!error.isEmpty()){
                        error = Formating.fullformat((Player) sender,error);
                        sender.sendMessage(error);
                    }

                    break;
            }


        return false;
    }
}
