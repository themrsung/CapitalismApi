package jbs.capitalismapi;

import jbs.capitalismapi.types.entities.EconomicEntityApi;
import jbs.capitalismapi.types.entities.players.PlayerApi;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.UUID;

public final class CapitalismApi extends JavaPlugin {

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
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
