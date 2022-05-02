package com.guflimc.guibuilder.api;

import com.gufli.bookshelf.bukkit.api.entity.BukkitPlayer;
import com.guflimc.guibuilder.api.menu.Menu;
import com.guflimc.guibuilder.api.menu.MenuItem;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class MenuBuilder<ITEMT, MENUITEMT extends MenuItem<ITEMT, ?>, MENUT extends Menu<?, ?, ?, MENUITEMT>> {

    private final static MenuScheme[] SCHEMES = new MenuScheme[] {
            MenuScheme.of("000000000", "000010000"), // 1 item
            MenuScheme.of("000000000", "000101000"), // 2 items
            MenuScheme.of("000000000", "001010100"), // 3 items
            MenuScheme.of("000000000", "010101010"), // 4 items
            MenuScheme.of("000000000", "001000100", "000010000", "001000100"), // 5 items
            MenuScheme.of("000000000", "001010100", "001010100"), // 6 items
            MenuScheme.of("000000000", "010101010", "001010100"), // 7 items
            MenuScheme.of("000000000", "010101010", "010101010"), // 8 items
            MenuScheme.of("000000000", "010101010", "001010100", "010101010"), // 9 items
            MenuScheme.of("000000000", "010101010", "000101000", "010101010") // 10 items
    };

    //

    public MenuBuilder() {}

    //

    protected final List<MENUITEMT> items = new ArrayList<>();
    protected final Map<Integer, MENUITEMT> hotbar = new HashMap<>();

    //

    public final

    public final MenuBuilder withItem(ItemStack item) {
        items.add(new MenuItem(item));
        return this;
    }

    public final MenuBuilder withItems(ItemStack... items) {
        return withItems(Arrays.asList(items));
    }

    public final MenuBuilder withItems(Iterable<ItemStack> items) {
        for ( ItemStack item : items ) {
            this.items.add(new MenuItem(item));
        }
        return this;
    }

    public final MenuBuilder withItem(ItemStack item, BiFunction<BukkitPlayer, ClickType, Boolean> cb) {
        items.add(new MenuItem(item, cb));
        return this;
    }

    public final MenuBuilder withItem(ItemStack item, BiConsumer<BukkitPlayer, ClickType> cb) {
        items.add(new MenuItem(item, (player, clickType) -> {
            cb.accept(player, clickType);
            return true;
        }));
        return this;
    }

    public final MenuBuilder withHotbarItem(int slot, ItemStack item) {
        hotbar.put(slot, new MenuItem(item));
        return this;
    }

    public final MenuBuilder withHotbarItem(int slot, ItemStack item, BiFunction<BukkitPlayer, ClickType, Boolean> cb) {
        hotbar.put(slot, new MenuItem(item, cb));
        return this;
    }

    public Menu build() {
        if ( items.size() > 10 ) {
            throw new UnsupportedOperationException("More than 10 items is not supported.");
        }

        MenuScheme scheme = SCHEMES[items.size() - 1];
        int size = scheme.getRows() + 1;

        if ( !hotbar.isEmpty() ) {
            size += 1;
        }

        Menu menu = new Menu(size, title);

        // fill items
        int index = 0;
        for ( int slot : scheme.getSlots() ) {
            menu.setItem(slot, items.get(index));
            index++;
        }

        // fill hotbar
        for ( int slot : hotbar.keySet() ) {
            if ( slot < 0 || slot >= 9 ) {
                continue;
            }

            menu.setItem(((size-1) * 9) + slot, hotbar.get(slot));
        }

        return menu;
    }
    
}
