package com.guflimc.brick.gui.spigot.menu;

import com.guflimc.brick.gui.spigot.api.ISpigotMenu;
import com.guflimc.brick.gui.api.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class SpigotMenu extends Menu<SpigotMenuItem> implements ISpigotMenu {

    public static Consumer<InventoryClickEvent> soundWrapper(Function<InventoryClickEvent, Boolean> func) {
        return (event) -> {
            Sound sound = func.apply(event) ? Sound.UI_BUTTON_CLICK : Sound.ENTITY_VILLAGER_NO;
            if (event.getWhoClicked() instanceof Player p) {
                p.playSound(p.getEyeLocation(), sound, 1f, 1f);
            }
        };
    }

    //

    public final Inventory inventory;
    private final SpigotRegistry registry;

    private final List<Consumer<HumanEntity>> closeListeners = new ArrayList<>();
    private final List<Consumer<HumanEntity>> openListeners = new ArrayList<>();
    private final List<Consumer<InventoryClickEvent>> clickListeners = new ArrayList<>();

    public SpigotMenu(SpigotRegistry registry, int size, String title) {
        super(SpigotMenuItem.class, size);
        this.registry = registry;

        if ( title == null ) {
            title = "";
        }
        this.inventory = Bukkit.createInventory(null, size, title);
    }

    public SpigotMenu(SpigotRegistry registry, int size) {
        this(registry, size, null);
    }

    @Override
    public void removeItem(int index) {
        super.removeItem(index);
        inventory.setItem(index, null);
    }

    @Override
    public void setItem(int index, SpigotMenuItem item) {
        if ( item == null ) {
            super.removeItem(index);
            return;
        }

        super.setItem(index, item);
        inventory.setItem(index, item.handle());
    }

    // BukkitMenu

    @Override
    public void setItem(int index, ItemStack itemStack) {
        this.setItem(index, new SpigotMenuItem(itemStack));
    }

    @Override
    public void setItem(int index, ItemStack itemStack, Consumer<InventoryClickEvent> onClick) {
        this.setItem(index, new SpigotMenuItem(itemStack, onClick));
    }

    @Override
    public void setItem(int index, ItemStack itemStack, Function<InventoryClickEvent, Boolean> onClick) {
        this.setItem(index, new SpigotMenuItem(itemStack, soundWrapper(onClick)));
    }

    @Override
    public ItemStack[] items() {
        return Arrays.stream(super.items).map(SpigotMenuItem::handle).toArray(ItemStack[]::new);
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
        if ( event.getClickedInventory() == event.getInventory() ) {
            event.setCancelled(true);

            SpigotMenuItem item = super.items[event.getSlot()];
            if (item != null && item.callback() != null) {
                item.callback().accept(event);
            }
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
