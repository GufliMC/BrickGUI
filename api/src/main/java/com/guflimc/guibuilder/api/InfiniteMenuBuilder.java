package com.guflimc.guibuilder.api;

import com.gufli.bookshelf.bukkit.api.entity.BukkitPlayer;
import com.gufli.bookshelf.bukkit.api.item.ItemStackBuilder;
import com.guflimc.guibuilder.api.menu.Menu;
import com.guflimc.guibuilder.api.menu.MenuItem;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class InfiniteMenuBuilder {

    public static InfiniteMenuBuilder create() {
        return new InfiniteMenuBuilder();
    }

    public static InfiniteMenuBuilder create(String title) {
        return new InfiniteMenuBuilder().withTitle(title);
    }

    //

    private String title;

    private ItemStack previousItem;
    private ItemStack nextItem;

    private int itemAmount;
    private Function<Integer, MenuItem> supplier;

    protected final Map<Integer, MenuItem> hotbar = new HashMap<>();

    private InfiniteMenuBuilder() {
        previousItem = ItemStackBuilder.of(Material.ARROW)
                .withName(ChatColor.GREEN + "Previous page")
                .build();

        nextItem = ItemStackBuilder.of(Material.ARROW)
                .withName(ChatColor.GREEN + "Next page")
                .build();
    }

    //

    public final InfiniteMenuBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public InfiniteMenuBuilder withNextItem(ItemStack item) {
        this.nextItem = item;
        return this;
    }

    public InfiniteMenuBuilder withPreviousItem(ItemStack item) {
        this.previousItem = item;
        return this;
    }

    public InfiniteMenuBuilder withItemAmount(int amount) {
        this.itemAmount = amount;
        return this;
    }

    public InfiniteMenuBuilder withItemSupplier(Function<Integer, MenuItem> supplier) {
        this.supplier = supplier;
        return this;
    }

    public final InfiniteMenuBuilder withHotbarItem(int slot, ItemStack item) {
        hotbar.put(slot, new MenuItem(item));
        return this;
    }

    public final InfiniteMenuBuilder withHotbarItem(int slot, ItemStack item, BiFunction<BukkitPlayer, ClickType, Boolean> cb) {
        hotbar.put(slot, new MenuItem(item, cb));
        return this;
    }

    public Menu build() {
        if ( supplier == null ) {
            throw new IllegalStateException("Supplier may not be null.");
        }

        int size = (itemAmount / 9) + (itemAmount % 9 > 0 ? 1 : 0);

        if ( !hotbar.isEmpty() ) {
            size += 2;
        }

        // no need for pagination
        if ( size <= 6 ) {
            Menu menu = new Menu(size * 9, title);
            for ( int i = 0; i < itemAmount; i++ ) {
                menu.setItem(i, supplier.apply(i));
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

        return page(0);
    }

    private Menu page(int page) {
        int rows = (itemAmount / 9) + (itemAmount % 9 > 0 ? 1 : 0);
        int pages = (rows / 4) + (rows % 4 > 0 ? 1 : 0);

        Menu menu = new Menu(54, title);

        // fill with items
        int offset = page * 36;
        for ( int i = 0; i < Math.min(itemAmount - offset, 36); i++ ) {
            menu.setItem(i, supplier.apply(offset + i));
        }

        // fill hotbar
        for ( int slot : hotbar.keySet() ) {
            if ( slot >= 9 ) {
                continue;
            }

            menu.setItem(45 + slot, hotbar.get(slot));
        }

        if ( page > 0 ) {
            menu.setItem(47, new MenuItem(previousItem, (player, clickType) -> {
                player.openMenu(page(page - 1));
                return true;
            }));
        }

        if ( page < pages - 1 ) {
            menu.setItem(51, new MenuItem(nextItem, (player, clickType) -> {
                player.openMenu(page(page + 1));
                return true;
            }));
        }

        return menu;
    }

}
