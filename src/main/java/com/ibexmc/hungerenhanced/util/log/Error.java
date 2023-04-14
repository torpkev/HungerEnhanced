package com.ibexmc.hungerenhanced.util.log;

import com.ibexmc.hungerenhanced.HungerEnhanced;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

public class Error {

    private HungerEnhanced instance;

    public Error(HungerEnhanced instance) {
        this.instance = instance;
    }

    /**
     * Saves an error to the console
     * @param code Error code
     * @param className Class the error occurred in
     * @param functionName Function the error occurred in
     * @param summary Error summary
     * @param detail Error detail
     * @param severity Error severity
     * @param stackTraceElements Error Stack Trace
     */
    public void save(
            String code,
            String className,
            String functionName,
            String summary,
            String detail,
            ErrorSeverity severity,
            StackTraceElement[]
                    stackTraceElements
    ) {
        String severityString = severity.toString();
        switch (severity) {
            case INFO:
                severityString = ChatColor.LIGHT_PURPLE + "Info" + ChatColor.RESET;
                break;
            case WARN:
                severityString = ChatColor.GOLD + "Warning" + ChatColor.RESET;
                break;
            case URGENT:
                severityString = ChatColor.YELLOW + "Urgent" + ChatColor.RESET;
                break;
            case CRITICAL:
                severityString = ChatColor.RED + "Critical" + ChatColor.RESET;
                break;
        }


        StringBuilder stackTrace = new StringBuilder();
        if (stackTraceElements != null) {
            stackTrace.append(ChatColor.RED + "\n############################################################" + "\nStack Trace\n" + ChatColor.RED + "Please include this in any bug reports submitted!\n" + ChatColor.RED + "############################################################\n" + ChatColor.RESET);
            for (StackTraceElement element : stackTraceElements) {
                stackTrace.append(element.toString());
                stackTrace.append("\n");
            }
            stackTrace.append(ChatColor.RED + "############################################################" + ChatColor.RESET);
        }

        ConsoleCommandSender ccs = this.instance.getServer().getConsoleSender();
        ccs.sendMessage(
                ChatColor.RED + "[" + instance.getConfigData().getDisplayName() + " " + ChatColor.WHITE + "ERROR" + ChatColor.RED + "]\n" + ChatColor.RESET +
                        ChatColor.DARK_RED + "Error Code: " + ChatColor.WHITE + code + "\n" +
                        ChatColor.DARK_RED + "Source: " + ChatColor.WHITE + className + "\n" +
                        ChatColor.DARK_RED + "Function: " + ChatColor.WHITE + functionName + "\n" +
                        ChatColor.DARK_RED + "Severity: " + severityString + "\n" +
                        ChatColor.DARK_RED + "Summary: " + ChatColor.WHITE + summary + "\n" +
                        ChatColor.DARK_RED + "Detail: " + ChatColor.WHITE + detail + stackTrace
        );

    }
}
