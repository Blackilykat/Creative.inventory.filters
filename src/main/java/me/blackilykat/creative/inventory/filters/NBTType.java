package me.blackilykat.creative.inventory.filters;

public enum NBTType {
    DISPLAY, // name and lore
    ATTRIBUTE_MODIFIERS, // attribute modifiers such as max health, attack damage...
    ENCHANTMENTS, // enchantments
    ENTITY, // Entities in spawn eggs or spawners
    ITEMS, // items in shulker boxes / chests
    HEAD, // player head skulls
    FIREWORK, // firework effects
    BLOCK, // any block data not mentioned earlier
    BOOK // stuff written in a book
}
