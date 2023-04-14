package com.ibexmc.hungerenhanced.util;

import com.ibexmc.hungerenhanced.HungerEnhanced;
import com.ibexmc.hungerenhanced.util.generic.ColorFunctions;
import com.ibexmc.hungerenhanced.util.generic.StringFunctions;
import com.ibexmc.hungerenhanced.util.log.Error;
import com.ibexmc.hungerenhanced.util.log.ErrorSeverity;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Message {
    
    private final HungerEnhanced instance;
    private final Error error;

    public Message(HungerEnhanced instance) {
        this.instance = instance;
        this.error = new Error(instance);
    }

    /**
     * Sends a message to the player
     * @param player Player
     * @param messageCode Message code
     * @param message Message
     * @param placeHolders Placeholders
     * @param includeHeader If true, include the plugin display name
     */
    public void send(Player player, String messageCode, String message, Map<String, String> placeHolders, boolean includeHeader) {
        try {
            if (placeHolders == null) {
                placeHolders = new HashMap<>();
            }
            if (player != null) {
                if (Bukkit.getPlayer(player.getUniqueId()) != null) {
                    Locale locale = new Locale(instance);
                    if (messageCode.equalsIgnoreCase("na")) {
                        message = locale.localeText(messageCode, message, placeHolders);
                    }
                    if (!StringFunctions.isNullOrEmpty(message)) {
                        if (includeHeader) {
                            message = instance.getConfigData().getDisplayName() + "&r " + message;
                        }
                        player.sendMessage(ColorFunctions.color(message));
                    } else {
                        error.save(
                                "Message.send.004",
                                "Message",
                                "send(Player)",
                                "Unexpected Error",
                                "Message not found - message code: " + messageCode + " message: " + message,
                                ErrorSeverity.WARN,
                                null
                        );
                    }
                } else {
                    error.save(
                            "Message.send.003",
                            "Message",
                            "send(Player)",
                            "Unexpected Error",
                            "Player is not found",
                            ErrorSeverity.WARN,
                            null
                    );
                }
            } else {
                error.save(
                        "Message.send.002",
                        "Message",
                        "send(Player)",
                        "Unexpected Error",
                        "Player is null",
                        ErrorSeverity.CRITICAL,
                        null
                );
            }
        } catch (Exception ex) {
            error.save(
                    "Message.send.001",
                    "Message",
                    "send(Player)",
                    "Unexpected Error",
                    ex.getMessage(),
                    ErrorSeverity.CRITICAL,
                    ex.getStackTrace()
            );
        }
    }

    /**
     * Sends a message to the command sender
     * @param sender Command Sender
     * @param messageCode Message code
     * @param message Message
     * @param placeHolders Placeholders
     * @param includeHeader If true, include the plugin display name
     */
    public void send(CommandSender sender, String messageCode, String message, Map<String, String> placeHolders, boolean includeHeader) {
        try {
            if (placeHolders == null) {
                placeHolders = new HashMap<>();
            }
            if (sender != null) {
                if (sender instanceof Player) {
                    // Sending to player
                    this.send (
                            (Player) sender,
                            messageCode,
                            message,
                            placeHolders,
                            includeHeader
                    );
                } else {
                    // Sending to console
                    Locale locale = new Locale(instance);
                    if (messageCode.equalsIgnoreCase("na")) {
                        message = locale.localeText(messageCode, message, placeHolders);
                    }
                    if (!StringFunctions.isNullOrEmpty(message)) {
                        if (includeHeader) {
                            message = instance.getConfigData().getDisplayName() + "&r " + message;
                        }
                        sender.sendMessage(ColorFunctions.color(message));
                    } else {
                        error.save(
                                "Message.send.003",
                                "Message",
                                "send(CommandSender)",
                                "Unexpected Error",
                                "Message not found - message code: " + messageCode + " message: " + message,
                                ErrorSeverity.WARN,
                                null
                        );
                    }
                }
            } else {
                error.save(
                        "Message.send.002",
                        "Message",
                        "send(CommandSender)",
                        "Unexpected Error",
                        "CommandSender is null",
                        ErrorSeverity.CRITICAL,
                        null
                );
            }
        } catch (Exception ex) {
            error.save(
                    "Message.send.001",
                    "Message",
                    "send(CommandSender)",
                    "Unexpected Error",
                    ex.getMessage(),
                    ErrorSeverity.CRITICAL,
                    ex.getStackTrace()
            );
        }
    }

    /**
     * Displays a message in the action bar for a player
     * @param player Player
     * @param messageCode Message Code
     * @param message Message
     * @param placeHolders Placeholders
     */
    public void actionBar(Player player, String messageCode, String message, Map<String, String> placeHolders) {
        try {
            if (player != null) {
                if (Bukkit.getPlayer(player.getUniqueId()) != null) {
                    Locale locale = new Locale(instance);
                    if (messageCode.equalsIgnoreCase("na")) {
                        message = locale.localeText(messageCode, message, placeHolders);
                    }
                    if (!StringFunctions.isNullOrEmpty(message)) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ColorFunctions.color(message)));
                    } else {
                        error.save(
                                "Message.actionBar.004",
                                "Message",
                                "actionBar",
                                "Unexpected Error",
                                "Message not found - message code: " + messageCode + " message: " + message,
                                ErrorSeverity.WARN,
                                null
                        );
                    }
                } else {
                    error.save(
                            "Message.actionBar.003",
                            "Message",
                            "actionBar",
                            "Unexpected Error",
                            "Player is not found",
                            ErrorSeverity.WARN,
                            null
                    );
                }
            } else {
                error.save(
                        "Message.actionBar.002",
                        "Message",
                        "actionBar",
                        "Unexpected Error",
                        "Player is null",
                        ErrorSeverity.CRITICAL,
                        null
                );
            }
        } catch (Exception ex) {
            error.save(
                    "Message.actionBar.001",
                    "Message",
                    "actionBar",
                    "Unexpected Error",
                    ex.getMessage(),
                    ErrorSeverity.CRITICAL,
                    ex.getStackTrace()
            );
        }
    }

    /**
     * Displays a message in the action bar for a player
     * Passes a na locale code so no locale translation is done
     * @param player Player
     * @param message Message
     */
    public void actionBar(Player player, String message) {
        String messageCode = "na";
        Map<String, String> placeHolders = new HashMap<>();
        this.actionBar(player, messageCode, message, placeHolders);
    }

    /**
     * Displays a message in the title bar for the player
     * @param player Player
     * @param titleCode Message code for the title
     * @param title Title
     * @param titlePlaceHolders Title placeholders
     * @param subtitleCode Message code for the subtitle
     * @param subtitle Subtitle
     * @param subtitlePlaceHolders Subtitle placeholders
     * @param fadeIn Fade in ticks
     * @param stay Stay ticks
     * @param fadeOut Fade out ticks
     */
    public void titleBar(Player player, String titleCode, String title, Map<String, String> titlePlaceHolders, String subtitleCode, String subtitle, Map<String, String> subtitlePlaceHolders, int fadeIn, int stay, int fadeOut) {
        try {
            if (player != null) {
                if (Bukkit.getPlayer(player.getUniqueId()) != null) {
                    Locale locale = new Locale(instance);
                    if (titleCode.equalsIgnoreCase("na")) {
                        title = locale.localeText(titleCode, title, titlePlaceHolders);
                    }
                    if (subtitleCode.equalsIgnoreCase("na")) {
                        subtitle = locale.localeText(subtitleCode, subtitle, subtitlePlaceHolders);
                    }
                    if (!StringFunctions.isNullOrEmpty(title)) {
                        player.resetTitle();
                        player.sendTitle(title, StringFunctions.notNull(subtitle), fadeIn, stay, fadeOut);
                    } else {
                        error.save(
                                "Message.titleBar.004",
                                "Message",
                                "titleBar",
                                "Unexpected Error",
                                "Message not found - title code: " + titleCode + " title: " + title,
                                ErrorSeverity.WARN,
                                null
                        );
                    }
                } else {
                    error.save(
                            "Message.titleBar.003",
                            "Message",
                            "titleBar",
                            "Unexpected Error",
                            "Player is not found",
                            ErrorSeverity.WARN,
                            null
                    );
                }
            } else {
                error.save(
                        "Message.titleBar.002",
                        "Message",
                        "titleBar",
                        "Unexpected Error",
                        "Player is null",
                        ErrorSeverity.CRITICAL,
                        null
                );
            }
        } catch (Exception ex) {
            error.save(
                    "Message.titleBar.001",
                    "Message",
                    "titleBar",
                    "Unexpected Error",
                    ex.getMessage(),
                    ErrorSeverity.CRITICAL,
                    ex.getStackTrace()
            );
        }
    }

    /**
     * Displays a message in the title bar for the player
     * Passes a na locale code so no locale translation is done
     * @param player Player
     * @param title Title
     * @param subtitle Subtitle
     * @param fadeIn Fade in ticks
     * @param stay Stay ticks
     * @param fadeOut Fade out ticks
     */
    public void titleBar(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        this.titleBar(player, "na", title, new HashMap<>(), "na", subtitle, new HashMap<>(), fadeIn, stay, fadeOut);
    }
}
