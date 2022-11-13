package com.guflimc.brick.gui.spigot.api;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Function;

public interface ISpigotMenuRowBuilder {

    ISpigotMenuRowBuilder withItem(@NotNull ItemStack itemStack);

    ISpigotMenuRowBuilder withItem(@NotNull ItemStack itemStack, @NotNull Consumer<InventoryClickEvent> consumer);

    ISpigotMenuRowBuilder withItem(@NotNull ItemStack itemStack, @NotNull Function<InventoryClickEvent, Boolean> consumer);

    void fill(ISpigotMenu menu, int row);

}
