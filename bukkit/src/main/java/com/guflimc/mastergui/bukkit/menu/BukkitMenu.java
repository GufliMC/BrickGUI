package com.guflimc.mastergui.bukkit.menu;

import com.guflimc.mastergui.api.menu.Menu;
import com.guflimc.mastergui.bukkit.api.IBukkitMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class BukkitMenu extends Menu<BukkitMenuItem> implements IBukkitMenu {

    public final Inventory inventory;
    private final BukkitRegistry registry;

    private final List<Consumer<HumanEntity>> closeListeners = new ArrayList<>();
    private final List<Consumer<HumanEntity>> openListeners = new ArrayList<>();
    private final List<Consumer<InventoryClickEvent>> clickListeners = new ArrayList<>();

    public BukkitMenu(BukkitRegistry registry, int size, String title) {
        super(BukkitMenuItem.class, size);
        this.registry = registry;
        this.inventory = Bukkit.createInventory(null, size, title);
    }

    public BukkitMenu(BukkitRegistry registry, int size) {
        this(registry, size, null);
    }

    @Override
    public void removeItem(int index) {
        super.removeItem(index);
        inventory.setItem(index, null);
    }

    @Override
    public void setItem(int index, BukkitMenuItem item) {
        super.setItem(index, item);
        inventory.setItem(index, item.handle());
    }

    // BukkitMenu

    @Override
    public void setItem(int index, ItemStack itemStack) {
        this.setItem(index, new BukkitMenuItem(itemStack));
    }

    @Override
    public void setItem(int index, ItemStack itemStack, Consumer<InventoryClickEvent> onClick) {
        this.setItem(index, new BukkitMenuItem(itemStack, onClick));
    }

    @Override
    public ItemStack[] items() {
        return Arrays.stream(super.items).map(BukkitMenuItem::handle).toArray(ItemStack[]::new);
    }

    @Override
    public void open(HumanEntity entity) {
        entity.openInventory(inventory);
        dispatchOpen(entity);
        registry.register(entity, this);
    }

    // events

    @Override
    public void addOpenListener(Consumer<HumanEntity> listener) {
        openListeners.add(listener);
    }

    @Override
    public void addCloseListener(Consumer<HumanEntity> listener) {
        closeListeners.add(listener);
    }

    @Override
    public void addClickListener(Consumer<InventoryClickEvent> listener) {
        clickListeners.add(listener);
    }

    //

    public void dispatchClick(InventoryClickEvent event) {
        BukkitMenuItem item = super.items[event.getSlot()];
        if ( item != null && item.callback() != null) {
            item.callback().accept(event);
        }

        clickListeners.forEach(listener -> listener.accept(event));
    }

    public void dispatchClose(HumanEntity entity) {
        closeListeners.forEach(listener -> listener.accept(entity));
    }

    public void dispatchOpen(HumanEntity entity) {
        openListeners.forEach(listener -> listener.accept(entity));
    }

}
