package dev.memorydealer;

import dev.memorydealer.modules.chillzone.ChillZoneModule;
import dev.memorydealer.modules.endportal.EndPortalModule;
import dev.memorydealer.modules.netherportal.NetherPortalModule;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.LocalDateTime;

public final class TheMalteseNetrworkSMP extends JavaPlugin {

    private ChillZoneModule chillZoneModule;

    @Override
    public void onEnable() {
        chillZoneModule = new ChillZoneModule(this);
        NetherPortalModule netherPortalModule = new NetherPortalModule(this);
        EndPortalModule endPortalModule = new EndPortalModule(this);

        chillZoneModule.start();
        netherPortalModule.start();
        endPortalModule.start();

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
