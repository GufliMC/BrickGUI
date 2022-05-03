package com.guflimc.mastergui.bukkit.builder;

import com.guflimc.mastergui.api.builder.PaginatedMenuBuilder;
import com.guflimc.mastergui.bukkit.api.IBukkitPaginatedMenuBuilder;
import com.guflimc.mastergui.bukkit.menu.BukkitMenu;
import com.guflimc.mastergui.bukkit.menu.BukkitMenuItem;
import com.guflimc.mastergui.bukkit.menu.BukkitRegistry;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;
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
    public BukkitPaginatedMenuBuilder withItems(int size, Function<Integer, BukkitMenuItem> supplier) {
        super.withItems(size, supplier);
        return this;
    }

    @Override
    public BukkitPaginatedMenuBuilder withTitle(Function<Integer, String> supplier) {
        this.title = supplier;
        return this;
    }

    @Override
    public BukkitPaginatedMenuBuilder withBackItem(ItemStack itemStack) {
        this.back = itemStack;
        return this;
    }

    @Override
    public BukkitPaginatedMenuBuilder withNextItem(ItemStack itemStack) {
        this.next = itemStack;
        return this;
    }

    @Override
    public BukkitPaginatedMenuBuilder withHotbarItem(int index, ItemStack itemStack, Consumer<InventoryClickEvent> consumer) {
        super.withHotbarItem(index, new BukkitMenuItem(itemStack, consumer));
        return this;
    }

    @Override
    public BukkitPaginatedMenuBuilder withHotbarItem(int index, ItemStack itemStack, Function<InventoryClickEvent, Boolean> consumer) {
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
        return page(0);
    }

    private BukkitMenu page(int page) {
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
