package com.guflimc.brick.gui.spigot.api;

import com.guflimc.brick.gui.api.click.ClickFunction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public interface ISpigotMenuBuilder {

    ISpigotMenuBuilder withTitle(String title);

    ISpigotMenuBuilder withItem(ItemStack itemStack);

    ISpigotMenuBuilder withItem(ItemStack itemStack, Consumer<InventoryClickEvent> consumer);

    ISpigotMenuBuilder withItem(ItemStack itemStack, ClickFunction<InventoryClickEvent> consumer);

    ISpigotMenuBuilder withHotbarItem(int index, ItemStack itemStack, Consumer<InventoryClickEvent> consumer);

    ISpigotMenuBuilder withHotbarItem(int index, ItemStack itemStack, ClickFunction<InventoryClickEvent> consumer);

    ISpigotMenu build();

}
