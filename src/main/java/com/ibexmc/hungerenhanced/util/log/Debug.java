package com.ibexmc.hungerenhanced.util.log;

import com.ibexmc.hungerenhanced.HungerEnhanced;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

public class Debug {
    private HungerEnhanced instance;

    public Debug(HungerEnhanced instance) {
        this.instance = instance;
    }

    public void log(String className, String functionName, String message) {
        if (instance.getData().getDebug().get()) {
            ConsoleCommandSender consoleCommandSender = instance.getServer().getConsoleSender();
            consoleCommandSender.sendMessage(
                    ChatColor.translateAlternateColorCodes('&',
                            "&d[" + instance.getConfigData().getDisplayName() +
                                    ".Debug]&b[" + className +
                                    "." + functionName +
                                    "]&f " + message
                    )
            );
        }
    }
}
