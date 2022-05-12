package com.guflimc.brick.gui.spigot.listener;

import com.guflimc.brick.gui.spigot.menu.SpigotRegistry;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.PlayerInventory;

public class SpigotHotbarListener implements Listener {

    public final SpigotRegistry registry;

    public SpigotHotbarListener(SpigotRegistry registry) {
        this.registry = registry;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        registry.unregister(event.getPlayer());
    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
        if ( !(event.getWhoClicked() instanceof Player player) ) {
            return;
        }
        // TODO
//        registry.findHotbar(player)
//                .ifPresent(gui -> gui.dispatchClick(event));
    }

    @EventHandler(ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent e) {
        registry.findHotbar(e.getPlayer())
                .ifPresent(hotbar -> hotbar.dispatchInteract(e));
    }

    @EventHandler(ignoreCancelled = true)
    public void onInteract(PlayerInteractEntityEvent e) {
        registry.findHotbar(e.getPlayer())
                .ifPresent(hotbar -> hotbar.dispatchInteractEntity(e));
    }

    @EventHandler(ignoreCancelled = true)
    public void onInteract(PlayerInteractAtEntityEvent e) {
        registry.findHotbar(e.getPlayer())
                .ifPresent(hotbar -> hotbar.dispatchInteractEntity(e));
    }

    @EventHandler(ignoreCancelled = true)
    public void onItemMove(InventoryMoveItemEvent e) {
        if ( e.getSource().getHolder() == null || !(e.getSource().getHolder() instanceof Player player) ) return;
        registry.findHotbar(player).ifPresent(ignore -> e.setCancelled(true));
    }

}
