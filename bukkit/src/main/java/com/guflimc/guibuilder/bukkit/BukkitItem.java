package com.guflimc.guibuilder.bukkit;

import com.guflimc.guibuilder.api.menu.MenuItem;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class BukkitItem extends MenuItem<ItemStack, InventoryClickEvent> {

    public BukkitItem(ItemStack handle, Consumer<InventoryClickEvent> callback) {
        super(handle, callback);
    }

    public BukkitItem(ItemStack handle) {
        super(handle);
    }

}
