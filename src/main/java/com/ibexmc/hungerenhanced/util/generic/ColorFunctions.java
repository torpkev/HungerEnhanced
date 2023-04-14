package com.ibexmc.hungerenhanced.util.generic;

import org.bukkit.ChatColor;
import org.bukkit.Color;

import java.util.ArrayList;
import java.util.List;

public class ColorFunctions {
    /**
     * Returns an & color coded string
     * @param input Input text
     * @return Colored output
     */
    public static String color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    /**
     * Returns an & color coded string list
     * @param input Input list
     * @return Colored output
     */
    public static List<String> color(List<String> input) {
        List<String> output = new ArrayList<>();
        if (input != null) {
            for (String line : input) {
                output.add(color(line));
            }
        }
        return output;
    }

    /**
     * Gets a color by name
     * @param name Color to lookup
     * @return Color requested, WHITE if not found
     */
    public static Color colorFromName(String name) {
        Color ret;
        switch (name) {
            case "AQUA" :
                ret = Color.AQUA;
                break;
            case "BLACK" :
                ret = Color.BLACK;
                break;
            case "BLUE" :
                ret = Color.BLUE;
                break;
            case "FUCHSIA" :
                ret = Color.FUCHSIA;
                break;
            case "GRAY" :
                ret = Color.GRAY;
                break;
            case "GREEN" :
                ret = Color.GREEN;
                break;
            case "LIME" :
                ret = Color.LIME;
                break;
            case "MAROON" :
                ret = Color.MAROON;
                break;
            case "NAVY" :
                ret = Color.NAVY;
                break;
            case "OLIVE" :
                ret = Color.OLIVE;
                break;
            case "ORANGE" :
                ret = Color.ORANGE;
                break;
            case "PURPLE" :
                ret = Color.PURPLE;
                break;
            case "SILVER" :
                ret = Color.SILVER;
                break;
            case "TEAL" :
                ret = Color.TEAL;
                break;
            case "YELLOW" :
                ret = Color.YELLOW;
                break;
            case "WHITE" :
            default:
                ret = Color.WHITE;
                break;
        }
        return ret;
    }
}
