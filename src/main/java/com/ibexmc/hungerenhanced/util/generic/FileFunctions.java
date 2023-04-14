package com.ibexmc.hungerenhanced.util.generic;

import com.ibexmc.hungerenhanced.HungerEnhanced;
import com.ibexmc.hungerenhanced.util.log.Error;
import com.ibexmc.hungerenhanced.util.log.ErrorSeverity;
import com.ibexmc.hungerenhanced.util.log.Log;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class FileFunctions {

    /**
     * Gets the config.yml file - If not found, will try to create from resources, if not found there
     * will create a new blank file
     * @return Config File object
     */
    public static File getConfigYml() {
        File configFile = new File(
                HungerEnhanced.getInstance().getDataFolder() +
                        File.separator +
                        "config.yml"
        );
        if (!configFile.exists()) {
            if (HungerEnhanced.getInstance().getResource("config.yml") != null) {
                HungerEnhanced.getInstance().saveResource("config.yml", false);
            } else {
                try {
                    configFile.createNewFile();
                    FileConfiguration configuration = YamlConfiguration.loadConfiguration(configFile);
                    try {
                        configuration.save(configFile);
                    } catch (IOException e) {
                        Error error = new Error(HungerEnhanced.getInstance());
                        error.save(
                                "HungerEnhanced.startRunnable.000",
                                "HungerEnhanced",
                                "startRunnable",
                                "Unexpected Error",
                                e.getMessage(),
                                ErrorSeverity.URGENT,
                                e.getStackTrace()
                        );
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return configFile;
    }

    /**
     * Writes a map of objects to a yaml file
     * @param instance HungerEnhanced Instance
     * @param folderName Folder Name
     * @param filename Filename
     * @param items Map of objects to write
     * @return If true, file wrote successfully
     */
    public static boolean saveToFile(HungerEnhanced instance, String folderName, String filename, Map<String, Object> items) {

        Error error = new Error(instance);
        Log log = new Log(instance);

        File dir = new File(folderName);
        if (!dir.exists()) {
            boolean mk = dir.mkdir();
            if (!mk) {
                log.log(
                        "FileFunctions",
                        "saveToFile",
                        "Creating Folder: " + folderName
                );
            }
        }

        File file = new File(folderName + File.separator + filename);
        if (!file.exists()) {
            try {
                boolean c = file.createNewFile();
                if (!c) {
                    error.save(
                            "FileFunctions.saveToFile.004",
                            "FileFunctions",
                            "saveToFile",
                            "Unexpected Error",
                            "Error creating new file",
                            ErrorSeverity.URGENT,
                            null
                    );
                }
            } catch (IOException e) {
                error.save(
                        "FileFunctions.saveToFile.003",
                        "FileFunctions",
                        "saveToFile",
                        "Unexpected Error",
                        e.getMessage(),
                        ErrorSeverity.URGENT,
                        e.getStackTrace()
                );
            } catch (Exception ex) {
                error.save(
                        "FileFunctions.saveToFile.002",
                        "FileFunctions",
                        "saveToFile",
                        "Unexpected Error",
                        ex.getMessage(),
                        ErrorSeverity.URGENT,
                        ex.getStackTrace()
                );
            }
        }

        FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);

        for (Map.Entry<String, Object> item : items.entrySet()) {
            fileConfig.set(item.getKey(), item.getValue());
        }

        try {
            fileConfig.save(file);
            return true;
        } catch (IOException e) {
            error.save(
                    "FileFunctions.saveToFile.001",
                    "FileFunctions",
                    "saveToFile",
                    "Unexpected Error",
                    e.getMessage(),
                    ErrorSeverity.URGENT,
                    e.getStackTrace()
            );
        } catch (Exception ex) {
            error.save(
                    "FileFunctions.saveToFile.000",
                    "FileFunctions",
                    "saveToFile",
                    "Unexpected Error",
                    ex.getMessage(),
                    ErrorSeverity.URGENT,
                    ex.getStackTrace()
            );
        }

        return false;
    }

    public static boolean renameFile(HungerEnhanced instance, File file, String newName) {

        Error error = new Error(instance);
        Log log = new Log(instance);

        if (file.exists()) {
            try {
                File newFile = new File(file.getPath() + File.separator + newName);
                return file.renameTo(newFile);
            } catch (Exception ex) {
                error.save(
                        "FileFunctions.renameFile.000",
                        "FileFunctions",
                        "renameFile",
                        "Unexpected Error",
                        ex.getMessage(),
                        ErrorSeverity.URGENT,
                        ex.getStackTrace()
                );
                return false;
            }
        } else {
            error.save(
                    "FileFunctions.renameFile.001",
                    "FileFunctions",
                    "renameFile",
                    "File does not exist",
                    "Original File: " + file.getName() + " New File: " + newName,
                    ErrorSeverity.URGENT,
                    null
            );
            return false;
        }
    }
}
