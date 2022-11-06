package com.guflimc.brick.gui.spigot.api;

import com.guflimc.brick.gui.spigot.builder.SpigotMenuBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Function;

public interface ISpigotMenuBuilder {

    ISpigotMenuBuilder withTitle(@NotNull String title);

    SpigotMenuBuilder withTitle(@NotNull Component title);

    ISpigotMenuBuilder withItem(@NotNull ItemStack itemStack);

    ISpigotMenuBuilder withItem(@NotNull ItemStack itemStack, @NotNull Consumer<InventoryClickEvent> consumer);

    ISpigotMenuBuilder withItem(@NotNull ItemStack itemStack, @NotNull Function<InventoryClickEvent, Boolean> consumer);

    ISpigotMenuBuilder withHotbarItem(int index, @NotNull ItemStack itemStack, @NotNull Consumer<InventoryClickEvent> consumer);

    ISpigotMenuBuilder withHotbarItem(int index, @NotNull ItemStack itemStack, @NotNull Function<InventoryClickEvent, Boolean> consumer);

    ISpigotMenu build();

}
