package com.guflimc.brick.gui.minestom.listener;

import com.guflimc.brick.gui.minestom.MinestomRegistry;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.inventory.InventoryClickEvent;
import net.minestom.server.event.player.PlayerBlockInteractEvent;
import net.minestom.server.event.player.PlayerEntityInteractEvent;

public class MinestomHotbarListener {

    public final MinestomRegistry registry;

    public MinestomHotbarListener(MinestomRegistry registry) {
        this.registry = registry;
        GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();
        eventHandler.addListener(InventoryClickEvent.class, this::onInventoryClick);
        eventHandler.addListener(PlayerBlockInteractEvent.class, this::onBlockInteract);
        eventHandler.addListener(PlayerEntityInteractEvent.class, this::onEntityInteract);
    }

    public void onInventoryClick(InventoryClickEvent event) {
        registry.findHotbar(event.getPlayer())
                .ifPresent(gui -> gui.dispatchInventoryClick(event));
    }

    public void onBlockInteract(PlayerBlockInteractEvent event) {
        registry.findHotbar(event.getPlayer())
                .ifPresent(hotbar -> hotbar.dispatchInteract(event));
    }

    public void onEntityInteract(PlayerEntityInteractEvent event) {
        registry.findHotbar(event.getPlayer())
                .ifPresent(hotbar -> hotbar.dispatchInteractEntity(event));
    }

    // TODO
//    public void onItemMove(InventoryMoveItemEvent e) {
//        if ( e.getSource().getHolder() == null || !(e.getSource().getHolder() instanceof Player player) ) return;
//        registry.findHotbar(player).ifPresent(ignore -> e.setCancelled(true));
//    }

}
