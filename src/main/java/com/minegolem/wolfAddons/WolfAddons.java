package com.minegolem.wolfAddons;


import com.Zrips.CMI.CMI;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ConnectionSide;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.minegolem.wolfAddons.craftings.skeletonDungeon.SkeletonCraftings;
import com.minegolem.wolfAddons.data.dataManager.CacheManager;
import com.minegolem.wolfAddons.listeners.PlayerListener;
import com.minegolem.wolfAddons.listeners.ce.InventoryListener;
import com.minegolem.wolfAddons.listeners.craftings.SmithingRecipes;
import com.minegolem.wolfAddons.listeners.nfcNotes.NFCNotesListener;
import com.minegolem.wolfAddons.listeners.playerAuction.AuctionBuyListener;
import com.minegolem.wolfAddons.listeners.playerAuction.AuctionSellListener;
import com.minegolem.wolfAddons.listeners.quickShop.QuickShopListener;
import com.minegolem.wolfAddons.listeners.trade.TradeListener;
import com.minegolem.wolfAddons.utils.Logger;
import com.minegolem.wolfAddons.wolfafo.commands.WolfAFOCommand;
import com.minegolem.wolfAddons.wolfafo.listeners.ArmorCheckPlayerListener;
import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.ViaAPI;
import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.SmithingTransformRecipe;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;
import java.util.Objects;

@Getter
public final class WolfAddons extends JavaPlugin {

    public static WolfAddons INSTANCE = null;

    public NamespacedKey PLAYER_DATA_KEY = new NamespacedKey(this, "player_uuid");
    public NamespacedKey AFO_DATA_KEY = new NamespacedKey(this, "afo_data_key");

    private Economy econ = null;
    private CMI cmi = null;
    private CacheManager cache = null;
    private ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        Logger.log(Logger.LogLevel.INFO, "WOLF Addons v" + this.getPluginMeta().getVersion());
        Logger.log(Logger.LogLevel.OUTLINE, " ");
        saveDefaultConfig();

        INSTANCE = this;
        cache = new CacheManager(this);
        protocolManager = ProtocolLibrary.getProtocolManager();

        Logger.log(Logger.LogLevel.SUCCESS, "Config loaded!");
        Logger.log(Logger.LogLevel.INFO, "Loading recipes...");
        Logger.log(Logger.LogLevel.OUTLINE, " ");

        List<SmithingTransformRecipe> armorSmithingRecipes = SkeletonCraftings.getArmorSmithingRecipes();
        List<SmithingTransformRecipe> weaponsSmithingRecipes = SkeletonCraftings.getWeaponsSmithingRecipes();
        List<ShapedRecipe> itemsShapedRecipes = SkeletonCraftings.getItemsShapedRecipes();

        SkeletonCraftings.getArmorSmithingRecipes().forEach(recipe -> {
            Bukkit.addRecipe(recipe);
            Logger.log(Logger.LogLevel.INFO, "Registered recipe: " + recipe.getKey());
        });

        SkeletonCraftings.getWeaponsSmithingRecipes().forEach(recipe -> {
            Bukkit.addRecipe(recipe);
            Logger.log(Logger.LogLevel.INFO, "Registered recipe: " + recipe.getKey());
        });

        SkeletonCraftings.getItemsShapedRecipes().forEach(recipe -> {
            Bukkit.addRecipe(recipe);
            Logger.log(Logger.LogLevel.INFO, "Registered recipe: " + recipe.getKey());
        });

        Logger.log(Logger.LogLevel.OUTLINE, " ");
        Logger.log(Logger.LogLevel.SUCCESS, "Recipes loaded!");
        Logger.log(Logger.LogLevel.OUTLINE, " ");

        getServer().getPluginManager().registerEvents(new InventoryListener(), this);

        if (getServer().getPluginManager().getPlugin("Oraxen") != null) {
            Logger.log(Logger.LogLevel.INFO, "Oraxen found | Hooked into it.");

            getServer().getPluginManager().registerEvents(new SmithingRecipes(this), this);
            Objects.requireNonNull(getCommand("afogive")).setExecutor(new WolfAFOCommand());
            getServer().getPluginManager().registerEvents(new ArmorCheckPlayerListener(), this);
        }

        if (getServer().getPluginManager().getPlugin("ProtocolLib") != null) {
            Logger.log(Logger.LogLevel.INFO, "ProtocolLib found | Hooked into it.");

        }

        if (setupEconomy()) {
            getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        }

        Logger.log(Logger.LogLevel.INFO, "Loading protocols...!");

        //SHOP TAX

        if (getServer().getPluginManager().getPlugin("PlayerAuctions") != null) {
            Logger.log(Logger.LogLevel.INFO, "PlayerAuctions found | Hooked into it.");

            getServer().getPluginManager().registerEvents(new AuctionSellListener(this), this);
        }

        if (getServer().getPluginManager().getPlugin("NFCNotes") != null) {
            Logger.log(Logger.LogLevel.INFO, "NFCNotes found | Hooked into it.");

            getServer().getPluginManager().registerEvents(new NFCNotesListener(this), this);
        }

        if (getServer().getPluginManager().getPlugin("CMI") != null) {
            Logger.log(Logger.LogLevel.INFO, "CMI found | Hooked into it.");

            this.cmi = ((CMI) Bukkit.getPluginManager().getPlugin("CMI"));

            getServer().getPluginManager().registerEvents(new AuctionBuyListener(this), this);
        }

        if (getServer().getPluginManager().getPlugin("TradeSystem") != null) {
            Logger.log(Logger.LogLevel.INFO, "TradeSystem found | Hooked into it.");
            getServer().getPluginManager().registerEvents(new TradeListener(this), this);
        }

        if (getServer().getPluginManager().getPlugin("QuickShop") != null) {
            Logger.log(Logger.LogLevel.INFO, "QuickShop found | Hooked into it.");

            getServer().getPluginManager().registerEvents(new QuickShopListener(this), this);
        }



    }

    @Override
    public void onDisable() {
        Logger.log(Logger.LogLevel.INFO, "WOLF Addons v" + this.getPluginMeta().getVersion());

        Logger.log(Logger.LogLevel.INFO, "Unloading recipes...");
        Objects.requireNonNull(SkeletonCraftings.getArmorSmithingRecipes()).forEach(smithingTransformRecipe -> getServer().removeRecipe(smithingTransformRecipe.getKey()));
        Objects.requireNonNull(SkeletonCraftings.getWeaponsSmithingRecipes()).forEach(smithingTransformRecipe -> getServer().removeRecipe(smithingTransformRecipe.getKey()));
        Objects.requireNonNull(SkeletonCraftings.getItemsShapedRecipes()).forEach(itemShapedRecipe -> getServer().removeRecipe(itemShapedRecipe.getKey()));
        Logger.log(Logger.LogLevel.SUCCESS, "Recipes unloaded!");
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);

        if (rsp == null) {
            Logger.log(Logger.LogLevel.WARNING, "If you are using CMI try to install CMIEInjector or use the recompiled version");
            Logger.log(Logger.LogLevel.WARNING, "of Vault that support CMI Economy.");
            return false;
        }

        this.econ = rsp.getProvider();

        return true;
    }
}
