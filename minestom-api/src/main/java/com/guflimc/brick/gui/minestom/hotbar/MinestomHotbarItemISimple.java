package com.guflimc.brick.gui.minestom.hotbar;

import com.guflimc.brick.gui.api.menu.MenuItem;
import net.minestom.server.entity.Player;
import net.minestom.server.event.inventory.InventoryClickEvent;
import net.minestom.server.event.player.PlayerBlockInteractEvent;
import net.minestom.server.event.player.PlayerEntityInteractEvent;
import net.minestom.server.item.ItemStack;

import java.util.function.Consumer;

public class MinestomHotbarItemISimple implements MenuItem {

    private final ItemStack handle;
    private final Consumer<PlayerBlockInteractEvent> interact;
    private final Consumer<PlayerEntityInteractEvent> entityInteract;
    private final Consumer<InventoryClickEvent> click;

    public MinestomHotbarItemISimple(ItemStack handle,
                                     Consumer<PlayerBlockInteractEvent> interact,
                                     Consumer<PlayerEntityInteractEvent> entityInteract,
                                     Consumer<InventoryClickEvent> click) {
        this.handle = handle;
        this.interact = interact;
        this.entityInteract = entityInteract;
        this.click = click;
    }

    public MinestomHotbarItemISimple(ItemStack handle, Consumer<Player> consumer) {
        this(
                handle,
                (e) -> consumer.accept(e.getPlayer()),
                (e) -> consumer.accept(e.getPlayer()),
                (e) -> consumer.accept(e.getPlayer())
        );
    }

    public MinestomHotbarItemISimple(ItemStack handle) {
        this(handle, null, null, null);
    }

    @Override
    public ItemStack handle() {
        return handle;
    }

    @Override
    public Consumer<PlayerBlockInteractEvent> callback() {
        return interact;
    }

    public Consumer<PlayerEntityInteractEvent> entityInteract() {
        return entityInteract;
    }

    public Consumer<InventoryClickEvent> click() {
        return click;
    }
}
