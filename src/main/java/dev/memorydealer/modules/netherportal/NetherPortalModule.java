package dev.memorydealer.modules.netherportal;

import dev.memorydealer.modules.netherportal.listeners.PortalCreateListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.time.LocalDateTime;

public class NetherPortalModule {

    private final Plugin plugin;

    public NetherPortalModule(Plugin plugin) {
        this.plugin = plugin;
    }

    public void start() {
        LocalDateTime baseline = LocalDateTime.of(2025, 3, 28, 0, 0);
        LocalDateTime netherAllowedTime = baseline.plusWeeks(1); // Opens 04 April 2025

        Bukkit.getPluginManager().registerEvents(new PortalCreateListener(netherAllowedTime), plugin);
    }
}