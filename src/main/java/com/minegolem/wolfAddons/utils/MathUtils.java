package com.minegolem.wolfAddons.utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

@UtilityClass
public class MathUtils {

    public static double applyMoneyTax(double money, long playtime) {
        return money / (1 + (playtime / Math.pow(10, 6)));
    }

    public static double applyPriceTax(double price, double balance, long playtime) {
        return price * (.05 + (balance/100000000)) * ((double) 1 /(1+((double) playtime /1000000)));
    }

    public static double calculateTax(double balance, long playtime) {
        return 1 - (.05 + (balance / Math.pow(10, 8))) * ((double) 1 /(1+((double) playtime /1000000)));
    }

    public static long getPlaytime(Player player) {
        return player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20;
    }
}
