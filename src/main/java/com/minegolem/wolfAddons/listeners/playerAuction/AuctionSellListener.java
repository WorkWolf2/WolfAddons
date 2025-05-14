package com.minegolem.wolfAddons.listeners.playerAuction;

import com.minegolem.wolfAddons.WolfAddons;
import com.minegolem.wolfAddons.utils.ColorUtils;
import com.minegolem.wolfAddons.utils.ConfigUtils;
import com.olziedev.playerauctions.api.auction.Auction;
import com.olziedev.playerauctions.api.events.auction.PlayerAuctionSellEvent;
import io.th0rgal.oraxen.api.OraxenItems;
import io.th0rgal.oraxen.config.ConfigsManager;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

@RequiredArgsConstructor
public class AuctionSellListener implements Listener {

    private final transient WolfAddons plugin;

    @EventHandler
    public void onAuctionSell(PlayerAuctionSellEvent event) {
        Auction auction = event.getPlayerAuction();
        Player player = auction.getAuctionPlayer().getPlayer();

        ItemStack itemStack = auction.getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (OraxenItems.exists(itemStack) || itemMeta.hasDisplayName()) {
            plugin.getServer().broadcast(ColorUtils.colorizeSellMsg(
                            ConfigUtils.SELL_MESSAGE_RAW,
                            player.displayName(),
                            itemStack.displayName(),
                            auction.getItemAmount(),
                            auction.getPrice()
                    )
            );
        }
        else {
            plugin.getServer().broadcast(ColorUtils.colorizeSellMsg(
                            ConfigUtils.SELL_MESSAGE_RAW,
                            player.displayName(),
                            Component.text(auction.getItem().getType().name()),
                            auction.getItemAmount(),
                            auction.getPrice()
                    )
            );
        }


    }
}
