package com.guflimc.mastergui.bukkit.builder;

import com.guflimc.mastergui.api.builder.MenuBuilder;
import com.guflimc.mastergui.bukkit.api.IBukkitMenu;
import com.guflimc.mastergui.bukkit.api.IBukkitMenuBuilder;
import com.guflimc.mastergui.bukkit.menu.BukkitMenu;
import com.guflimc.mastergui.bukkit.menu.BukkitMenuItem;
import com.guflimc.mastergui.bukkit.menu.BukkitRegistry;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class BukkitMenuBuilder extends MenuBuilder<BukkitMenuItem> implements IBukkitMenuBuilder {

    private final BukkitRegistry registry;

    private String title = null;

    public BukkitMenuBuilder(BukkitRegistry registry) {
        super(BukkitMenuItem.class);
        this.registry =registry;
    }


    @Override
    public IBukkitMenuBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public IBukkitMenuBuilder withItem(ItemStack itemStack) {
        super.withItem(new BukkitMenuItem(itemStack));
        return this;
    }

    @Override
    public IBukkitMenuBuilder withItem(ItemStack itemStack, Consumer<InventoryClickEvent> consumer) {
        super.withItem(new BukkitMenuItem(itemStack, consumer));
        return this;
    }

    @Override
    public IBukkitMenu build() {
        BukkitMenuItem[] items = super.compile();

        BukkitMenu menu = new BukkitMenu(registry, items.length, title);
        for ( int i = 0 ; i < items.length ; i++ ) {
           menu.setItem(i, items[i]);
        }

        return menu;
    }

}