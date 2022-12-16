package me.blackilykat.creative.inventory.filters;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class Config {
    public static ArrayList<NBTType> types = new ArrayList<>();
    public static ArrayList<Material> itemBlacklist = new ArrayList<>();
    public static ArrayList<Material> nbtBlacklist = new ArrayList<>();
    public static ArrayList<Material> nbtWhitelist = new ArrayList<>();
    public static ItemStack blacklistReplacementItem;
    public static boolean debug = false;

    static void load() {
        types.clear();
        itemBlacklist.clear();
        nbtBlacklist.clear();
        nbtWhitelist.clear();
        Main.plugin.saveDefaultConfig();
        Main.plugin.reloadConfig();
        FileConfiguration config = Main.plugin.getConfig();

        for (String s : config.getStringList("Types")) {
            switch (s) {
                case "DISPLAY" -> types.add(NBTType.DISPLAY);
                case "ATTRIBUTE_MODIFIERS" -> types.add(NBTType.ATTRIBUTE_MODIFIERS);
                case "ENCHANTMENTS" -> types.add(NBTType.ENCHANTMENTS);
                case "ENTITY" -> types.add(NBTType.ENTITY);
                case "ITEMS" -> types.add(NBTType.ITEMS);
                case "HEAD" -> types.add(NBTType.HEAD);
                case "FIREWORK" -> types.add(NBTType.FIREWORK);
                case "BLOCK" -> types.add(NBTType.BLOCK);
                case "BOOK" -> types.add(NBTType.BOOK);
            }
            if(debug) Main.LOGGER.info("Found value " + s + " in Types");
        }

        for (String s : config.getStringList("Item-blacklist")) {
            itemBlacklist.add(Material.getMaterial(s.toUpperCase()));
            if(debug) Main.LOGGER.info("Found value " + s + " in Item-blacklist");
        }

        for (String s : config.getStringList("NBT-blacklist")) {
            nbtBlacklist.add(Material.getMaterial(s.toUpperCase()));
            if(debug) Main.LOGGER.info("Found value " + s + " in NBT-blacklist");
        }

        for (String s : config.getStringList("NBT-whitelist")) {
            nbtWhitelist.add(Material.getMaterial(s.toUpperCase()));
            if(debug) Main.LOGGER.info("Found value " + s + " in NBT-whitelist");
        }

        blacklistReplacementItem = new ItemStack(
                Material.getMaterial(config.getConfigurationSection("Blacklisted-items-replace").getString("Item").toUpperCase()),
                config.getConfigurationSection("Blacklisted-items-replace").getInt("Amount")
        );
        if(blacklistReplacementItem.getType() != Material.AIR) { // else it cries cuz no item meta to set display name
            // kinda messy but this just sets the display name and lore to the one set in the config and applies color codes to them
            blacklistReplacementItem.getItemMeta().setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getConfigurationSection("Blacklisted-items-replace").getString("Name")));
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.translateAlternateColorCodes('&', config.getConfigurationSection("Blacklisted-items-replace").getString("Lore")));
            blacklistReplacementItem.getItemMeta().setLore(lore);
        }

        if(debug) {
            Main.LOGGER.info("Enabled filters:");
            for (NBTType type : types) {
                Main.LOGGER.info(" - "+type.toString());
            }
            Main.LOGGER.info("Item blacklist:");
            for (Material material : itemBlacklist) {
                Main.LOGGER.info(" - "+material.toString());
            }
            Main.LOGGER.info("NBT blacklist:");
            for (Material material : nbtBlacklist) {
                Main.LOGGER.info(" - "+material.toString());
            }
            Main.LOGGER.info("NBT whitelist:");
            for (Material material : nbtWhitelist) {
                Main.LOGGER.info(" - "+material.toString());
            }
        }

    }
    //TODO: saving config
    public static void save() {
        ArrayList<String> stypes = new ArrayList<>();
        ArrayList<String> sitemBlacklist = new ArrayList<>();
        ArrayList<String> snbtBlacklist = new ArrayList<>();
        ArrayList<String> snbtWhitelist = new ArrayList<>();

        types.forEach(type -> stypes.add(type.toString()));
        itemBlacklist.forEach(type -> sitemBlacklist.add(type.toString()));
        nbtBlacklist.forEach(type -> snbtBlacklist.add(type.toString()));
        nbtWhitelist.forEach(type -> snbtWhitelist.add(type.toString()));

        Main.plugin.getConfig().set("Types", stypes);
        Main.plugin.getConfig().set("Item-blacklist", sitemBlacklist);
        Main.plugin.getConfig().set("NBT-blacklist", snbtBlacklist);
        Main.plugin.getConfig().set("NBT-whitelist", snbtWhitelist);
        Main.plugin.saveConfig();
        Chat.sendConsole("Saved config!");
    }
}
