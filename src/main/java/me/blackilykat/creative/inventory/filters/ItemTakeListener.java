package me.blackilykat.creative.inventory.filters;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import java.util.ArrayList;


public class ItemTakeListener  implements Listener {
    @EventHandler
    public void onCreativeItemTake(CreativeItemSpawnEvent event) {
        ArrayList<NBTType> nbtTypeList = new ArrayList<>();
        if(
                event.getItem() == null
                || event.getItem().getType() == Material.AIR
        ) return;
        NBTItem nbti = new NBTItem(event.getItem());

        if(nbti.hasKey("display")) nbtTypeList.add(NBTType.DISPLAY);
        if(nbti.hasKey("AttributeModifiers")) nbtTypeList.add(NBTType.ATTRIBUTE_MODIFIERS);
        if(nbti.hasKey("Enchantments")) nbtTypeList.add(NBTType.ENCHANTMENTS);
        if(nbti.hasKey("EntityTag")) nbtTypeList.add(NBTType.ENTITY);
        if(nbti.hasKey("BlockEntityTag")) nbtTypeList.add(NBTType.ITEMS);
        if(nbti.hasKey("SkullOwner")) nbtTypeList.add(NBTType.HEAD);
        if(nbti.hasKey("Fireworks")) nbtTypeList.add(NBTType.FIREWORK);
        if(nbti.hasKey("BlockStateTag")) nbtTypeList.add(NBTType.BLOCK);
        if(nbti.hasKey("pages")) nbtTypeList.add(NBTType.BOOK);

        if(nbtTypeList.contains(NBTType.DISPLAY)) {
            if(Config.debug) Main.LOGGER.info("It has display data");
            if(Config.types.contains(NBTType.DISPLAY)) nbti.removeKey("display");
        }
        if(nbtTypeList.contains(NBTType.ATTRIBUTE_MODIFIERS)) {
            if(Config.debug) Main.LOGGER.info("It has attribute modifiers");
            if(Config.types.contains(NBTType.ATTRIBUTE_MODIFIERS)) nbti.removeKey("AttributeModifiers");
        }
        if(nbtTypeList.contains(NBTType.ENCHANTMENTS)) {
            if(Config.debug) Main.LOGGER.info("It has enchantments");
            if(Config.types.contains(NBTType.ENCHANTMENTS)) nbti.removeKey("Enchantments");
        }
        if(nbtTypeList.contains(NBTType.ENTITY)) {
            if(Config.debug) Main.LOGGER.info("It has entity data");
            if(Config.types.contains(NBTType.ENTITY)) nbti.removeKey("EntityTag");
        }
        if(nbtTypeList.contains(NBTType.ITEMS)) {
            if(Config.debug) Main.LOGGER.info("It contains items");
            if(Config.types.contains(NBTType.ITEMS)) nbti.removeKey("BlockEntityTag");
        }
        if(nbtTypeList.contains(NBTType.HEAD)) {
            if(Config.debug) Main.LOGGER.info("It's a custom player head'");
            if(Config.types.contains(NBTType.HEAD)) nbti.removeKey("SkullOwner");
        }
        if(nbtTypeList.contains(NBTType.BLOCK)) {
            if(Config.debug) Main.LOGGER.info("It has block data");
            if(Config.types.contains(NBTType.BLOCK)) nbti.removeKey("BlockStateTag");
        }
        if(nbtTypeList.contains(NBTType.FIREWORK)) {
            if(Config.debug) Main.LOGGER.info("It's a custom firework");
            if(Config.types.contains(NBTType.FIREWORK)) nbti.removeKey("Fireworks");
        }
        if(nbtTypeList.contains(NBTType.BOOK)) {
            if(Config.debug) Main.LOGGER.info("It's a written book");
            if(Config.types.contains(NBTType.BOOK)) nbti.removeKey("pages");
        }
        event.setItem(nbti.getItem());
    }
}
