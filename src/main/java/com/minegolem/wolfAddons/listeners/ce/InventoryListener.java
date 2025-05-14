package com.minegolem.wolfAddons.listeners.ce;

import com.minegolem.wolfAddons.WolfAddons;
import com.minegolem.wolfAddons.utils.ColorUtils;
import com.minegolem.wolfAddons.utils.ConfigUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class InventoryListener implements Listener{

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        String targetName = ConfigUtils.getCEInventoryName().replace('&', '§');
        if (!event.getView().getTitle().equals(targetName)) return;

        Inventory inventory = event.getInventory();

        ItemStack infoIS = new ItemStack(Material.COMPASS);
        ItemMeta infoIM = infoIS.getItemMeta();

        infoIM.displayName(ColorUtils.colorize("<!i><bold><aqua>Info</aqua>"));

        List<Component> lore = new ArrayList<>();

        lore.add(ColorUtils.colorize("<!i><gray>Clicca per vedere tutti i tipi di enchant!</gray>"));

        infoIM.lore(lore);
        infoIS.setItemMeta(infoIM);

        inventory.setItem(0, infoIS);
    }
}
