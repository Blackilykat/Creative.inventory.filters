package me.blackilykat.creative.inventory.filters;

import dev.jorel.commandapi.CommandAPICommand;
import org.bukkit.Material;
import org.bukkit.entity.Player;

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

                )
                .register();

    }
}
