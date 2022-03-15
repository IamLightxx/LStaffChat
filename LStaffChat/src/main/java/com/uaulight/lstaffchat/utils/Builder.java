package com.uaulight.lstaffchat.utils;

import com.uaulight.lstaffchat.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Builder {
    public String prefix;

    public String name;

    public String msg;

    public Builder(Player pl) {
        this.name = pl.getName();
        if ((Main.getInstance()).hookedVault) {
            this.prefix = ApiVault.getPrefix(pl.getName());
            this.msg = ChatColor.translateAlternateColorCodes('&',
                    Main.getInstance().getConfig().getString("staffchat.formato").replace("%prefixo%", this.prefix).replace("%staff%", this.name));
        } else {
            this.prefix = "Vault not hooked";
            this.msg = ChatColor.translateAlternateColorCodes('&',
                    Main.getInstance().getConfig().getString("staffchat.formato").replace("%staff%", this.name));
        }
    }

    public String getMsg() {
        return this.msg;
    }
}