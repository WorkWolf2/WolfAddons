package com.minegolem.wolfAddons.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class ColorUtils {

    private static final MiniMessage mm = MiniMessage.miniMessage();

    public static Component colorize(String string) {
        return mm.deserialize(string);
    }

    public static Component colorizeSellMsg(String text, Component player_name, Component itemName, long amount, double price) {
        return colorize(text.replace("<player>", mm.serialize(player_name))
                .replace("<item>", mm.serialize(itemName))
                .replace("<amount>", String.valueOf(amount))
                .replace("<price>", String.valueOf(price))
                );
    }
}
