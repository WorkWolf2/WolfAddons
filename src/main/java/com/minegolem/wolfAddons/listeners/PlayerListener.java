package com.minegolem.wolfAddons.listeners;

import com.Zrips.CMI.Containers.CMIUser;
import com.minegolem.wolfAddons.WolfAddons;
import com.minegolem.wolfAddons.data.PlayerData;
import com.minegolem.wolfAddons.data.dataManager.CacheManager;
import com.minegolem.wolfAddons.utils.MathUtils;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public class PlayerListener implements Listener {

    private final transient WolfAddons plugin;
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        CacheManager cacheManager = plugin.getCache();
        Map<UUID, PlayerData> cache = cacheManager.getPlayerCache();

        if (cache.containsKey(player.getUniqueId())) return;

        CMIUser user = plugin.getCmi().getPlayerManager().getUser(player);

        double playerBalance = user.getBalance();

        if(playerBalance == 1000) return;

        long playerPlayTime = MathUtils.getPlaytime(player);

        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
            plugin.getEcon().withdrawPlayer(player, MathUtils.applyMoneyTax(playerBalance, playerPlayTime));
            cacheManager.addPlayerToCache(player);

            System.out.println("Sono stati rimossi: " + MathUtils.applyMoneyTax(playerBalance, playerPlayTime) + " a: " + player.getName());
        }, 10L);
    }
}
