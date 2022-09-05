package com.guflimc.brick.gui.spigot.menu;

import com.guflimc.brick.gui.api.click.ClickFunction;
import com.guflimc.brick.gui.api.menu.MenuItem;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class SpigotISimpleMenuItem implements MenuItem {

    private final ItemStack handle;
    private final ClickFunction<InventoryClickEvent> click;

    public SpigotISimpleMenuItem(ItemStack handle, ClickFunction<InventoryClickEvent> click) {
        this.handle = handle;
        this.click = click;
    }

    public SpigotISimpleMenuItem(ItemStack handle) {
        this(handle, null);
    }

    @Override
    public ItemStack handle() {
        return handle;
    }

    @Override
    public ClickFunction<InventoryClickEvent> click() {
        return click;
    }
}
