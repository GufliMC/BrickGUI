package com.guflimc.brick.gui.minestom.builder;

import com.guflimc.brick.gui.common.builder.SimplePaginatedMenuBuilder;
import com.guflimc.brick.gui.api.click.ClickFunction;
import com.guflimc.brick.gui.api.click.ClickResult;
import com.guflimc.brick.gui.minestom.api.IMinestomPaginatedMenuBuilder;
import com.guflimc.brick.gui.minestom.item.ItemStackBuilder;
import com.guflimc.brick.gui.minestom.menu.MinestomISimpleMenu;
import com.guflimc.brick.gui.minestom.menu.MinestomISimpleMenuItem;
import com.guflimc.brick.gui.minestom.MinestomRegistry;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.event.inventory.InventoryClickEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

import java.util.function.Consumer;
import java.util.function.Function;

public class MinestomPaginatedMenuBuilder extends SimplePaginatedMenuBuilder<MinestomISimpleMenuItem> implements IMinestomPaginatedMenuBuilder {

    private final MinestomRegistry registry;

    private Function<Integer, String> title;

    private ItemStack back = ItemStackBuilder.of(Material.ARROW)
            .withName(Component.text("Back", NamedTextColor.GREEN))
            .build();

    private ItemStack next = ItemStackBuilder.of(Material.ARROW)
            .withName(Component.text("Next", NamedTextColor.GREEN))
            .build();

    public MinestomPaginatedMenuBuilder(MinestomRegistry registry) {
        super(MinestomISimpleMenuItem.class);
        this.registry = registry;
    }

    @Override
    public MinestomPaginatedMenuBuilder withItems(int size, Function<Integer, MinestomISimpleMenuItem> supplier) {
        super.withItems(size, supplier);
        return this;
    }

    @Override
    public MinestomPaginatedMenuBuilder withTitle(Function<Integer, String> supplier) {
        this.title = supplier;
        return this;
    }

    @Override
    public MinestomPaginatedMenuBuilder withBackItem(ItemStack itemStack) {
        this.back = itemStack;
        return this;
    }

    @Override
    public MinestomPaginatedMenuBuilder withNextItem(ItemStack itemStack) {
        this.next = itemStack;
        return this;
    }

    @Override
    public MinestomPaginatedMenuBuilder withHotbarItem(int index, ItemStack itemStack, Consumer<InventoryClickEvent> consumer) {
        return withHotbarItem(index, itemStack, ClickFunction.from(consumer));
    }

    @Override
    public MinestomPaginatedMenuBuilder withHotbarItem(int index, ItemStack itemStack, ClickFunction<InventoryClickEvent> consumer) {
        this.withHotbarItem(index, new MinestomISimpleMenuItem(itemStack, consumer));
        return this;
    }

    //

    @Override
    public MinestomISimpleMenu build() {
        return page(0);
    }

    private MinestomISimpleMenu page(int page) {
        MinestomISimpleMenuItem back = new MinestomISimpleMenuItem(this.back, (event) -> {
            page(page - 1).open(event.getPlayer());
            return ClickResult.SUCCESS;
        });
        MinestomISimpleMenuItem next = new MinestomISimpleMenuItem(this.next, (event) -> {
            page(page + 1).open(event.getPlayer());
            return ClickResult.SUCCESS;
        });

        MinestomISimpleMenuItem[] items = super.compile(page, back, next);

        String title = null;
        if (this.title != null) {
            title = this.title.apply(page);
        }

        MinestomISimpleMenu menu = new MinestomISimpleMenu(registry, items.length, title);
        for (int i = 0; i < items.length; i++) {
            menu.setItem(i, items[i]);
        }

        return menu;
    }
}
