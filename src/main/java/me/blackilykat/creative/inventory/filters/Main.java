package me.blackilykat.creative.inventory.filters;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Main extends JavaPlugin {
    static Logger LOGGER;
    static JavaPlugin plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        LOGGER = this.getLogger();
        plugin = this;
        getServer().getPluginManager().registerEvents(new ItemTakeListener(), this);
        PacketListeners.add();
        LOGGER.info("Loading config...");
        Config.load();
        LOGGER.info("Config loaded!");
        LOGGER.info("Creative inventory filters loaded.");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        LOGGER.info("Creative inventory filters disabled");
    }
}
