package com.guflimc.mastergui.bukkit.menu;

import com.guflimc.mastergui.api.menu.MenuItem;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class BukkitMenuItem implements MenuItem {

    private final ItemStack handle;
    private final Consumer<InventoryClickEvent> callback;

    public BukkitMenuItem(ItemStack handle, Consumer<InventoryClickEvent> callback) {
        this.handle = handle;
        this.callback = callback;
    }

    public BukkitMenuItem(ItemStack handle) {
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
