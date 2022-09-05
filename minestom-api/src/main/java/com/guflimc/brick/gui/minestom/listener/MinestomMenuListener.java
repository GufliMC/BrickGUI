package com.guflimc.brick.gui.minestom.listener;

import com.guflimc.brick.gui.minestom.MinestomRegistry;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.inventory.InventoryClickEvent;
import net.minestom.server.event.inventory.InventoryCloseEvent;
import net.minestom.server.event.inventory.InventoryOpenEvent;
import net.minestom.server.event.player.PlayerDisconnectEvent;

public class MinestomMenuListener {

    public final MinestomRegistry registry;

    public MinestomMenuListener(MinestomRegistry registry) {
        this.registry = registry;
        GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();
        eventHandler.addListener(PlayerDisconnectEvent.class, this::onQuit);
        eventHandler.addListener(InventoryClickEvent.class, this::onInventoryClick);
        eventHandler.addListener(InventoryCloseEvent.class, this::onClose);
    }

    public void onQuit(PlayerDisconnectEvent event) {
        registry.findMenu(event.getPlayer())
                .ifPresent(gui -> gui.dispatchClose(event.getPlayer()));
        registry.unregister(event.getPlayer());
    }

    public void onClose(InventoryCloseEvent event) {
        registry.findMenu(event.getPlayer())
                .filter(gui -> gui.inventory == event.getInventory())
                .ifPresent(gui -> gui.dispatchClose(event.getPlayer()));
        registry.unregister(event.getPlayer());
    }

    public void onInventoryClick(InventoryClickEvent event) {
        registry.findMenu(event.getPlayer())
                .filter(gui -> gui.inventory == event.getInventory())
                .ifPresent(gui -> gui.dispatchClick(event));
    }

}
