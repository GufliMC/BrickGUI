package com.guflimc.brick.gui.spigot.api;

import com.guflimc.brick.gui.api.click.ClickFunction;
import com.guflimc.brick.gui.spigot.menu.SpigotISimpleMenuItem;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;
import java.util.function.Function;

public interface ISpigotPaginatedMenuBuilder {

    ISpigotPaginatedMenuBuilder withTitle(Function<Integer, String> supplier);

    ISpigotPaginatedMenuBuilder withBackItem(ItemStack itemStack);

    ISpigotPaginatedMenuBuilder withNextItem(ItemStack itemStack);

    ISpigotPaginatedMenuBuilder withItems(int size, Function<Integer, SpigotISimpleMenuItem> supplier);

    ISpigotPaginatedMenuBuilder withHotbarItem(int index, ItemStack itemStack, Consumer<InventoryClickEvent> consumer);

    ISpigotPaginatedMenuBuilder withHotbarItem(int index, ItemStack itemStack, ClickFunction<InventoryClickEvent> consumer);

    ISpigotMenu build();

}
