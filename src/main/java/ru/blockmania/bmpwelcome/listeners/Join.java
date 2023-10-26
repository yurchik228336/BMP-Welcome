package ru.blockmania.bmpwelcome.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
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
    private final FileConfiguration config;
    private final Random random = new Random();
    private JavaPlugin plugin;

    public Join(FileConfiguration config, JavaPlugin plugin) {
        this.config = config;
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerLoginEvent e) {
        Player player = e.getPlayer();
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
                                player.sendMessage(Chat.color(messageLine));
                            }

                            String soundName = (String) messageMap.get("sound");
                            if (soundName != null && !soundName.isEmpty()) {
                                Sound sound = Sound.valueOf(soundName);
                                player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
                            }
                            break;
                        }
                    }
                }
            }
        }.runTaskLater(plugin, 40);
    }
}

