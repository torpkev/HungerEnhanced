package com.ibexmc.hungerenhanced.hunger;

import com.ibexmc.hungerenhanced.HungerEnhanced;
import com.ibexmc.hungerenhanced.data.InstanceData;
import com.ibexmc.hungerenhanced.util.log.Log;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class HungerManager {

    private final HungerEnhanced instance;
    private final Log log;

    public HungerManager(HungerEnhanced instance) {
        this.instance = instance;
        this.log = new Log(instance);
    }

    public void applyAction(UUID uuid) {


        int actionsPerHunger = instance.getConfigData().getActionsPerHunger();
        InstanceData instanceData = instance.getData();
        int currentActionLevel = 0;
        if (instanceData.getHungerPlayers().has(uuid)) {
            currentActionLevel = instanceData.getHungerPlayers().get(uuid);
        }
        if (currentActionLevel + 1 >= actionsPerHunger) {
            // apply hunger
            Player player = Bukkit.getPlayer(uuid);
            if (player != null) {
                instanceData.getHungerPlayers().put(uuid, 0);
                player.setFoodLevel(player.getFoodLevel() - 1);
            }
        } else {
            instanceData.getHungerPlayers().put(uuid, currentActionLevel + 1);
        }
    }
}
