package com.minegolem.wolfAddons.wolfafo.commands;

import com.jeff_media.morepersistentdatatypes.DataType;
import com.jeff_media.morepersistentdatatypes.datatypes.UuidDataType;
import com.minegolem.wolfAddons.WolfAddons;
import com.minegolem.wolfAddons.utils.ColorUtils;
import io.th0rgal.oraxen.api.OraxenItems;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WolfAFOCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (!sender.hasPermission("wolfaddons.wolfafo.give")) return true;

        if (args.length != 2) {
            sender.sendMessage("§cUtilizzo corretto: /" + s + " <item_id> <player>");
            return false;
        }

        String itemId = args[0];
        String playerName = args[1];

        if (!OraxenItems.exists(itemId)) {
            sender.sendMessage("§cL'item con ID '" + itemId + "' non esiste.");
            return false;
        }

        ItemStack oraxenItem = OraxenItems.getItemById(itemId).build();

        Player target = Bukkit.getPlayerExact(playerName);
        if (target == null || !target.isOnline()) {
            sender.sendMessage("§cIl giocatore '" + playerName + "' non è online.");
            return false;
        }

        ItemMeta meta = oraxenItem.getItemMeta();

        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        pdc.set(WolfAddons.INSTANCE.getPLAYER_DATA_KEY(), DataType.UUID, target.getUniqueId());
        pdc.set(WolfAddons.INSTANCE.getAFO_DATA_KEY(), PersistentDataType.BOOLEAN, true);

        List<Component> lore = meta.lore();

        assert lore != null;
        lore.add(ColorUtils.colorize("<!i><gray>Proprieta' di</gray><white> <bold>" + target.getName()));

        meta.lore(lore);

        oraxenItem.setItemMeta(meta);

        target.getInventory().addItem(oraxenItem);
        return true;
    }
}
