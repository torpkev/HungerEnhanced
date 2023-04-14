package com.ibexmc.hungerenhanced;
import com.ibexmc.hungerenhanced.data.ConfigData;
import com.ibexmc.hungerenhanced.data.InstanceData;
import com.ibexmc.hungerenhanced.listeners.bukkit.BlockEvents;
import com.ibexmc.hungerenhanced.runnables.CleanupTicker;
import com.ibexmc.hungerenhanced.util.log.Debug;
import com.ibexmc.hungerenhanced.util.log.Error;
import com.ibexmc.hungerenhanced.util.log.ErrorSeverity;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class HungerEnhanced extends JavaPlugin {

    private static HungerEnhanced instance;
    private Error error;
    private Debug debug;
    private InstanceData instanceData;
    private ConfigData configData;

    public static HungerEnhanced getInstance() {
        return instance;
    }
    public InstanceData getData() {
        return this.instanceData;
    }
    public ConfigData getConfigData() {
        return this.configData;
    }

    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new BlockEvents(this), this);
    }

    public void startRunnable() {
        try {
            BukkitTask task = new BukkitRunnable() {
                public void run() {
                    CleanupTicker cleanupTicker = new CleanupTicker(instance);
                    cleanupTicker.run();
                }
            }.runTaskTimer(instance, 0, 100);
            debug.log(
                    "HungerEnhanced",
                    "startRunnable",
                    "CleanupTicker running with id " + task.getTaskId()
            );
        } catch (Exception ex) {
            error.save(
                    "HungerEnhanced.startRunnable.000",
                    "HungerEnhanced",
                    "startRunnable",
                    "Unexpected Error",
                    ex.getMessage(),
                    ErrorSeverity.URGENT,
                    ex.getStackTrace()
            );
        }
    }

    @Override
    public void onLoad() {
        instance = this;
        this.instanceData = new InstanceData(this);
        this.configData = new ConfigData(this);
        this.error = new Error(this);
        this.debug = new Debug(this);
    }
    @Override
    public void onEnable() {
        registerEvents();
        startRunnable();
    }
    @Override
    public void onDisable() {

    }

}
