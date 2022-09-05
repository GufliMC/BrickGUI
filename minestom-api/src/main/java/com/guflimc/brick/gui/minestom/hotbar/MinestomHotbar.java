package com.guflimc.brick.gui.minestom.hotbar;

import com.guflimc.brick.gui.api.menu.ItemContainer;
import com.guflimc.brick.gui.minestom.api.IMinestomHotbar;
import com.guflimc.brick.gui.minestom.MinestomRegistry;
import net.minestom.server.entity.Player;
import net.minestom.server.event.inventory.InventoryClickEvent;
import net.minestom.server.event.player.PlayerBlockInteractEvent;
import net.minestom.server.event.player.PlayerEntityInteractEvent;
import net.minestom.server.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class MinestomHotbar extends ItemContainer<MinestomHotbarItemISimple> implements IMinestomHotbar {

    private final List<Consumer<PlayerBlockInteractEvent>> interactListeners = new ArrayList<>();
    private final List<Consumer<PlayerEntityInteractEvent>> interactEntityListeners = new ArrayList<>();
    private final List<Consumer<InventoryClickEvent>> inventoryClickListeners = new ArrayList<>();

    private final MinestomRegistry registry;

    public MinestomHotbar(MinestomRegistry registry) {
        super(MinestomHotbarItemISimple.class, 9);
        this.registry = registry;
    }

    @Override
    public void removeItem(int index) {
        super.removeItem(index);
    }

    @Override
    public void setItem(int index, MinestomHotbarItemISimple item) {
        if (item == null) {
            super.removeItem(index);
            return;
        }

        super.setItem(index, item);
    }

    // BukkitMenu

    @Override
    public void setItem(int index, ItemStack itemStack) {
        this.setItem(index, new MinestomHotbarItemISimple(itemStack));
    }

    @Override
    public void setItem(int index, ItemStack itemStack, Consumer<Player> onClick) {
        this.setItem(index, new MinestomHotbarItemISimple(itemStack, onClick));
    }

    @Override
    public void setItem(int index, ItemStack itemStack,
                        Consumer<PlayerBlockInteractEvent> interact,
                        Consumer<PlayerEntityInteractEvent> interactEntity,
                        Consumer<InventoryClickEvent> click) {
        this.setItem(index, new MinestomHotbarItemISimple(itemStack, interact, interactEntity, click));
    }

    @Override
    public ItemStack[] items() {
        return Arrays.stream(super.items).map(MinestomHotbarItemISimple::handle).toArray(ItemStack[]::new);
    }

    @Override
    public void open(Player player) {
        registry.register(player, this);
    }

    // events

    @Override
    public void addInteractListener(Consumer<PlayerBlockInteractEvent> listener) {
        interactListeners.add(listener);
    }

    @Override
    public void addInteractEntityListener(Consumer<PlayerEntityInteractEvent> listener) {
        interactEntityListeners.add(listener);
    }

    @Override
    public void addInventoryClickListener(Consumer<InventoryClickEvent> listener) {
        inventoryClickListeners.add(listener);
    }

    //

    public void dispatchInteract(PlayerBlockInteractEvent event) {
        int slot = event.getPlayer().getHeldSlot();
        if (slot < items.length) {
            MinestomHotbarItemISimple item = items[slot];
            if (item != null && item.callback() != null) {
                item.callback().accept(event);
            }
        }

        interactListeners.forEach(listener -> listener.accept(event));
    }

    public void dispatchInteractEntity(PlayerEntityInteractEvent event) {
        int slot = event.getPlayer().getHeldSlot();
        if (slot < items.length) {
            MinestomHotbarItemISimple item = super.items[slot];
            if (item != null && item.entityInteract() != null) {
                item.entityInteract().accept(event);
            }
        }

        interactEntityListeners.forEach(listener -> listener.accept(event));
    }

    public void dispatchInventoryClick(InventoryClickEvent event) {
        int slot = event.getSlot();

        // TODO check if inventory is player's inventory
        if (slot < super.items.length) {
            MinestomHotbarItemISimple item = super.items[slot];
            if (item != null && item.click() != null) {
                item.click().accept(event);
            }
        }

        inventoryClickListeners.forEach(listener -> listener.accept(event));
    }
}
