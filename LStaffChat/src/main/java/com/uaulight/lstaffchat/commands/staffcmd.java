package com.uaulight.lstaffchat.commands;

import com.uaulight.lstaffchat.Main;
import com.uaulight.lstaffchat.utils.Builder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class staffcmd implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (Main.getInstance().getConfig().getBoolean("staffchat.ativado")) {
                Player p = (Player)sender;
                for (Player pls : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission(Main.getInstance().getConfig().getString("staffchat.permissao"))) {
                        if (pls.hasPermission(Main.getInstance().getConfig().getString("staffchat.permissao"))) {
                            if (Main.getInstance().getConfig().getBoolean("staffchat.hook-vault")) {
                                if (args.length == 0) {
                                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("staffchat.formato-errado")));
                                    continue;
                                }
                                String str1 = String.join(" ", (CharSequence[])args);
                                Builder builder1 = new Builder(p);
                                String str2 = builder1.getMsg().replace("%msg%", str1);
                                if (Main.getInstance().getConfig().getBoolean("staffchat.cores")) {
                                    if (p.hasPermission(Main.getInstance().getConfig().getString("staffchat.permissao-cores"))) {
                                        if (Main.getInstance().getConfig().getBoolean("staffchat.destaque")) {
                                            if (p.hasPermission(Main.getInstance().getConfig().getString("staffchat.permissao-destaque")))
                                                pls.sendMessage("\n" + ChatColor.translateAlternateColorCodes('&', str2) + "\n \n");
                                            continue;
                                        }
                                        pls.sendMessage(ChatColor.translateAlternateColorCodes('&', str2));
                                        continue;
                                    }
                                    pls.sendMessage(str2);
                                    continue;
                                }
                                if (Main.getInstance().getConfig().getBoolean("staffchat.destaque")) {
                                    if (p.hasPermission(Main.getInstance().getConfig().getString("staffchat.permissao-destaque"))) {
                                        pls.sendMessage("\n" + str2 + "\n \n");
                                        continue;
                                    }
                                    pls.sendMessage(str2);
                                    continue;
                                }
                                pls.sendMessage(str2);
                                continue;
                            }
                            if (args.length == 0) {
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("staffchat.formato-errado")));
                                continue;
                            }
                            String msgg = String.join(" ", (CharSequence[])args);
                            Builder builder = new Builder(p);
                            String msg = builder.getMsg().replace("%msg%", msgg);
                            if (Main.getInstance().getConfig().getBoolean("staffchat.cores")) {
                                if (p.hasPermission(Main.getInstance().getConfig().getString("staffchat.permissao-cores"))) {
                                    if (Main.getInstance().getConfig().getBoolean("staffchat.destaque")) {
                                        if (p.hasPermission(Main.getInstance().getConfig().getString("staffchat.permissao-destaque")))
                                            pls.sendMessage("\n" + ChatColor.translateAlternateColorCodes('&', msg) + "\n \n");
                                        continue;
                                    }
                                    pls.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                                    continue;
                                }
                                pls.sendMessage(msg);
                                continue;
                            }
                            if (Main.getInstance().getConfig().getBoolean("staffchat.destaque")) {
                                if (p.hasPermission(Main.getInstance().getConfig().getString("staffchat.permissao-destaque"))) {
                                    pls.sendMessage("\n" + msg + "\n \n");
                                    continue;
                                }
                                pls.sendMessage(msg);
                                continue;
                            }
                            pls.sendMessage(msg);
                        }
                        continue;
                    }
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("staffchat.sem-permissao")));
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("staffchat.desativado")));
            }
        } else {
            sender.sendMessage(Main.getInstance().getConfig().getString("staffchat.console"));
        }
        return true;
    }
}