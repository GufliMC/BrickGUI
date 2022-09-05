package com.guflimc.brick.gui.minestom.api;

import com.guflimc.brick.gui.api.click.ClickFunction;
import com.guflimc.brick.gui.minestom.menu.MinestomISimpleMenuItem;
import net.minestom.server.event.inventory.InventoryClickEvent;
import net.minestom.server.item.ItemStack;

import java.util.function.Consumer;
import java.util.function.Function;

public interface IMinestomPaginatedMenuBuilder {

    IMinestomPaginatedMenuBuilder withTitle(Function<Integer, String> supplier);

    IMinestomPaginatedMenuBuilder withBackItem(ItemStack itemStack);

    IMinestomPaginatedMenuBuilder withNextItem(ItemStack itemStack);

    IMinestomPaginatedMenuBuilder withItems(int size, Function<Integer, MinestomISimpleMenuItem> supplier);

    IMinestomPaginatedMenuBuilder withHotbarItem(int index, ItemStack itemStack, Consumer<InventoryClickEvent> consumer);

    IMinestomPaginatedMenuBuilder withHotbarItem(int index, ItemStack itemStack, ClickFunction<InventoryClickEvent> consumer);

    IMinestomMenu build();

}
