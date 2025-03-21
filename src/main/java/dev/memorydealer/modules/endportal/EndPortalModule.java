package dev.memorydealer.modules.endportal;

import dev.memorydealer.modules.endportal.listeners.PreventEndPortalActivationListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.time.LocalDateTime;

public class EndPortalModule {

    private final Plugin plugin;

    public EndPortalModule(Plugin plugin) {
        this.plugin = plugin;
    }

    public void start() {
        LocalDateTime baseline = LocalDateTime.of(2025, 3, 28, 0, 0);
        LocalDateTime endAllowedTime = baseline.plusMonths(1); // Opens 28 April 2025

        Bukkit.getPluginManager().registerEvents(new PreventEndPortalActivationListener(endAllowedTime), plugin);
    }
}