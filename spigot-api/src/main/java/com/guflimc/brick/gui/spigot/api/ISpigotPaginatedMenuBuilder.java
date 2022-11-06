package com.guflimc.brick.gui.spigot.api;

import com.guflimc.brick.gui.spigot.menu.SpigotMenuItem;
import net.kyori.adventure.text.Component;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Function;

public interface ISpigotPaginatedMenuBuilder {

    ISpigotPaginatedMenuBuilder withTitle(@NotNull Function<Integer, String> supplier);

    ISpigotPaginatedMenuBuilder withTitle(@NotNull String title);

    ISpigotPaginatedMenuBuilder withTitle(@NotNull Component title);

    ISpigotPaginatedMenuBuilder withBackItem(@NotNull ItemStack itemStack);

    ISpigotPaginatedMenuBuilder withBackItemName(@NotNull Component text);

    ISpigotPaginatedMenuBuilder withBackItemName(@NotNull String text);

    ISpigotPaginatedMenuBuilder withNextItem(@NotNull ItemStack itemStack);

    ISpigotPaginatedMenuBuilder withNextItemName(@NotNull Component text);

    ISpigotPaginatedMenuBuilder withNextItemName(@NotNull String text);

    ISpigotPaginatedMenuBuilder withItems(int size, @NotNull Function<Integer, SpigotMenuItem> supplier);

    ISpigotPaginatedMenuBuilder withHotbarItem(int index, @NotNull ItemStack itemStack, @NotNull Consumer<InventoryClickEvent> consumer);

    ISpigotPaginatedMenuBuilder withHotbarItem(int index, @NotNull ItemStack itemStack, @NotNull Function<InventoryClickEvent, Boolean> consumer);

    ISpigotMenu build();

}
