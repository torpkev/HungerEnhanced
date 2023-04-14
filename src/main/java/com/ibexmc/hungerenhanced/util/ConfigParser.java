package com.ibexmc.hungerenhanced.util;

import com.ibexmc.hungerenhanced.HungerEnhanced;
import com.ibexmc.hungerenhanced.util.generic.ColorFunctions;
import com.ibexmc.hungerenhanced.util.generic.ItemFunctions;
import com.ibexmc.hungerenhanced.util.generic.StringFunctions;
import com.ibexmc.hungerenhanced.util.generic.WorldFunctions;
import com.ibexmc.hungerenhanced.util.log.Error;
import com.ibexmc.hungerenhanced.util.log.ErrorSeverity;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConfigParser {
    private HungerEnhanced instance;
    private FileConfiguration config;
    private Error error;

    public ConfigParser(HungerEnhanced instance, File configFile) {
        this.error = new Error(instance);
        this.instance = instance;
        instance.saveDefaultConfig();
        instance.reloadConfig();
        this.config = YamlConfiguration.loadConfiguration(configFile);
    }


    /**
     * Gets a list of keys for this configItem
     * For a list of all root level keys, use ""
     * @param configItem Config Item
     * @return List of keys
     */
    public List<String> getKeys(String configItem) {
        List<String> keys = new ArrayList<>();
        for (String key : this.config.getConfigurationSection(configItem).getKeys(false)) {
            keys.add(key);
        }
        return keys;
    }

    /**
     * Gets an Integer config item
     * @param configItem Configuration item to check
     * @param defaultValue Default value
     * @param required If true, is required
     * @return Output object
     */
    public ConfigParser.Output getInt(String configItem, Integer defaultValue, boolean required) {
        try {
            if (this.config.contains(configItem)) {
                if (this.config.isInt(configItem)) {
                    return new ConfigParser.Output(true, this.config.getInt(configItem));
                } else {
                    error.save(
                            "ConfigParser.getValue.001",
                            "ConfigParser",
                            "getValue(String configItem, Integer defaultValue, boolean required)",
                            "Config item is not int",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                    return new ConfigParser.Output(false, defaultValue);
                }
            } else {
                if (required) {
                    error.save(
                            "ConfigParser.getValue.002",
                            "ConfigParser",
                            "getValue(String configItem, Integer defaultValue, boolean required)",
                            "Config item is missing and required",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                }
                return new ConfigParser.Output(false, defaultValue);
            }
        } catch (Exception ex) {
            error.save(
                    "ConfigParser.getValue.003",
                    "ConfigParser",
                    "getValue(String configItem, Integer defaultValue, boolean required)",
                    "Unexpected Error getting String",
                    "Config Item: " + configItem + "\nError: " + ex.getMessage(),
                    ErrorSeverity.URGENT,
                    ex.getStackTrace()
            );
            return new ConfigParser.Output(false, defaultValue);
        }
    }

    /**
     * Gets a double config item
     * @param configItem Configuration item to check
     * @param defaultValue Default value
     * @param required If true, is required
     * @return Output object
     */
    public ConfigParser.Output getDouble(String configItem, double defaultValue, boolean required) {
        try {
            if (this.config.contains(configItem)) {
                if (this.config.isDouble(configItem) || this.config.isInt(configItem)) {
                    return new ConfigParser.Output(true, this.config.getDouble(configItem));
                } else {
                    error.save(
                            "ConfigParser.getDoubleValue.001",
                            "ConfigParser",
                            "getDoubleValue(String configItem, double defaultValue, boolean required)",
                            "Config item is not an int or double",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                    return new ConfigParser.Output(false, defaultValue);
                }
            } else {
                if (required) {
                    error.save(
                            "ConfigParser.getDoubleValue.002",
                            "ConfigParser",
                            "getDoubleValue(String configItem, double defaultValue, boolean required)",
                            "Config item is missing and required",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                }
                return new ConfigParser.Output(false, defaultValue);
            }
        } catch (Exception ex) {
            error.save(
                    "ConfigParser.getValue.003",
                    "ConfigParser",
                    "getValue(String configItem, double defaultValue, boolean required)",
                    "Unexpected Error getting String",
                    "Config Item: " + configItem + "\nError: " + ex.getMessage(),
                    ErrorSeverity.URGENT,
                    ex.getStackTrace()
            );
            return new ConfigParser.Output(false, defaultValue);
        }
    }

    /**
     * Gets a long config item
     * @param configItem Configuration item to check
     * @param defaultValue Default value
     * @param required If true, is required
     * @return Output object
     */
    public ConfigParser.Output getLong(String configItem, long defaultValue, boolean required) {
        try {
            if (this.config.contains(configItem)) {
                if (this.config.isLong(configItem) || this.config.isInt(configItem)) {
                    return new ConfigParser.Output(true, this.config.getLong(configItem));
                } else {
                    error.save(
                            "ConfigParser.getLong.001",
                            "ConfigParser",
                            "getDoubleValue(String configItem, long defaultValue, boolean required)",
                            "Config item is not an int or long",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                    return new ConfigParser.Output(false, defaultValue);
                }
            } else {
                if (required) {
                    error.save(
                            "ConfigParser.getLong.002",
                            "ConfigParser",
                            "getLong(String configItem, long defaultValue, boolean required)",
                            "Config item is missing and required",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                }
                return new ConfigParser.Output(false, defaultValue);
            }
        } catch (Exception ex) {
            error.save(
                    "ConfigParser.getLong.003",
                    "ConfigParser",
                    "getLong(String configItem, long defaultValue, boolean required)",
                    "Unexpected Error getting String",
                    "Config Item: " + configItem + "\nError: " + ex.getMessage(),
                    ErrorSeverity.URGENT,
                    ex.getStackTrace()
            );
            return new ConfigParser.Output(false, defaultValue);
        }
    }

    /**
     * Gets a String config item
     * @param configItem Configuration item to check
     * @param defaultValue Default value
     * @param required If true, is required
     * @return Output object
     */
    public ConfigParser.Output getString(String configItem, String defaultValue, boolean required) {
        try {
            if (this.config.contains(configItem)) {
                if (this.config.isString(configItem)) {
                    return new ConfigParser.Output(true, this.config.getString(configItem));
                } else {
                    error.save(
                            "ConfigParser.getValue.001",
                            "ConfigParser",
                            "getValue(String configItem, String defaultValue, boolean required)",
                            "Config item is not string",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                    return new ConfigParser.Output(false, defaultValue);
                }
            } else {
                if (required) {
                    error.save(
                            "ConfigParser.getValue.002",
                            "ConfigParser",
                            "getValue(String configItem, String defaultValue, boolean required)",
                            "Config item is missing and required",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                }
                return new ConfigParser.Output(false, defaultValue);
            }
        } catch (Exception ex) {
            error.save(
                    "ConfigParser.getValue.003",
                    "ConfigParser",
                    "getValue(String configItem, String defaultValue, boolean required)",
                    "Unexpected Error getting String",
                    "Config Item: " + configItem + "\nError: " + ex.getMessage(),
                    ErrorSeverity.URGENT,
                    ex.getStackTrace()
            );
            return new ConfigParser.Output(false, defaultValue);
        }
    }

    /**
     * Gets a boolean config item
     * @param configItem Configuration item to check
     * @param defaultValue Default value
     * @param required If true, is required
     * @return Output object
     */
    public ConfigParser.Output getBoolean(String configItem, boolean defaultValue, boolean required) {
        try {
            if (this.config.contains(configItem)) {
                if (this.config.isBoolean(configItem)) {
                    return new ConfigParser.Output(true, this.config.getString(configItem));
                } else {
                    error.save(
                            "ConfigParser.getValue.001",
                            "ConfigParser",
                            "getValue(String configItem, boolean defaultValue, boolean required)",
                            "Config item is not boolean",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                    return new ConfigParser.Output(false, defaultValue);
                }
            } else {
                if (required) {
                    error.save(
                            "ConfigParser.getValue.002",
                            "ConfigParser",
                            "getValue(String configItem, boolean defaultValue, boolean required)",
                            "Config item is missing and required",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                }
                return new ConfigParser.Output(false, defaultValue);
            }
        } catch (Exception ex) {
            error.save(
                    "ConfigParser.getValue.003",
                    "ConfigParser",
                    "getValue(String configItem, boolean defaultValue, boolean required)",
                    "Unexpected Error getting String",
                    "Config Item: " + configItem + "\nError: " + ex.getMessage(),
                    ErrorSeverity.URGENT,
                    ex.getStackTrace()
            );
            return new ConfigParser.Output(false, defaultValue);
        }
    }

    /**
     * Gets a UUID config item
     * @param configItem Configuration item to check
     * @param required If true, is required
     * @return Output object
     */
    public ConfigParser.Output getUUID(String configItem, boolean required) {
        try {
            if (this.config.contains(configItem)) {
                if (this.config.isString(configItem)) {
                    return new ConfigParser.Output(true, this.config.get(configItem));
                } else {
                    error.save(
                            "ConfigParser.getValue.001",
                            "ConfigParser",
                            "getValue(String configItem, boolean required)",
                            "Config item is not string",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                    return new ConfigParser.Output(false, null);
                }
            } else {
                if (required) {
                    error.save(
                            "ConfigParser.getValue.002",
                            "ConfigParser",
                            "getValue(String configItem, boolean required)",
                            "Config item is missing and required",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                }
                return new ConfigParser.Output(false, null);
            }
        } catch (Exception ex) {
            error.save(
                    "ConfigParser.getValue.003",
                    "ConfigParser",
                    "getValue(String configItem, boolean required)",
                    "Unexpected Error getting String",
                    "Config Item: " + configItem + "\nError: " + ex.getMessage(),
                    ErrorSeverity.URGENT,
                    ex.getStackTrace()
            );
            return new ConfigParser.Output(false, null);
        }
    }

    /**
     * Gets a string list config item
     * @param configItem Configuration item to check
     * @param required If true, is required
     * @return Output object
     */
    public ConfigParser.Output getStringList(String configItem, boolean required) {
        try {
            if (this.config.contains(configItem)) {
                if (this.config.isList(configItem)) {
                    return new ConfigParser.Output(true, this.config.getStringList(configItem));
                } else {
                    error.save(
                            "ConfigParser.getValue.001",
                            "ConfigParser",
                            "getValue(String configItem, List<String> defaultValue, boolean required)",
                            "Config item is not a list",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                    return new ConfigParser.Output(false, new ArrayList<String>());
                }
            } else {
                if (required) {
                    error.save(
                            "ConfigParser.getValue.002",
                            "ConfigParser",
                            "getValue(String configItem, boolean defaultValue, boolean required)",
                            "Config item is missing and required",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                }
                return new ConfigParser.Output(false, new ArrayList<String>());
            }
        } catch (Exception ex) {
            error.save(
                    "ConfigParser.getValue.003",
                    "ConfigParser",
                    "getValue(String configItem, boolean defaultValue, boolean required)",
                    "Unexpected Error getting String",
                    "Config Item: " + configItem + "\nError: " + ex.getMessage(),
                    ErrorSeverity.URGENT,
                    ex.getStackTrace()
            );
            return new ConfigParser.Output(false, new ArrayList<String>());
        }
    }

    /**
     * Gets a Material config item
     * @param configItem Configuration item to check
     * @param defaultValue Default value
     * @param required If true, is required
     * @return Output object
     */
    public ConfigParser.Output getMaterial(String configItem, Material defaultValue, boolean required) {
        try {
            if (this.config.contains(configItem)) {
                if (this.config.isString(configItem)) {
                    Material material = StringFunctions.stringToMaterial(this.config.getString(configItem));
                    return new ConfigParser.Output(true, material);
                } else {
                    error.save(
                            "ConfigParser.getValue.001",
                            "ConfigParser",
                            "getValue(String configItem, Material defaultValue, boolean required)",
                            "Config item is not string",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                    return new ConfigParser.Output(false, defaultValue);
                }
            } else {
                if (required) {
                    error.save(
                            "ConfigParser.getValue.002",
                            "ConfigParser",
                            "getValue(String configItem, Material defaultValue, boolean required)",
                            "Config item is missing and required",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                }
                return new ConfigParser.Output(false, defaultValue);
            }
        } catch (Exception ex) {
            error.save(
                    "ConfigParser.getValue.003",
                    "ConfigParser",
                    "getValue(String configItem, Material defaultValue, boolean required)",
                    "Unexpected Error getting String",
                    "Config Item: " + configItem + "\nError: " + ex.getMessage(),
                    ErrorSeverity.URGENT,
                    ex.getStackTrace()
            );
            return new ConfigParser.Output(false, defaultValue);
        }
    }

    /**
     * Gets a Location config item
     * @param configItem Configuration item to check
     * @param required If true, is required
     * @return Output object
     */
    public ConfigParser.Output getLocation(String configItem, boolean required) {
        try {
            if (this.config.contains(configItem)) {
                if (this.config.isString(configItem)) {
                    Location location = WorldFunctions.deserialize(this.config.getString(configItem));
                    return new ConfigParser.Output(true, location);
                } else {
                    error.save(
                            "ConfigParser.getValue.001",
                            "ConfigParser",
                            "getValue(String configItem, boolean required)",
                            "Config item is not string",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                    return new ConfigParser.Output(false, null);
                }
            } else {
                if (required) {
                    error.save(
                            "ConfigParser.getValue.002",
                            "ConfigParser",
                            "getValue(String configItem, boolean required)",
                            "Config item is missing and required",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                }
                return new ConfigParser.Output(false, null);
            }
        } catch (Exception ex) {
            error.save(
                    "ConfigParser.getValue.003",
                    "ConfigParser",
                    "getValue(String configItem, boolean required)",
                    "Unexpected Error getting String",
                    "Config Item: " + configItem + "\nError: " + ex.getMessage(),
                    ErrorSeverity.URGENT,
                    ex.getStackTrace()
            );
            return new ConfigParser.Output(false, null);
        }
    }

    /**
     * Gets an ItemStack config item
     * @param configItem Configuration item to check
     * @param required If true, is required
     * @return Output object
     */
    public ConfigParser.Output getItemStack(String configItem, boolean required) {
        try {
            if (this.config.contains(configItem)) {
                if (this.config.isString(configItem)) {
                    ItemStack itemStack = ItemFunctions.deserialize(this.config.getString(configItem));
                    return new ConfigParser.Output(true, itemStack);
                } else {
                    error.save(
                            "ConfigParser.getValue.001",
                            "ConfigParser",
                            "getItemStack(String configItem, boolean required)",
                            "Config item is not string",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                    return new ConfigParser.Output(false, null);
                }
            } else {
                if (required) {
                    error.save(
                            "ConfigParser.getValue.002",
                            "ConfigParser",
                            "getItemStack(String configItem, boolean required)",
                            "Config item is missing and required",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                }
                return new ConfigParser.Output(false, null);
            }
        } catch (Exception ex) {
            error.save(
                    "ConfigParser.getValue.003",
                    "ConfigParser",
                    "getItemStack(String configItem, boolean required)",
                    "Unexpected Error getting String",
                    "Config Item: " + configItem + "\nError: " + ex.getMessage(),
                    ErrorSeverity.URGENT,
                    ex.getStackTrace()
            );
            return new ConfigParser.Output(false, null);
        }
    }

    /**
     * Gets a Material config item
     * @param configItem Configuration item to check
     * @param defaultValue Default value
     * @param required If true, is required
     * @return Output object
     */
    public ConfigParser.Output getPotionType(String configItem, PotionType defaultValue, boolean required) {
        try {
            if (this.config.contains(configItem)) {
                if (this.config.isString(configItem)) {
                    PotionType potionType = PotionType.valueOf(this.config.getString(configItem));
                    return new ConfigParser.Output(true, potionType);
                } else {
                    error.save(
                            "ConfigParser.getValue.001",
                            "ConfigParser",
                            "getValue(String configItem, PotionType defaultValue, boolean required)",
                            "Config item is not string",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                    return new ConfigParser.Output(false, defaultValue);
                }
            } else {
                if (required) {
                    error.save(
                            "ConfigParser.getValue.002",
                            "ConfigParser",
                            "getValue(String configItem, PotionType defaultValue, boolean required)",
                            "Config item is missing and required",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                }
                return new ConfigParser.Output(false, defaultValue);
            }
        } catch (Exception ex) {
            error.save(
                    "ConfigParser.getValue.003",
                    "ConfigParser",
                    "getValue(String configItem, Material defaultValue, boolean required)",
                    "Unexpected Error getting String",
                    "Config Item: " + configItem + "\nError: " + ex.getMessage(),
                    ErrorSeverity.URGENT,
                    ex.getStackTrace()
            );
            return new ConfigParser.Output(false, defaultValue);
        }
    }

    /**
     * Gets a material from a config item
     * @param configItem Configuration item to check
     * @param row Row to get
     * @param column Column to get
     * @return Material for that row/column.  Returns Material.AIR if not found
     */
    public Material getMaterialForCraftRecipe(String configItem, String row, String column) {
        if (this.config.contains(configItem + ".craft." + row + "." + column)) {
            if (this.config.isString(configItem + ".craft." + row + "." + column)) {
                String materialName = config.getString(
                        configItem + ".craft." + row + "." + column
                );
                Material material = StringFunctions.stringToMaterial(materialName);
                if (material == null) {
                    material = Material.BARRIER;
                }
                return material;
            } else {
                return Material.AIR;
            }
        } else {
            return Material.AIR;
        }
    }

    /**
     * Gets a color from a config item
     * @param configItem Configuration item to check
     * @param defaultValue Default value
     * @param required If true, is required
     * @return Output object
     */
    public ConfigParser.Output getColor(String configItem, Color defaultValue, boolean required) {
        try {
            Color returnValue = defaultValue;
            if (this.config.contains(configItem)) {
                if (config.contains(configItem + ".name")) {
                    returnValue = ColorFunctions.colorFromName(config.getString(configItem + ".name"));
                } else if (
                        config.contains(configItem + ".red") &&
                                config.contains(configItem + ".green") &&
                                config.contains(configItem + ".blue")
                ) {
                    if (
                            config.isInt(configItem + ".red") &&
                                    config.isInt(configItem + ".green") &&
                                    config.isInt(configItem + ".blue")
                    ) {
                        returnValue = Color.fromRGB(
                                config.getInt(configItem + ".red"),
                                config.getInt(configItem + ".green"),
                                config.getInt(configItem + ".blue")
                        );
                    }

                }

                return new ConfigParser.Output(true, returnValue);

            } else {
                return new ConfigParser.Output(false, defaultValue);
            }
        } catch (Exception ex) {
            error.save(
                    "ConfigParser.getColor.002",
                    "ConfigParser",
                    "getValue(String configItem, Color defaultValue, boolean required)",
                    "Unexpected Error getting String",
                    "Config Item: " + configItem + "\nError: " + ex.getMessage(),
                    ErrorSeverity.URGENT,
                    ex.getStackTrace()
            );
            return new ConfigParser.Output(false, defaultValue);
        }
    }

    /**
     * Gets a Configuration Section from a config item
     * @param configItem Configuration item to check
     * @param required If true, is required
     * @return Output object
     */
    public ConfigParser.Output getConfigSection(String configItem, boolean required) {
        try {
            if (this.config.contains(configItem)) {
                if (this.config.isConfigurationSection(configItem)) {

                    ConfigurationSection configSection = this.config.getConfigurationSection(configItem);

                    return new ConfigParser.Output(true, configSection);
                } else {
                    error.save(
                            "ConfigParser.getConfigSection.001",
                            "ConfigParser",
                            "getConfigSection(String configItem, boolean required)",
                            "Config item is not config section",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                    return new ConfigParser.Output(false, null);
                }
            } else {
                if (required) {
                    error.save(
                            "ConfigParser.getConfigSection.002",
                            "ConfigParser",
                            "getConfigSection(String configItem, boolean required)",
                            "Config item is missing and required",
                            "Config Item: " + configItem,
                            ErrorSeverity.WARN,
                            null
                    );
                }
                return new ConfigParser.Output(false, null);
            }
        } catch (Exception ex) {
            error.save(
                    "ConfigParser.getConfigSection.003",
                    "ConfigParser",
                    "getConfigSection(String configItem, boolean required)",
                    "Unexpected Error getting String",
                    "Config Item: " + configItem + "\nError: " + ex.getMessage(),
                    ErrorSeverity.URGENT,
                    ex.getStackTrace()
            );
            return new ConfigParser.Output(false, null);
        }
    }
    //endregion

    public static class Output {
        private boolean success;
        private Object returnObject;

        public Output(boolean success, Object returnObject) {
            this.success = success;
            this.returnObject = returnObject;
        }

        /**
         * Checks if the config lookup was successful
         * @return If true, was successful
         */
        public boolean success() {
            return success;
        }

        /**
         * Gets the return object
         * @return Object being returned
         */
        public Object getObject() {
            return returnObject;
        }

        /**
         * Gets the return object as a String
         * @return String of the returned object
         */
        public String getString() {
            if (returnObject instanceof String) {
                return (String) returnObject;
            } else {
                return null;
            }
        }

        /**
         * Gets the return object as a boolean
         * @return boolean of the returned object
         */
        public Boolean getBoolean() {
            if (returnObject instanceof String) {
                String returnString = (String) returnObject;
                if ("true".equalsIgnoreCase(returnString)) {
                    return true;
                }
                if ("false".equalsIgnoreCase(returnString)) {
                    return false;
                }

            }
            return false;
        }

        /**
         * Gets the return object as an Integer
         * @return Integer of the returned object
         */
        public Integer getInt() {
            if (returnObject instanceof Integer) {
                return (Integer) returnObject;
            } else {
                return -1;
            }
        }

        /**
         * Gets the return object as a double
         * @return Double of the returned object
         */
        public Double getDouble() {
            if (returnObject instanceof Double) {
                return (Double) returnObject;
            } else {
                return (double) -1;
            }
        }

        /**
         * Gets the return object as a long
         * @return Long of the returned object
         */
        public Long getLong() {
            if (returnObject instanceof Long) {
                return (Long) returnObject;
            } else {
                return (long) -1;
            }
        }


        /**
         * Gets the return object as a UUID
         * @return UUID of the returned object
         */
        public UUID getUUID() {
            if (returnObject instanceof String) {
                return StringFunctions.uuidFromString(returnObject.toString());
            } else {
                return null;
            }
        }

        /**
         * Gets the return object as a String List
         * @return String List of the returned object
         */
        public List<String> getStringList() {
            if (returnObject instanceof List) {
                return (List<String>) returnObject;
            } else {
                return new ArrayList<>();
            }
        }

        /**
         * Gets the return object as a Material
         * @return Material of the returned object
         */
        public Material getMaterial() {
            if (returnObject instanceof Material) {
                return (Material) returnObject;
            } else {
                return Material.AIR;
            }
        }

        /**
         * Gets the return object as a Location
         * @return Location of the returned object
         */
        public Location getLocation() {
            if (returnObject instanceof Location) {
                return (Location) returnObject;
            } else {
                return null;
            }
        }

        /**
         * Gets the return object as an ItemStack
         * @return ItemStack of the returned object
         */
        public ItemStack getItemStack() {
            if (returnObject instanceof ItemStack) {
                return (ItemStack) returnObject;
            } else {
                return null;
            }
        }

        /**
         * Gets the return object as a PotionType
         * @return PotionType of the returned object
         */
        public PotionType getPotionType() {
            if (returnObject instanceof PotionType) {
                return (PotionType) returnObject;
            } else {
                return PotionType.WATER;
            }
        }

        /**
         * Gets the return object as a Color
         * @return Color of the returned object
         */
        public Color getColor() {
            if (returnObject instanceof Color) {
                return (Color) returnObject;
            } else {
                return Color.WHITE;
            }
        }

        /**
         * Gets the return object as a Configuration Section
         * @return Configuration Section of the returned object
         */
        public ConfigurationSection getConfigSection() {
            if (returnObject instanceof ConfigurationSection) {
                return (ConfigurationSection) returnObject;
            } else {
                return null;
            }
        }
        //endregion
    }

}
