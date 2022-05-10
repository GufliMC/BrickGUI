package com.guflimc.brick.gui.spigot.menu;

import com.guflimc.brick.gui.api.menu.MenuItem;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class SpigotMenuItem implements MenuItem {

    private final ItemStack handle;
    private final Consumer<InventoryClickEvent> callback;

    public SpigotMenuItem(ItemStack handle, Consumer<InventoryClickEvent> callback) {
        this.handle = handle;
        this.callback = callback;
    }

    public SpigotMenuItem(ItemStack handle) {
        this(handle, null);
    }

    @Override
    public ItemStack handle() {
        return handle;
    }

    @Override
    public Consumer<InventoryClickEvent> callback() {
        return callback;
    }
}
