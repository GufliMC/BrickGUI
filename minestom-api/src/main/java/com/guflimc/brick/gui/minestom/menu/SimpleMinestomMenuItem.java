package com.guflimc.brick.gui.minestom.menu;

import com.guflimc.brick.gui.api.click.ClickFunction;
import com.guflimc.brick.gui.common.factory.ItemFactory;
import com.guflimc.brick.gui.common.menu.SimpleMenuItem;
import com.guflimc.brick.gui.minestom.api.MinestomMenuItem;
import net.minestom.server.event.inventory.InventoryClickEvent;
import net.minestom.server.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleMinestomMenuItem extends SimpleMenuItem<ItemStack, InventoryClickEvent>
        implements MinestomMenuItem {

    private SimpleMinestomMenuItem(@NotNull ItemStack handle, @Nullable ClickFunction<InventoryClickEvent> clickEvent) {
        super(handle, clickEvent);
    }

    public static Factory factory() {
        return Factory.INSTANCE;
    }

    public static SimpleMinestomMenuItem of(ItemStack itemStack) {
        return factory().apply(itemStack, null);
    }

    public static SimpleMinestomMenuItem of(ItemStack itemStack, ClickFunction<InventoryClickEvent> clickFunction) {
        return factory().apply(itemStack, clickFunction);
    }

    public static class Factory implements ItemFactory<ItemStack, InventoryClickEvent, MinestomMenuItem> {

        private static final Factory INSTANCE = new Factory();

        @Override
        public SimpleMinestomMenuItem apply(ItemStack itemStack, ClickFunction<InventoryClickEvent> clickEvent) {
            return new SimpleMinestomMenuItem(itemStack, clickEvent);
        }
    }
}