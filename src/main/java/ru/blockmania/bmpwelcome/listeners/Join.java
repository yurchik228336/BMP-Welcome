package ru.blockmania.bmpwelcome.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import ru.blockmania.bmpwelcome.utils.Chat;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class Join implements Listener {

    private final Random random = new Random();
    private JavaPlugin plugin;

    public Join(JavaPlugin plugin) {

        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerLoginEvent e) {
        Configuration config = plugin.getConfig();
        Player player = e.getPlayer();
        int cd = config.getInt("wait_time")*20;
        new BukkitRunnable() {
            @Override
            public void run() {

                List<?> messages = config.getList("messages");

                double randomValue = random.nextDouble();
                for (Object entry : messages) {
                    if (entry instanceof Map<?, ?>) {
                        Map<?, ?> messageMap = (Map<?, ?>) entry;
                        double probability = (double) messageMap.get("probability");

                        if (randomValue < probability) {
                            List<String> messageLines = (List<String>) messageMap.get("message");
                            for (String messageLine : messageLines) {
                                messageLine = Chat.papi(player,messageLine);
                                player.sendMessage(Chat.color(messageLine));
                            }

                            String soundName = (String) messageMap.get("sound");
                            if (soundName != null && !soundName.isEmpty()) {
                                try {
                                    Sound sound = Sound.valueOf(soundName);
                                    player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
                                } catch (IllegalArgumentException e){
                                    Bukkit.getLogger().warning("Ошибка, код ошибки " + e);
                                }

                            }
                            break;
                        }
                    }
                }
            }
        }.runTaskLater(plugin, cd);
    }
}

