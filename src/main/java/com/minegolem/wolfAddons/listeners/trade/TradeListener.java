package com.minegolem.wolfAddons.listeners.trade;

import com.minegolem.wolfAddons.WolfAddons;
import com.minegolem.wolfAddons.utils.MathUtils;
import de.codingair.tradesystem.spigot.events.TradeReceiveEconomyEvent;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class TradeListener implements Listener {

    private final WolfAddons plugin;

    @EventHandler
    public void onMoneyTrade(TradeReceiveEconomyEvent event) {
        Player receiver = event.getReceiver();

        BigDecimal moneyTransferredBidDecimal = event.getBalance();
        double moneyTransferred = moneyTransferredBidDecimal.doubleValue();

        double receiverBalance = plugin.getCmi().getPlayerManager().getUser(receiver).getBalance();
        long receiverPlaytime = MathUtils.getPlaytime(receiver);

        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
            plugin.getEcon().withdrawPlayer(receiver, MathUtils.applyPriceTax(moneyTransferred, receiverBalance, receiverPlaytime));
        }, 10L);
    }
}
