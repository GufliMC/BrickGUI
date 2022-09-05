package com.guflimc.brick.gui.spigot.builder;

import com.guflimc.brick.gui.api.click.ClickFunction;
import com.guflimc.brick.gui.spigot.api.ISpigotMenuBuilder;
import com.guflimc.brick.gui.api.builder.MenuBuilder;
import com.guflimc.brick.gui.spigot.menu.SpigotISimpleMenu;
import com.guflimc.brick.gui.spigot.menu.SpigotISimpleMenuItem;
import com.guflimc.brick.gui.spigot.menu.SpigotRegistry;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;
import java.util.function.Function;

public class SpigotMenuBuilder extends MenuBuilder<SpigotISimpleMenuItem> implements ISpigotMenuBuilder {

    private final SpigotRegistry registry;

    private String title = null;

    public SpigotMenuBuilder(SpigotRegistry registry) {
        super(SpigotISimpleMenuItem.class);
        this.registry =registry;
    }


    @Override
    public SpigotMenuBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public SpigotMenuBuilder withItem(ItemStack itemStack) {
        super.withItem(new SpigotISimpleMenuItem(itemStack));
        return this;
    }

    @Override
    public SpigotMenuBuilder withItem(ItemStack itemStack, Consumer<InventoryClickEvent> consumer) {
        super.withItem(new SpigotISimpleMenuItem(itemStack, consumer));
        return this;
    }

    @Override
    public SpigotMenuBuilder withItem(ItemStack itemStack, Function<InventoryClickEvent, Boolean> consumer) {
        super.withItem(new SpigotISimpleMenuItem(itemStack, SpigotISimpleMenu.soundWrapper(consumer)));
        return this;
    }

    @Override
    public SpigotMenuBuilder withHotbarItem(int index, ItemStack itemStack, Consumer<InventoryClickEvent> consumer) {
        super.withHotbarItem(index, new SpigotISimpleMenuItem(itemStack, consumer));
        return this;
    }

    @Override
    public SpigotMenuBuilder withHotbarItem(int index, ItemStack itemStack, ClickFunction<InventoryClickEvent> consumer) {
        this.withHotbarItem(index, new SpigotISimpleMenuItem(itemStack, consumer));
        return this;
    }

    //

    @Override
    public SpigotISimpleMenu build() {
        SpigotISimpleMenuItem[] items = super.compile();

        SpigotISimpleMenu menu = new SpigotISimpleMenu(registry, items.length, title);
        for ( int i = 0 ; i < items.length ; i++ ) {
           menu.setItem(i, items[i]);
        }

        return menu;
    }

}
