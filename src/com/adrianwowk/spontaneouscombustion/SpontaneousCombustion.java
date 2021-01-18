package com.adrianwowk.spontaneouscombustion;

import com.adrianwowk.spontaneouscombustion.commands.CommandHandler;
import com.adrianwowk.spontaneouscombustion.commands.SCTabCompleter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class SpontaneousCombustion extends JavaPlugin {
    Server server;
    ConsoleCommandSender console;

    public SpontaneousCombustion() {
        this.server = Bukkit.getServer();
        this.console = this.server.getConsoleSender();
    }

    public void onEnable() {
        // Register command tab completer and executer

        saveDefaultConfig();

        getCommand("combust").setTabCompleter(new SCTabCompleter());
        getCommand("combust").setExecutor(new CommandHandler(this));

        Metrics metrics = new Metrics(this, 10040);

        // Server Console Message
        console.sendMessage(getPrefix() + "Successfully Enabled :)");
    }
    public void onDisable(){
        console.sendMessage(getPrefix() + "Successfully Disabled :)");
    }

    public String getPrefix() {
        return ChatColor.translateAlternateColorCodes('&', this.getConfig().getString("messages.prefix"));
    }

    public String translate(String path) {
        return getPrefix() + ChatColor.translateAlternateColorCodes('&', this.getConfig().getString(path));
    }
}
