package com.minegolem.wolfAddons.listeners.nfcNotes;

import com.Zrips.CMI.Containers.CMIUser;
import com.minegolem.wolfAddons.WolfAddons;
import com.minegolem.wolfAddons.utils.MathUtils;
import es.kikisito.nfcnotes.events.DepositEvent;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@RequiredArgsConstructor
public class NFCNotesListener implements Listener {

    private final transient WolfAddons plugin;

    @EventHandler
    public void onReedeme(DepositEvent event) {
        Player reedemer = event.getPlayer();
        double money = event.getMoney();

        CMIUser userReedemer = plugin.getCmi().getPlayerManager().getUser(reedemer);

        double reedemerBalance = userReedemer.getBalance();
        long reedemerPlayTime = MathUtils.getPlaytime(reedemer);

        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
            plugin.getEcon().withdrawPlayer(reedemer, MathUtils.applyPriceTax(money, reedemerBalance, reedemerPlayTime));
        }, 10L);

    }
}
