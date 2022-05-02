package com.guflimc.guibuilder.bukkit;

import com.guflimc.guibuilder.api.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class BukkitMenu extends Menu<HumanEntity, HumanEntity, InventoryClickEvent, BukkitItem> {

    final Inventory inventory;

    public BukkitMenu(int size, String title) {
        super(size);
        this.inventory = Bukkit.createInventory(null, size, title);
    }

    public BukkitMenu(int size) {
        this(size, null);
    }

    @Override
    public void removeItem(int index) {
        super.removeItem(index);
        inventory.setItem(index, null);
    }

    @Override
    public void setItem(int index, BukkitItem item) {
        super.setItem(index, item);
        inventory.setItem(index, item.handle());
    }

    //

    public void open(HumanEntity player) {
        BukkitGUIBuilder.menus.put(player, this);
        player.openInventory(inventory);
        _dispatchOpen(player);
    }

    //

    void _dispatchClose(HumanEntity player) {
        super.dispatchClose(player);
    }

    void _dispatchOpen(HumanEntity player) {
        super.dispatchOpen(player);
    }

    void _dispatchClick(InventoryClickEvent event) {
        super.dispatchClick(event);
    }
}
