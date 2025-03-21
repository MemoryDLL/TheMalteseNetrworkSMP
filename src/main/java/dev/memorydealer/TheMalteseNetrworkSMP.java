package dev.memorydealer;

import dev.memorydealer.listeners.NetherPortalCreateListener;
import dev.memorydealer.listeners.PreventEndPortalActivationListener;
import dev.memorydealer.modules.chillzone.ChillZoneModule;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;

public final class TheMalteseNetrworkSMP extends JavaPlugin {

    // Times when Nether and End portals become allowed
    private LocalDateTime netherAllowedTime;
    private LocalDateTime endAllowedTime;

    //modules

    private ChillZoneModule chillZoneModule;

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

        //start modules
        chillZoneModule = new ChillZoneModule(this);
        chillZoneModule.start();

        getLogger().info("TheMalteseNetworkSMP plugin by MemoryDLL is enabled!");

    }

    @Override
    public void onDisable() {
        if (chillZoneModule != null) {
            chillZoneModule.stop();
        }

        getLogger().info("TheMalteseNetworkSMP plugin by MemoryDLL is disabled!");
    }
}
