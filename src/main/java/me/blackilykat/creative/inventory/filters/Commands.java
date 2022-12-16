package me.blackilykat.creative.inventory.filters;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.IStringTooltip;
import dev.jorel.commandapi.StringTooltip;
import dev.jorel.commandapi.arguments.Argument;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.BooleanArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import org.bukkit.Material;

import java.util.ArrayList;

public class Commands {
    //TODO: Reload command and editing the config from the server
    public static void register() {

        new CommandAPICommand("creativeinventoryfilters")
                .withSubcommand(new CommandAPICommand("reload")
                        .withPermission("creativeinventoryfilters.reload")
                        .executes((sender, args) -> {
                            Chat.sendMessage(sender, "&7Reloading config...", true);
                            
                            Config.load();
                            Chat.sendMessage(sender, "&aDone!", true);
                            
                        })
                ).withSubcommand(new CommandAPICommand("debug")
                        .withPermission("creativeinventoryfilters.debug")
                        .withSubcommand(new CommandAPICommand("config")
                                .withPermission("creativeinventoryfilters.debug.config")
                                .executes((sender, args) -> {
                                    Chat.sendMessage(sender, "&l&6--- CIF config debug ---", false);
                                    Chat.sendMessage(sender, "&e-- TYPES --", false);
                                    for (NBTType type : Config.types) {
                                        Chat.sendMessage(sender, "- "+type.toString(), false);
                                    }
                                    Chat.sendMessage(sender, "&e-- ITEM BLACKLIST --", false);
                                    for (Material item : Config.itemBlacklist) {
                                        Chat.sendMessage(sender, "- "+item.toString(), false);
                                    }
                                    Chat.sendMessage(sender, "&e-- NBT BLACKLIST --", false);
                                    for (Material item : Config.nbtBlacklist) {
                                        Chat.sendMessage(sender, "- "+item.toString(), false);
                                    }
                                    Chat.sendMessage(sender, "&e-- NBT WHITELIST --", false);
                                    for (Material item : Config.nbtWhitelist) {
                                        Chat.sendMessage(sender, "- "+item.toString(), false);
                                    }
                                })
                        )

                ).withSubcommand(new CommandAPICommand("set")
                        .withPermission("creativeinventoryfilters.set")
                        .withArguments(new StringArgument("setting").includeSuggestions(ArgumentSuggestions.stringsWithTooltips(
                                StringTooltip.ofString("type", "What data to block in items"),
                                StringTooltip.ofString("item-blacklist", "Which items to block entirely"),
                                StringTooltip.ofString("nbt-blacklist", "Which items should never have any nbt data"),
                                StringTooltip.ofString("nbt-whitelist", "Which items should be allowed any nbt data")))
                        ).withArguments(new StringArgument("item").includeSuggestions(ArgumentSuggestions.strings(info -> {
                            ArrayList<String> suggestions = new ArrayList<>();
                            String setting = (String) info.previousArgs()[0];
                            if(setting.equals("type")) {
                                suggestions.add("display");
                                suggestions.add("attribute_modifiers");
                                suggestions.add("enchantments");
                                suggestions.add("entity");
                                suggestions.add("items");
                                suggestions.add("head");
                                suggestions.add("firework");
                                suggestions.add("block");
                                suggestions.add("book");
                            } else if(setting.equals("item-blacklist") || setting.equals("nbt-blacklist") || setting.equals("nbt-whitelist")) {
                                for (Material material : Material.class.getEnumConstants()) {
                                    suggestions.add(material.toString().toLowerCase());
                                }
                            }
                            return suggestions.toArray(new String[0]);
                        })))
                        .withArguments(new BooleanArgument("value"))
                        .executes((sender, args) -> {
                            String setting = (String) args[0];
                            String item = ((String) args[1]).toUpperCase();
                            boolean value = (boolean) args[2];
                            if(setting.equals("type")) {
                                try {
                                    if (value) {
                                        if (!Config.types.contains(NBTType.valueOf(item)))
                                            Config.types.add(NBTType.valueOf(item));
                                        Chat.sendMessage(sender, "set value of " + NBTType.valueOf(item) + " to " + value, true);
                                    } else {
                                        if (Config.types.contains(NBTType.valueOf(item)))
                                            Config.types.remove(NBTType.valueOf(item));
                                        Chat.sendMessage(sender, "set value of " + NBTType.valueOf(item) + " to " + value, true);
                                    }
                                } catch (IllegalArgumentException e) {
                                    Chat.sendMessage(sender, "&cInvalid type "+item.toLowerCase()+".", true);
                                }
                            } else if(setting.equals("item-blacklist")) {
                                Material material;
                                try {
                                    material = Material.valueOf(item);
                                } catch (IllegalArgumentException e) {
                                    Chat.sendMessage(sender, "&cInvalid item "+item.toLowerCase()+".", true);
                                    return;
                                }
                                if(value) {
                                    if(!Config.itemBlacklist.contains(material)) Config.itemBlacklist.add(material);
                                    Chat.sendMessage(sender, "Added item "+item.toLowerCase()+" to the item blacklist.", true);
                                } else {
                                    if(Config.itemBlacklist.contains(material)) Config.itemBlacklist.remove(material);
                                    Chat.sendMessage(sender, "Removed item "+item.toLowerCase()+" from the item blacklist.", true);
                                }
                            } else if(setting.equals("nbt-blacklist")) {
                                Material material;
                                try {
                                    material = Material.valueOf(item);
                                } catch (IllegalArgumentException e) {
                                    Chat.sendMessage(sender, "&cInvalid item "+item.toLowerCase()+".", true);
                                    return;
                                }
                                if(value) {
                                    if(!Config.nbtBlacklist.contains(material)) Config.nbtBlacklist.add(material);
                                    Chat.sendMessage(sender, "Added item "+item.toLowerCase()+" to the nbt blacklist.", true);
                                } else {
                                    if(Config.nbtBlacklist.contains(material)) Config.nbtBlacklist.remove(material);
                                    Chat.sendMessage(sender, "Removed item "+item.toLowerCase()+" from the nbt blacklist.", true);
                                }
                            } else if(setting.equals("nbt-whitelist")) {
                                Material material;
                                try {
                                    material = Material.valueOf(item);
                                } catch (IllegalArgumentException e) {
                                    Chat.sendMessage(sender, "&cInvalid item "+item.toLowerCase()+".", true);
                                    return;
                                }
                                if(value) {
                                    if(!Config.nbtWhitelist.contains(material)) Config.nbtWhitelist.add(material);
                                    Chat.sendMessage(sender, "Added item "+item.toLowerCase()+" to the nbt whitelist.", true);
                                } else {
                                    if(Config.nbtWhitelist.contains(material)) Config.nbtWhitelist.remove(material);
                                    Chat.sendMessage(sender, "Removed item "+item.toLowerCase()+" from the nbt whitelist.", true);
                                }
                            } else {
                                Chat.sendMessage(sender, "&cInvalid setting "+setting+".", true);
                            }
                        })
                )
                .register();

    }
}
