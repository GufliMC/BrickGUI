package com.guflimc.guibuilder.bukkit;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BukkitMenuListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if ( !BukkitGUIBuilder.menus.containsKey(event.getPlayer()) ) {
            return;
        }
        BukkitGUIBuilder.menus.remove(event.getPlayer())._dispatchClose(event.getPlayer());
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if ( !BukkitGUIBuilder.menus.containsKey(event.getPlayer()) ) {
            return;
        }
        BukkitGUIBuilder.menus.remove(event.getPlayer())._dispatchClose(event.getPlayer());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if ( !BukkitGUIBuilder.menus.containsKey(event.getWhoClicked()) ) {
            return;
        }
        BukkitGUIBuilder.menus.remove(event.getWhoClicked())._dispatchClick(event);
    }

}
