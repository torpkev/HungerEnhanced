package com.ibexmc.hungerenhanced.listeners.bukkit;

import com.ibexmc.hungerenhanced.HungerEnhanced;
import com.ibexmc.hungerenhanced.hunger.HungerManager;
import com.ibexmc.hungerenhanced.util.Message;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockEvents implements Listener {

    private final HungerEnhanced instance;
    private final Message message;

    public BlockEvents(HungerEnhanced instance) {
        this.instance = instance;
        this.message = new Message(instance);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        HungerManager hungerManager = new HungerManager(instance);
        hungerManager.applyAction(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        HungerManager hungerManager = new HungerManager(instance);
        hungerManager.applyAction(event.getPlayer().getUniqueId());
    }



}
