package jbs.capitalismapi;

import jbs.capitalismapi.io.ApiAutoSaver;
import jbs.capitalismapi.io.ApiData;
import jbs.capitalismapi.io.ApiDataInitializer;
import jbs.capitalismapi.types.entities.EconomicEntityApi;
import jbs.capitalismapi.types.entities.players.PlayerApi;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.UUID;

public final class CapitalismApi extends JavaPlugin {
    public static String version = "5.0";

    ArrayList<EconomicEntityApi> entities = new ArrayList<EconomicEntityApi>();
    public ArrayList<EconomicEntityApi> getEntities() {
        return entities;
    }

    @Nullable
    public EconomicEntityApi getEntity(UUID uuid) {
        for (EconomicEntityApi eea : entities) {
            if (eea.getUuid().equals(uuid)) return eea;
        }

        return null;
    }
    @Nullable
    public PlayerApi getPlayer(UUID uuid) {
        EconomicEntityApi entity = getEntity(uuid);

        if (entity != null) {
            if (entity instanceof PlayerApi) {
                return (PlayerApi) entity;
            }
        }

        return null;
    }

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("CapitalismApi v" + CapitalismApi.version + " is loading...");

        loadData();

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new ApiAutoSaver(this), 20, 20);

        Bukkit.getLogger().info("CapitalismApi v" + CapitalismApi.version + " is loaded!");

    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("CapitalismApi v" + CapitalismApi.version + " is shutting down.");
        saveData();
    }

    public void loadData() {
        ApiData ad = ApiData.loadData("plugins/CapitalismApi");
        ApiDataInitializer adi = new ApiDataInitializer(this);

        if (ad.entities.size() > 0) {
            this.entities = ad.entities;
            adi.initializeEntities(ad.entityData);
        }
    }

    public boolean saveData() {
        ApiData ad = new ApiData(
                entities
        );

        return ad.saveData("plugins/CapitalismApi");
    }

}
