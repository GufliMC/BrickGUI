package com.guflimc.mastergui.bukkit.api;

import com.guflimc.mastergui.bukkit.menu.BukkitMenuItem;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;
import java.util.function.Function;

public interface IBukkitPaginatedMenuBuilder {

    IBukkitPaginatedMenuBuilder withTitle(Function<Integer, String> supplier);

    IBukkitPaginatedMenuBuilder withBackItem(ItemStack itemStack);

    IBukkitPaginatedMenuBuilder withNextItem(ItemStack itemStack);

    IBukkitPaginatedMenuBuilder withItems(int size, Function<Integer, BukkitMenuItem> supplier);

    IBukkitPaginatedMenuBuilder withHotbarItem(int index, ItemStack itemStack, Consumer<InventoryClickEvent> consumer);

    IBukkitPaginatedMenuBuilder withHotbarItem(int index, ItemStack itemStack, Function<InventoryClickEvent, Boolean> consumer);

    IBukkitMenu build();

}
