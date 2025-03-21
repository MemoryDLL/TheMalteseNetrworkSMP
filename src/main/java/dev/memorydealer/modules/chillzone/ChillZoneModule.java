package dev.memorydealer.modules.chillzone;

import dev.memorydealer.modules.chillzone.listeners.PvPListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ChillZoneModule {

    private final Plugin plugin;
    private LocalDateTime chillEndDate;
    private int taskId;

    public ChillZoneModule(Plugin plugin) {
        this.plugin = plugin;
    }

    public void start() {
        LocalDateTime baseline = LocalDateTime.of(2025, 3, 28, 0, 0);
        chillEndDate = baseline.plusDays(3);

        // Register PvP listener
        Bukkit.getPluginManager().registerEvents(new PvPListener(chillEndDate), plugin);

        // Schedule ActionBar message
        taskId = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            long daysLeft = LocalDateTime.now().until(chillEndDate, ChronoUnit.DAYS);
            long hoursLeft = LocalDateTime.now().until(chillEndDate, ChronoUnit.HOURS) % 24;

            String message = LocalDateTime.now().isBefore(chillEndDate)
                    ? ChatColor.GREEN + "☮ Chill Zone ends in " + ChatColor.YELLOW + daysLeft + "d " + hoursLeft + "h"
                    : ChatColor.RED + "⚔ PvP is now ENABLED!";

            Bukkit.getOnlinePlayers().forEach(player ->
                    player.sendActionBar(message)
            );

        }, 0L, 20L).getTaskId();
    }

    public void stop() {
        Bukkit.getScheduler().cancelTask(taskId);
    }
}