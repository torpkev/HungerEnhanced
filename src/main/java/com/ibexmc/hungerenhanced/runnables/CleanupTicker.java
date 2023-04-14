package com.ibexmc.hungerenhanced.runnables;

import com.ibexmc.hungerenhanced.HungerEnhanced;
import com.ibexmc.hungerenhanced.util.log.Log;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CleanupTicker extends BukkitRunnable {

    private final HungerEnhanced instance;
    private final Log log;

    public CleanupTicker(HungerEnhanced instance) {
        this.instance = instance;
        this.log = new Log(instance);
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            int currentActionLevel = 0;
            if (instance.getData().getHungerPlayers().has(player.getUniqueId())) {
                currentActionLevel = instance.getData().getHungerPlayers().get(player.getUniqueId());
            }
            if (currentActionLevel > 0) {
                instance.getData().getHungerPlayers().put(player.getUniqueId(), currentActionLevel - 1);
            }
        }


    }
}
