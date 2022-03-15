package com.uaulight.lstaffchat.utils;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class ApiVault {

    public static String getPrefix(String player) {

        String prefix = "";
        try {
            RegisteredServiceProvider<Chat> chatclazz = Bukkit.getServicesManager().getRegistration(Chat.class);
            if (chatclazz != null) {
                Chat chat = chatclazz.getProvider();
                if (chat != null) {
                    try {
                        prefix = chat.getPlayerPrefix(Bukkit.getWorlds().get(0), player).replace("&",
                                "§");
                    } catch (Exception e) {
                        prefix = "";
                    }
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return prefix;

    }

}
