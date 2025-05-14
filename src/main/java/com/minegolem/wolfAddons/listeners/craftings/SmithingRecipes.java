package com.minegolem.wolfAddons.listeners.craftings;

import com.jeff_media.morepersistentdatatypes.DataType;
import com.minegolem.wolfAddons.WolfAddons;
import io.th0rgal.oraxen.OraxenPlugin;
import io.th0rgal.oraxen.api.OraxenItems;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareSmithingEvent;
import org.bukkit.event.inventory.SmithItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.Event.Result;
import org.checkerframework.checker.units.qual.N;

@RequiredArgsConstructor
@Getter
public class SmithingRecipes implements Listener {

    private final transient WolfAddons plugin;

    private static final WolfAddons INSTANCE = WolfAddons.INSTANCE;

    private final ItemStack boneSwordIS = OraxenItems.getItemById("bone_sword").build();
    private final ItemStack boneShovelIS = OraxenItems.getItemById("bone_shovel").build();
    private final ItemStack bonePickaxeIS = OraxenItems.getItemById("bone_pickaxe").build();
    private final ItemStack boneAxeIS = OraxenItems.getItemById("bone_axe").build();
    private final ItemStack boneHoeIS = OraxenItems.getItemById("bone_hoe").build();
    private final ItemStack boneHelmetIS = OraxenItems.getItemById("bone_helmet").build();
    private final ItemStack boneChestplateIS = OraxenItems.getItemById("bone_chestplate").build();
    private final ItemStack boneLeggingsIS = OraxenItems.getItemById("bone_leggings").build();
    private final ItemStack boneBootsIS = OraxenItems.getItemById("bone_boots").build();

    private final ItemStack upgradeTemplate = new ItemStack(Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE);
    private final ItemStack cristalloOsseoIS = OraxenItems.getItemById("cristallo_osseo").build();

    @SuppressWarnings("Duplicates")
    @EventHandler
    public void onPrepareSmithing(PrepareSmithingEvent event) {
        final var smithingInventory = event.getInventory();

        final ItemStack templateSlot = smithingInventory.getItem(0);
        final ItemStack itemToTransformSlot = smithingInventory.getItem(1);
        final ItemStack mineralSlot = smithingInventory.getItem(2);

        if (templateSlot == null || itemToTransformSlot == null || mineralSlot == null) return;

        if (!templateSlot.getType().equals(upgradeTemplate.getType())) return;
        if (!mineralSlot.getType().equals(cristalloOsseoIS.getType())) return;

        if (!OraxenItems.exists(mineralSlot)) return;

        switch (itemToTransformSlot.getType()) {
            case NETHERITE_SWORD -> event.setResult(boneSwordIS);
            case NETHERITE_SHOVEL -> event.setResult(boneShovelIS);
            case NETHERITE_PICKAXE -> event.setResult(bonePickaxeIS);
            case NETHERITE_AXE -> event.setResult(boneAxeIS);
            case NETHERITE_HOE -> event.setResult(boneHoeIS);
            case NETHERITE_HELMET -> event.setResult(boneHelmetIS);
            case NETHERITE_CHESTPLATE -> event.setResult(boneChestplateIS);
            case NETHERITE_LEGGINGS -> event.setResult(boneLeggingsIS);
            case NETHERITE_BOOTS -> event.setResult(boneBootsIS);
            default -> event.setResult(new ItemStack(Material.AIR));
        }
    }

    @SuppressWarnings("Duplicates")
    @EventHandler
    public void onSmithing(SmithItemEvent event) {
        final var smithingInventory = event.getInventory();

        final ItemStack templateSlot = smithingInventory.getItem(0);
        final ItemStack itemToTransformSlot = smithingInventory.getItem(1);
        final ItemStack mineralSlot = smithingInventory.getItem(2);

        if (templateSlot == null || itemToTransformSlot == null || mineralSlot == null) return;

        if (!templateSlot.getType().equals(upgradeTemplate.getType())) return;
        if (!mineralSlot.getType().equals(cristalloOsseoIS.getType())) return;

        if (!OraxenItems.exists(mineralSlot)) return;

        switch (itemToTransformSlot.getType()) {
            case NETHERITE_SWORD -> event.getInventory().setResult(boneSwordIS);
            case NETHERITE_SHOVEL -> event.getInventory().setResult(boneShovelIS);
            case NETHERITE_PICKAXE -> event.getInventory().setResult(bonePickaxeIS);
            case NETHERITE_AXE -> event.getInventory().setResult(boneAxeIS);
            case NETHERITE_HOE -> event.getInventory().setResult(boneHoeIS);
            case NETHERITE_HELMET -> event.getInventory().setResult(boneHelmetIS);
            case NETHERITE_CHESTPLATE -> event.getInventory().setResult(boneChestplateIS);
            case NETHERITE_LEGGINGS -> event.getInventory().setResult(boneLeggingsIS);
            case NETHERITE_BOOTS -> event.getInventory().setResult(boneBootsIS);
            default -> event.getInventory().setResult(new ItemStack(Material.AIR));
        }
    }
}
