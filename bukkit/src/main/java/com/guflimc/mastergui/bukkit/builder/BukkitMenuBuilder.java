package com.guflimc.mastergui.bukkit.builder;

import com.guflimc.mastergui.api.builder.MenuBuilder;
import com.guflimc.mastergui.bukkit.api.IBukkitMenuBuilder;
import com.guflimc.mastergui.bukkit.menu.BukkitMenu;
import com.guflimc.mastergui.bukkit.menu.BukkitMenuItem;
import com.guflimc.mastergui.bukkit.menu.BukkitRegistry;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;
import java.util.function.Function;

public class BukkitMenuBuilder extends MenuBuilder<BukkitMenuItem> implements IBukkitMenuBuilder {

    private final BukkitRegistry registry;

    private String title = null;

    public BukkitMenuBuilder(BukkitRegistry registry) {
        super(BukkitMenuItem.class);
        this.registry =registry;
    }


    @Override
    public BukkitMenuBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public BukkitMenuBuilder withItem(ItemStack itemStack) {
        super.withItem(new BukkitMenuItem(itemStack));
        return this;
    }

    @Override
    public BukkitMenuBuilder withItem(ItemStack itemStack, Consumer<InventoryClickEvent> consumer) {
        super.withItem(new BukkitMenuItem(itemStack, consumer));
        return this;
    }

    @Override
    public BukkitMenuBuilder withItem(ItemStack itemStack, Function<InventoryClickEvent, Boolean> consumer) {
        super.withItem(new BukkitMenuItem(itemStack, (event) -> {
            Sound sound = consumer.apply(event) ? Sound.UI_BUTTON_CLICK : Sound.ENTITY_VILLAGER_NO;
            if ( event.getWhoClicked() instanceof Player p) {
                p.playSound(p.getEyeLocation(), sound, 1f, 1f);
            }
        }));
        return this;
    }

    @Override
    public BukkitMenuBuilder withHotbarItem(int index, ItemStack itemStack, Consumer<InventoryClickEvent> consumer) {
        super.withHotbarItem(index, new BukkitMenuItem(itemStack, consumer));
        return this;
    }

    @Override
    public BukkitMenuBuilder withHotbarItem(int index, ItemStack itemStack, Function<InventoryClickEvent, Boolean> consumer) {
        this.withHotbarItem(index, itemStack, (event) -> {
            Sound sound = consumer.apply(event) ? Sound.UI_BUTTON_CLICK : Sound.ENTITY_VILLAGER_NO;
            if ( event.getWhoClicked() instanceof Player p) {
                p.playSound(p.getEyeLocation(), sound, 1f, 1f);
            }
        });
        return this;
    }

    //

    @Override
    public BukkitMenu build() {
        BukkitMenuItem[] items = super.compile();

        BukkitMenu menu = new BukkitMenu(registry, items.length, title);
        for ( int i = 0 ; i < items.length ; i++ ) {
           menu.setItem(i, items[i]);
        }

        return menu;
    }

}
