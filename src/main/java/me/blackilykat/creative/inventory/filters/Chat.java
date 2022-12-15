package me.blackilykat.creative.inventory.filters;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Chat {
    public static void sendMessage(Player player, String message, boolean hasPrefix) {
        String prefix = "&a[CreativeInventoryFilters] ";
        if(hasPrefix) message = prefix + message;
        message = ChatColor.translateAlternateColorCodes('&', message);
        player.sendMessage(message);
    }
    public static void sendMessage(CommandSender sender, String message, boolean hasPrefix) {
        String prefix = "&a[CreativeInventoryFilters] ";
        if(hasPrefix) message = prefix + message;
        message = ChatColor.translateAlternateColorCodes('&', message);
        sender.sendMessage(message);
    }
    public static void sendMessage(Player[] players, String message, boolean hasPrefix) {
        String prefix = "&a[CreativeInventoryFilters] ";
        if(hasPrefix) message = prefix + message;
        message = ChatColor.translateAlternateColorCodes('&', message);
        for (Player player : players) {
            player.sendMessage(message);
        }
    }
    public static void sendConsole(String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);
        Main.LOGGER.info(message);
    }
    public static void broadcast(String message, boolean hasPrefix) {
        String prefix = "&a[CreativeInventoryFilters] ";
        if(hasPrefix) message = prefix + message;
        message = ChatColor.translateAlternateColorCodes('&', message);
        String finalMessage = message;
        Bukkit.getOnlinePlayers().forEach((player) -> {
            player.sendMessage(finalMessage);
        });
    }

}
