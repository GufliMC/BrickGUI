package com.guflimc.brick.gui.spigot.builder;

import com.guflimc.brick.gui.api.builder.MenuRowBuilder;
import com.guflimc.brick.gui.api.menu.Menu;
import com.guflimc.brick.gui.spigot.api.ISpigotMenu;
import com.guflimc.brick.gui.spigot.api.ISpigotMenuRowBuilder;
import com.guflimc.brick.gui.spigot.menu.SpigotMenu;
import com.guflimc.brick.gui.spigot.menu.SpigotMenuItem;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Function;

public class SpigotMenuRowBuilder extends MenuRowBuilder<SpigotMenuItem> implements ISpigotMenuRowBuilder {

    public SpigotMenuRowBuilder() {
        super(SpigotMenuItem.class);
    }

    @Override
    public SpigotMenuRowBuilder withItem(@NotNull ItemStack itemStack) {
        super.withItem(new SpigotMenuItem(itemStack));
        return this;
    }

    @Override
    public SpigotMenuRowBuilder withItem(@NotNull ItemStack itemStack, @NotNull Consumer<InventoryClickEvent> consumer) {
        super.withItem(new SpigotMenuItem(itemStack, consumer));
        return this;
    }

    @Override
    public SpigotMenuRowBuilder withItem(@NotNull ItemStack itemStack, @NotNull Function<InventoryClickEvent, Boolean> consumer) {
        super.withItem(new SpigotMenuItem(itemStack, SpigotMenu.soundWrapper(consumer)));
        return this;
    }

    @Override
    public void fill(ISpigotMenu menu, int row) {
        super.fill((Menu) menu, row);
    }

}
