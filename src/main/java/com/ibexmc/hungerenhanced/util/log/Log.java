package com.ibexmc.hungerenhanced.util.log;

import com.ibexmc.hungerenhanced.HungerEnhanced;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

public class Log {

    private HungerEnhanced instance;

    public Log(HungerEnhanced instance) {
        this.instance = instance;
    }

    /**
     * Logs to the console
     * @param className Class Name
     * @param functionName Function Name
     * @param message Message to log
     */
    public void log(String className, String functionName, String message) {
        ConsoleCommandSender consoleCommandSender = instance.getServer().getConsoleSender();
        consoleCommandSender.sendMessage(
                ChatColor.translateAlternateColorCodes('&',
                        "&7[" + instance.getConfigData().getDisplayName() +
                                ".Debug]&b[" + className +
                                "." + functionName +
                                "]&f " + message
                )
        );
    }

    /**
     * Logs to the console
     * @param message Message to log
     */
    public void log(String message) {
        ConsoleCommandSender consoleCommandSender = instance.getServer().getConsoleSender();
        consoleCommandSender.sendMessage(
                ChatColor.translateAlternateColorCodes('&',
                        message
                )
        );
    }
}
