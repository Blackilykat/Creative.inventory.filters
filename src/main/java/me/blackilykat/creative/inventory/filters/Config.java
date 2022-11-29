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

    static void load() {
        types.clear();
        itemBlacklist.clear();
        nbtBlacklist.clear();
        nbtWhitelist.clear();
        Main.plugin.saveDefaultConfig();
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
        }

        for (String s : config.getStringList("Item-blacklist")) {
            itemBlacklist.add(Material.getMaterial(s.toUpperCase()));
        }

        for (String s : config.getStringList("NBT-blacklist")) {
            nbtBlacklist.add(Material.getMaterial(s.toUpperCase()));
        }

        for (String s : config.getStringList("NBT-whitelist")) {
            nbtWhitelist.add(Material.getMaterial(s.toUpperCase()));
        }

        blacklistReplacementItem = new ItemStack(
                Material.getMaterial(config.getConfigurationSection("Blacklisted-items-replace").getString("Item").toUpperCase()),
//                Material.getMaterial("PAPER"),
                config.getConfigurationSection("Blacklisted-items-replace").getInt("Amount")
        );
        // kinda messy but this just sets the display name and lore to the one set in the config and applies color codes to them
        blacklistReplacementItem.getItemMeta().setDisplayName( ChatColor.translateAlternateColorCodes('&', config.getConfigurationSection("Blacklisted-items-replace").getString("Name")) );
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', config.getConfigurationSection("Blacklisted-items-replace").getString("Lore")));
        blacklistReplacementItem.getItemMeta().setLore(lore);

        Main.LOGGER.info("Item blacklist: ");
        for (Material material : itemBlacklist) {
            Main.LOGGER.info(" - "+material.toString());
        }

    }
    //TODO: saving config
    public static void save() {
        Main.LOGGER.info("This isn't made yet!");
    }
}
