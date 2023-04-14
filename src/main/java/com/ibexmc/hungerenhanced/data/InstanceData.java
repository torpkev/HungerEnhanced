package com.ibexmc.hungerenhanced.data;

import com.ibexmc.hungerenhanced.HungerEnhanced;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class InstanceData {

    private HungerEnhanced instance;

    public InstanceData(HungerEnhanced instance) {
        this.instance = instance;
    }

    private final Debug debug = new Debug();
    private final HungerPlayer hungerPlayers = new HungerPlayer();
    private final Locale locale = new Locale();

    public Debug getDebug() { return this.debug; }
    public HungerPlayer getHungerPlayers() { return this.hungerPlayers; }
    public Locale getLocale() { return this.locale; }

    public class Debug {
        private boolean debug = false;
        public boolean get() { return this.debug; }
        public void set(boolean debug) { this.debug = debug; }
        public void toggle() { this.debug = !this.debug; }
    }
    public class HungerPlayer {
        private Map<UUID, Integer> actions = new HashMap<>();
        public Map<UUID, Integer> get() { return this.actions; }
        public int get(UUID uuid) { return this.actions.get(uuid); }
        public boolean has(UUID uuid) { return this.actions.containsKey(uuid); }
        public void set(Map<UUID, Integer> actions) { this.actions = actions; }
        public void put(UUID uuid, int count) { this.actions.put(uuid, count); }
        public void remove(String key) { this.actions.remove(key); }
    }
    public class Locale {
        private Map<String, String> locale = new HashMap<>();
        public Map<String, String> get() { return this.locale; }
        public String get(String key) { return this.locale.get(key); }
        public boolean has(String key) { return this.locale.containsKey(key); }
        public void set(Map<String, String> locale) { this.locale = locale; }
        public void put(String key, String locale) { this.locale.put(key, locale); }
        public void remove(String key) { this.locale.remove(key); }
    }

}
