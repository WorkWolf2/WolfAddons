package com.minegolem.wolfAddons.wolfafo.listeners;

import com.Zrips.CMI.events.CMIArmorChangeEvent;
import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import com.jeff_media.morepersistentdatatypes.DataType;
import com.minegolem.wolfAddons.WolfAddons;
import com.minegolem.wolfAddons.utils.ColorUtils;
import io.th0rgal.oraxen.utils.armorequipevent.ArmorEquipEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.Objects;
import java.util.UUID;

public class ArmorCheckPlayerListener implements Listener {

    @EventHandler
    public void onArmorCheck(ArmorEquipEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        ItemStack item = event.getNewArmorPiece();

        if (item == null) return;
        if (item.getItemMeta() == null) return;
        PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();

        UUID uuid = pdc.get(WolfAddons.INSTANCE.getPLAYER_DATA_KEY(), DataType.UUID);

        if (!playerUUID.equals(uuid) && pdc.has(WolfAddons.INSTANCE.getAFO_DATA_KEY())) {
            event.setCancelled(true);

            player.sendMessage(ColorUtils.colorize("<bold><red>ᴀᴛᴛᴇɴᴢɪᴏɴᴇ</red></bold> <dark_gray>|</dark_gray> <gray>Non puoi equipaggiare questa armatura!</gray>"));
        }
    }
}
