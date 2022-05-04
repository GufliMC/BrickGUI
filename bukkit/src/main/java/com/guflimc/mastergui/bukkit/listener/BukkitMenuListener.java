package com.guflimc.mastergui.bukkit.listener;

import com.guflimc.mastergui.bukkit.menu.BukkitRegistry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BukkitMenuListener implements Listener {

    public final BukkitRegistry registry;

    public BukkitMenuListener(BukkitRegistry registry) {
        this.registry = registry;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        registry.get(event.getPlayer())
                .ifPresent(gui -> gui.dispatchClose(event.getPlayer()));
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        registry.get(event.getPlayer())
                .filter(gui -> gui.inventory == event.getInventory())
                .ifPresent(gui -> gui.dispatchClose(event.getPlayer()));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        registry.get(event.getWhoClicked())
                .filter(gui -> gui.inventory == event.getInventory())
                .ifPresent(gui -> {
                    event.setCancelled(true);
                    gui.dispatchClick(event);
                });
    }

}
