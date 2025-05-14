package com.minegolem.wolfAddons.listeners.quickShop;

import com.Zrips.CMI.Containers.CMIUser;
import com.minegolem.wolfAddons.WolfAddons;
import com.minegolem.wolfAddons.utils.MathUtils;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.maxgamer.quickshop.api.event.ShopTaxEvent;

@RequiredArgsConstructor
public class QuickShopListener implements Listener {

    private final WolfAddons plugin;

    @EventHandler
    public void onShopTax(ShopTaxEvent event) {
        Player player = Bukkit.getPlayer(event.getUser());

        CMIUser user = plugin.getCmi().getPlayerManager().getUser(player);

        double playerBalance = user.getBalance();

        assert player != null;
        long playerPlayTime = MathUtils.getPlaytime(player);

        event.setTax(MathUtils.calculateTax(playerBalance, playerPlayTime));
    }
}
