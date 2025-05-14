package com.minegolem.wolfAddons.data.dataManager;

import com.minegolem.wolfAddons.WolfAddons;
import com.minegolem.wolfAddons.data.PlayerData;
import com.minegolem.wolfAddons.utils.Logger;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class CacheManager {
    private final transient WolfAddons plugin;

    private final File cacheFile;
    private final FileConfiguration cacheConfig;
    @Getter
    private final Map<UUID, PlayerData> playerCache = new HashMap<>();

    public CacheManager(WolfAddons plugin) {
        this.plugin = plugin;

        this.cacheFile = new File(plugin.getDataFolder(), "player_cache.yml");
        if (!cacheFile.exists()) {
            try {
                cacheFile.createNewFile();
            } catch (IOException e) {
                Logger.log(Logger.LogLevel.ERROR, "Failed to create cache file!", e);
            }
        }

        this.cacheConfig = YamlConfiguration.loadConfiguration(this.cacheFile);
        loadCache();
    }

    public void addPlayerToCache(Player player) {
        PlayerData data = new PlayerData(player.getName());

        playerCache.put(player.getUniqueId(), data);
        savePlayerData(player.getUniqueId(), data);
    }

    private void savePlayerData(UUID uuid, PlayerData data) {
        cacheConfig.set("players." + uuid + ".name", data.player_name());
        saveConfig();
    }

    private void saveConfig() {
        try {
            cacheConfig.save(cacheFile);
        } catch (IOException e) {
            Logger.log(Logger.LogLevel.ERROR, "Failed to save cache file!", e);
        }
    }

    private void loadCache () {
        if (cacheConfig.contains("players")) {
            for (String key : Objects.requireNonNull(cacheConfig.getConfigurationSection("players")).getKeys(false)) {
                UUID uuid = UUID.fromString(key);
                String name = cacheConfig.getString("players." + key + ".name");
                int level = cacheConfig.getInt("players." + key + ".level");
                playerCache.put(uuid, new PlayerData(name));
            }
        }
    }
}

