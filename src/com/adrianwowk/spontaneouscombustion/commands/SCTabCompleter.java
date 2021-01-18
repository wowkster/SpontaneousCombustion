package com.adrianwowk.spontaneouscombustion.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SCTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {

        List<String> list = new ArrayList<>();

        if (cmd.getName().equalsIgnoreCase("combust")) {

            if (sender.hasPermission("spontaneouscombustion.combust") && args.length <= 1) {
                // Get item in hand and display enchantments
                for (Player player : Bukkit.getOnlinePlayers())
                    list.add(player.getDisplayName());
            }
        }
        return list;
    }
}