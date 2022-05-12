package com.guflimc.brick.gui.spigot.hotbar;

import com.guflimc.brick.gui.api.menu.Menu;
import com.guflimc.brick.gui.spigot.api.ISpigotHotbar;
import com.guflimc.brick.gui.spigot.menu.SpigotRegistry;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class SpigotHotbar extends Menu<SpigotHotbarItem> implements ISpigotHotbar {

    private final List<Consumer<PlayerInteractEvent>> interactListeners = new ArrayList<>();
    private final List<Consumer<PlayerInteractEntityEvent>> interactEntityListeners = new ArrayList<>();
    private final List<Consumer<InventoryClickEvent>> inventoryClickListeners = new ArrayList<>();

    private final SpigotRegistry registry;

    public SpigotHotbar(SpigotRegistry registry) {
        super(SpigotHotbarItem.class, 9);
        this.registry = registry;
    }

    @Override
    public void removeItem(int index) {
        super.removeItem(index);
    }

    @Override
    public void setItem(int index, SpigotHotbarItem item) {
        if ( item == null ) {
            super.removeItem(index);
            return;
        }

        super.setItem(index, item);
    }

    // BukkitMenu

    @Override
    public void setItem(int index, ItemStack itemStack) {
        this.setItem(index, new SpigotHotbarItem(itemStack));
    }

    @Override
    public void setItem(int index, ItemStack itemStack, Consumer<Player> onClick) {
        this.setItem(index, new SpigotHotbarItem(itemStack, onClick));
    }

    @Override
    public void setItem(int index, ItemStack itemStack,
                        Consumer<PlayerInteractEvent> interact,
                        Consumer<PlayerInteractEntityEvent> interactEntity,
                        Consumer<InventoryClickEvent> click) {
        this.setItem(index, new SpigotHotbarItem(itemStack, interact, interactEntity, click));
    }

    @Override
    public ItemStack[] items() {
        return Arrays.stream(super.items).map(SpigotHotbarItem::handle).toArray(ItemStack[]::new);
    }

    @Override
    public void open(Player player) {
        registry.register(player, this);
    }

    // events

    @Override
    public void addInteractListener(Consumer<PlayerInteractEvent> listener) {
        interactListeners.add(listener);
    }

    @Override
    public void addInteractEntityListener(Consumer<PlayerInteractEntityEvent> listener) {
        interactEntityListeners.add(listener);
    }

    @Override
    public void addInventoryClickListener(Consumer<InventoryClickEvent> listener) {
        inventoryClickListeners.add(listener);
    }

    //

    public void dispatchInteract(PlayerInteractEvent event) {
        int slot = event.getPlayer().getInventory().getHeldItemSlot();
        if ( slot < items.length ) {
            SpigotHotbarItem item = items[slot];
            if (item != null && item.callback() != null) {
                item.callback().accept(event);
            }
        }

        interactListeners.forEach(listener -> listener.accept(event));
    }

    public void dispatchInteractEntity(PlayerInteractEntityEvent event) {
        int slot = event.getPlayer().getInventory().getHeldItemSlot();
        if ( slot < items.length ) {
            SpigotHotbarItem item = super.items[slot];
            if (item != null && item.entityInteract() != null) {
                item.entityInteract().accept(event);
            }
        }

        interactEntityListeners.forEach(listener -> listener.accept(event));
    }

    public void dispatchInventoryClick(InventoryClickEvent event) {
        int slot = event.getSlot();
        if ( event.getInventory() instanceof PlayerInventory && slot < super.items.length) {
            SpigotHotbarItem item = super.items[slot];
            if (item != null && item.entityInteract() != null) {
                item.click().accept(event);
            }
        }

        inventoryClickListeners.forEach(listener -> listener.accept(event));
    }
}
