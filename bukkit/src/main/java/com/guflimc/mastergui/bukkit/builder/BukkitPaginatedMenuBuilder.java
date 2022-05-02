package com.guflimc.mastergui.bukkit.builder;

import com.guflimc.mastergui.api.builder.PaginatedMenuBuilder;
import com.guflimc.mastergui.bukkit.api.IBukkitMenu;
import com.guflimc.mastergui.bukkit.api.IBukkitPaginatedMenuBuilder;
import com.guflimc.mastergui.bukkit.menu.BukkitMenu;
import com.guflimc.mastergui.bukkit.menu.BukkitMenuItem;
import com.guflimc.mastergui.bukkit.menu.BukkitRegistry;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public class BukkitPaginatedMenuBuilder extends PaginatedMenuBuilder<BukkitMenuItem> implements IBukkitPaginatedMenuBuilder {

    private final BukkitRegistry registry;

    private Function<Integer, String> title;

    private ItemStack back = ItemStackBuilder.of(Material.ARROW)
            .withName(ChatColor.GREEN + "Back")
            .build();

    private ItemStack next = ItemStackBuilder.of(Material.ARROW)
            .withName(ChatColor.GREEN + "Next")
            .build();

    public BukkitPaginatedMenuBuilder(BukkitRegistry registry) {
        super(BukkitMenuItem.class);
        this.registry = registry;
    }

    @Override
    public IBukkitPaginatedMenuBuilder withTitle(Function<Integer, String> supplier) {
        this.title = supplier;
        return this;
    }

    @Override
    public IBukkitPaginatedMenuBuilder withBackItem(ItemStack itemStack) {
        this.back = itemStack;
        return this;
    }

    @Override
    public IBukkitPaginatedMenuBuilder withNextItem(ItemStack itemStack) {
        this.next = itemStack;
        return this;
    }

    @Override
    public IBukkitMenu build() {
        return page(0);
    }

    private IBukkitMenu page(int page) {
        BukkitMenuItem back = new BukkitMenuItem(this.back, (event) -> page(page - 1).open(event.getWhoClicked()));
        BukkitMenuItem next = new BukkitMenuItem(this.next, (event) -> page(page + 1).open(event.getWhoClicked()));

        BukkitMenuItem[] items = super.compile(page, back, next);

        String title = null;
        if (this.title != null) {
            title = this.title.apply(page);
        }

        BukkitMenu menu = new BukkitMenu(registry, items.length, title);
        for (int i = 0; i < items.length; i++) {
            menu.setItem(i, items[i]);
        }

        return menu;
    }
}
