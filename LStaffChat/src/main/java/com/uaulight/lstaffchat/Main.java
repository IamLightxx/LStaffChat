package com.uaulight.lstaffchat;

import com.uaulight.lstaffchat.commands.staffcmd;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {
    public static Main instance;

    public boolean hookedVault = false;

    public static Main getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        File config = new File(getDataFolder(), "config.yml");
        if (!config.exists())saveResource("config.yml", false);

        hookedVault = getConfig().getBoolean("staffchat.hook-vault");

        getCommand("staff").setExecutor((CommandExecutor)new staffcmd());
        Bukkit.getConsoleSender().sendMessage("");
        send("&aPlugin ativado com sucesso");
        send("&aAutor: uauLight");
        send("&aVersao: 1.0");
        Bukkit.getConsoleSender().sendMessage("");
    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("");
        send("&cPlugin desativado com sucesso");
        send("&cAutor: uauLight");
        send("&cVersao: 1.0");
        Bukkit.getConsoleSender().sendMessage("");
    }


    public void send(String msg) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&b[LStaffChat] &9> " + msg));
    }
}
