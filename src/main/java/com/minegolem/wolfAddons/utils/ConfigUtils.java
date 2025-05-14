package com.minegolem.wolfAddons.utils;

import com.minegolem.wolfAddons.WolfAddons;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@UtilityClass
public class ConfigUtils {
    private static final WolfAddons plugin = WolfAddons.INSTANCE;

    public static String SELL_MESSAGE_RAW = plugin.getConfig().getString("sell-msg");

    public static String getCEInventoryName() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("CrazyEnchantments");

        if (plugin == null) return "0";

        File folder = plugin.getDataFolder();

        if (!folder.exists()) return "0";

        File targetFile = new File(folder, "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(targetFile);

        return config.getString("Settings.InvName");
    }

    public static YamlConfiguration getETypesConfig() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("CrazyEnchantments");

        if (plugin == null) return null;

        File folder = plugin.getDataFolder();

        if (!folder.exists()) return null;

        File targetFile = new File(folder, "Enchantments.yml");

        return YamlConfiguration.loadConfiguration(targetFile);
    }
}
