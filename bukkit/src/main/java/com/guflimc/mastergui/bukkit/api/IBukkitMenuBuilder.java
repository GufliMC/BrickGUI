package com.guflimc.mastergui.bukkit.api;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;
import java.util.function.Function;

public interface IBukkitMenuBuilder {

    IBukkitMenuBuilder withTitle(String title);

    IBukkitMenuBuilder withItem(ItemStack itemStack);

    IBukkitMenuBuilder withItem(ItemStack itemStack, Consumer<InventoryClickEvent> consumer);

    IBukkitMenuBuilder withItem(ItemStack itemStack, Function<InventoryClickEvent, Boolean> consumer);

    IBukkitMenuBuilder withHotbarItem(int index, ItemStack itemStack, Consumer<InventoryClickEvent> consumer);

    IBukkitMenuBuilder withHotbarItem(int index, ItemStack itemStack, Function<InventoryClickEvent, Boolean> consumer);

    IBukkitMenu build();

}
