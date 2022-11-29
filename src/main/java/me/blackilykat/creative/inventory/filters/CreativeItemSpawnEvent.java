package me.blackilykat.creative.inventory.filters;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class CreativeItemSpawnEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private ItemStack item;
    private Player player;
    private boolean cancelled;

    public CreativeItemSpawnEvent(Player player, ItemStack item) {
        super(true);
        this.player = player;
        this.item = item;
    }


    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }
}
