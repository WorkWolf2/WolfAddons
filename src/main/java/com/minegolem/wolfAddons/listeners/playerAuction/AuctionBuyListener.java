package com.minegolem.wolfAddons.listeners.playerAuction;

import com.Zrips.CMI.Containers.CMIUser;
import com.minegolem.wolfAddons.WolfAddons;
import com.minegolem.wolfAddons.utils.MathUtils;
import com.olziedev.playerauctions.api.auction.Auction;
import com.olziedev.playerauctions.api.events.auction.PlayerAuctionBuyEvent;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@RequiredArgsConstructor
public class AuctionBuyListener implements Listener {

    private final WolfAddons plugin;

    @EventHandler
    public void onAuctionBuyEvent(PlayerAuctionBuyEvent event) {
        Auction auction = event.getPlayerAuction();
        Player seller = auction.getAuctionPlayer().getPlayer();

        double price = auction.getBuyPrice(auction.getItemAmount());

        CMIUser userSeller = plugin.getCmi().getPlayerManager().getUser(seller);

        double sellerBalance = userSeller.getBalance();
        long sellerPlayTime = MathUtils.getPlaytime(seller);

        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
            plugin.getEcon().withdrawPlayer(seller, MathUtils.applyPriceTax(price, sellerBalance, sellerPlayTime));
        }, 10L);
    }
}
