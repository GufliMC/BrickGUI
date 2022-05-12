package com.guflimc.brick.gui.spigot.listener;

import com.guflimc.brick.gui.spigot.menu.SpigotRegistry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class SpigotMenuListener implements Listener {

    public final SpigotRegistry registry;

    public SpigotMenuListener(SpigotRegistry registry) {
        this.registry = registry;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        registry.findMenu(event.getPlayer())
                .ifPresent(gui -> gui.dispatchClose(event.getPlayer()));
        registry.unregister(event.getPlayer());
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        registry.findMenu(event.getPlayer())
                .filter(gui -> gui.inventory == event.getInventory())
                .ifPresent(gui -> gui.dispatchClose(event.getPlayer()));
        registry.unregister(event.getPlayer());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        registry.findMenu(event.getWhoClicked())
                .filter(gui -> gui.inventory == event.getInventory())
                .ifPresent(gui -> gui.dispatchClick(event));
    }

}
