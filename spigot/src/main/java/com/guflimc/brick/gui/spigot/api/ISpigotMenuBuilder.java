package com.guflimc.brick.gui.spigot.api;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;
import java.util.function.Function;

public interface ISpigotMenuBuilder {

    ISpigotMenuBuilder withTitle(String title);

    ISpigotMenuBuilder withItem(ItemStack itemStack);

    ISpigotMenuBuilder withItem(ItemStack itemStack, Consumer<InventoryClickEvent> consumer);

    ISpigotMenuBuilder withItem(ItemStack itemStack, Function<InventoryClickEvent, Boolean> consumer);

    ISpigotMenuBuilder withHotbarItem(int index, ItemStack itemStack, Consumer<InventoryClickEvent> consumer);

    ISpigotMenuBuilder withHotbarItem(int index, ItemStack itemStack, Function<InventoryClickEvent, Boolean> consumer);

    ISpigotMenu build();

}
