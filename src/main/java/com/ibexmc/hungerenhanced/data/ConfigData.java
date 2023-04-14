package com.ibexmc.hungerenhanced.data;

import com.ibexmc.hungerenhanced.HungerEnhanced;
import com.ibexmc.hungerenhanced.util.ConfigParser;
import com.ibexmc.hungerenhanced.util.generic.FileFunctions;

import java.io.File;
import java.util.UUID;

public class ConfigData {
    private HungerEnhanced instance;
    private ConfigParser ymlParser;

    public ConfigData(HungerEnhanced instance) {
        this.instance = instance;
        File configFile = FileFunctions.getConfigYml();
        if (configFile != null) {
            ymlParser = new ConfigParser(instance, configFile);
        }
    }

    private String displayName = "";
    private int actionsPerHunger = 10;

    public void load() {


        ConfigParser.Output crDisplayName = ymlParser.getString(
                "display_name",
                "[HungerEnhanced]",
                false
        );
        if (crDisplayName.success()) {
            this.displayName = crDisplayName.getString();
        } else {
            this.displayName = "[HungerEnhanced]";
        }

        ConfigParser.Output crActionsPerHunger = ymlParser.getInt(
                "actions_per_hunger",
                10,
                false
        );
        if (crActionsPerHunger.success()) {
            this.actionsPerHunger = crActionsPerHunger.getInt();
        } else {
            this.actionsPerHunger = 10;
        }

    }

    public String getDisplayName() {
        return displayName;
    }

    public int getActionsPerHunger() { return actionsPerHunger; }
}
