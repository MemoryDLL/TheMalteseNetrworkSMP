package dev.memorydealer;

import dev.memorydealer.listeners.NetherPortalCreateListener;
import dev.memorydealer.listeners.PreventEndPortalActivationListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;

public final class TheMalteseNetrworkSMP extends JavaPlugin {

    // Times when Nether and End portals become allowed
    private LocalDateTime netherAllowedTime;
    private LocalDateTime endAllowedTime;

    @Override
    public void onEnable() {
        // Instead of LocalDateTime.now(), we fix it to March 28th of a chosen year
        // For example: 2025-03-28 at 00:00. Adjust year/time as desired.
        LocalDateTime fixedDate = LocalDateTime.of(2025, 3, 28, 0, 0);

        // 1 week after the fixed date
        netherAllowedTime = fixedDate.plusWeeks(1);

        // 1 month after the fixed date
        endAllowedTime = fixedDate.plusMonths(1);

        // Register your listeners
        getServer().getPluginManager().registerEvents(
                new NetherPortalCreateListener(netherAllowedTime),
                this
        );
        getServer().getPluginManager().registerEvents(
                new PreventEndPortalActivationListener(endAllowedTime),
                this
        );

        getLogger().info("NoPortalsPlugin enabled! Using a fixed start time of "
                + fixedDate + ", Nether portals open in 1 week, End in 1 month.");
    }

    @Override
    public void onDisable() {
        getLogger().info("NoPortalsPlugin disabled!");
    }
}
