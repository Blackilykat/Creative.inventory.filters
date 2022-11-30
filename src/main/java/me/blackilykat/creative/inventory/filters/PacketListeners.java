package me.blackilykat.creative.inventory.filters;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class PacketListeners {
    public static void add() {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        manager.addPacketListener(new PacketAdapter(Main.plugin, ListenerPriority.HIGH, PacketType.Play.Client.SET_CREATIVE_SLOT) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                PacketContainer packet = event.getPacket();
                ItemStack item = packet.getItemModifier().read(0);
                event.getPlayer().getInventory().addItem(Config.blacklistReplacementItem);
                if(event.getPlayer().hasPermission("preventsavedhotbars.bypass")) return; // check if player can bypass all items
                if(event.getPlayer().hasPermission("preventsavedhotbars.bypass." + item.getType().toString().toLowerCase())) return; // check if player can bypass a specific item
                if(Config.itemBlacklist.contains(item.getType())) {
                    packet.getItemModifier().write(0, Config.blacklistReplacementItem);
                    event.setPacket(packet);
                    event.getPlayer().updateInventory();
                    return;
                }
                if(Config.nbtBlacklist.contains(item.getType())) {
                    packet.getItemModifier().write(0, new ItemStack(item.getType(), item.getAmount()));
                    event.setPacket(packet);
                    event.getPlayer().updateInventory();
                    return;
                }
                if(item.getType() == Material.AIR || Config.nbtWhitelist.contains(item.getType())) return; // == Material.AIR stops item from vanishing when picked up with the cursor. kinda hardcoded but it works so idc
                CreativeItemSpawnEvent itemSpawnEvent = new CreativeItemSpawnEvent(event.getPlayer(), item);
                Bukkit.getPluginManager().callEvent(itemSpawnEvent);
                packet.getItemModifier().write(0, itemSpawnEvent.getItem());
                event.setPacket(packet);
                event.getPlayer().updateInventory();


            }
            @Override
            public void onPacketSending(PacketEvent event) {}
        });

    }

    public static void debugAllPackets() throws IllegalAccessException {
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        ArrayList<PacketType> list = new ArrayList<PacketType>();
        for (Class<?> x : PacketType.class.getClasses()) {
            Main.LOGGER.info("x: "+x.getName());
            for (Class<?> y : x.getClasses()) {
                Main.LOGGER.info("y: "+y.getName());
                for (Field field : y.getFields()) {
                    Main.LOGGER.info("Before checking class: "+field.getName());
                    if(field.getType() == PacketType.class) {
                        list.add((PacketType) field.get(PacketType.class));
                    }
                }
            }
        }
        list.forEach(packetType -> {
            Main.LOGGER.info("Registered listener for packetType " + packetType.name());
            manager.addPacketListener(new PacketAdapter(Main.plugin, ListenerPriority.HIGH, packetType) {
                @Override
                public void onPacketReceiving(PacketEvent event) {
                    Main.LOGGER.info("RECEIVED PACKET: " + packetType.name());
                }
            });
        });
    }
}
