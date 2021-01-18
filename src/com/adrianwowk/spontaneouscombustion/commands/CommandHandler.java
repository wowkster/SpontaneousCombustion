package com.adrianwowk.spontaneouscombustion.commands;

import com.adrianwowk.spontaneouscombustion.SpontaneousCombustion;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

    SpontaneousCombustion instance;

    public CommandHandler(SpontaneousCombustion plugin){
        this.instance = plugin;
    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("combust")) {
            if (!sender.hasPermission("spontaneouscombustion.combust")) {
                sender.sendMessage(instance.translate("messages.no-permission"));
                return true;
            }

            if (args.length == 0){
                sender.sendMessage(instance.translate("messages.description"));
            } else if (args.length == 1) {
                //args is only player
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null){
                    // set fire for default ticks
                    int secs = instance.getConfig().getInt("default");
                    combustPlayer(target, secs);
                    sender.sendMessage(instance.translate("messages.success").replace("%TARGET%", args[0]).replace("%SECONDS%", String.valueOf(secs)));
                } else {
                    // target not found
                    sender.sendMessage(instance.translate("messages.invalid.unknown-target").replace("%TARGET%", args[0]));
                }
            } else if (args.length == 2){
                // args is player and ticks
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null){
                    // set fire for default ticks
                    try {
                        int secs = Integer.parseInt(args[1]);
                        if (secs > 0) {
                            combustPlayer(target, secs);
                            sender.sendMessage(instance.translate("messages.success").replace("%TARGET%", args[0]).replace("%SECONDS%", args[1]));
                        }
                    } catch (NumberFormatException e) {
                        sender.sendMessage(instance.translate("messages.invalid.not-int"));
                    }
                } else {
                    // target not found
                    sender.sendMessage(instance.translate("messages.invalid.unknown-target").replace("%TARGET%", args[0]));
                }
                // set fire for specified ticks
            } else {
                /*
                TO MANY ARGUMENTS
                 */
                sender.sendMessage(instance.translate("messages.invalid.to-many-args"));
            }

            return true;
        }

        return false;
    }

    public void combustPlayer(Player target, int seconds){
        int ticks = seconds * 20;
        target.setFireTicks(ticks);
    }
}

