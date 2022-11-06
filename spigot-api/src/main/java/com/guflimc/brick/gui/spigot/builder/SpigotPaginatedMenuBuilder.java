package com.guflimc.brick.gui.spigot.builder;

import com.guflimc.brick.gui.api.builder.PaginatedMenuBuilder;
import com.guflimc.brick.gui.spigot.api.ISpigotPaginatedMenuBuilder;
import com.guflimc.brick.gui.spigot.item.ItemStackBuilder;
import com.guflimc.brick.gui.spigot.menu.SpigotMenu;
import com.guflimc.brick.gui.spigot.menu.SpigotMenuItem;
import com.guflimc.brick.gui.spigot.menu.SpigotRegistry;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Function;

public class SpigotPaginatedMenuBuilder extends PaginatedMenuBuilder<SpigotMenuItem> implements ISpigotPaginatedMenuBuilder {

    private final SpigotRegistry registry;

    private Function<Integer, String> title;

    private ItemStack back = ItemStackBuilder.of(Material.ARROW)
            .withName(ChatColor.GREEN + "Back")
            .build();

    private ItemStack next = ItemStackBuilder.of(Material.ARROW)
            .withName(ChatColor.GREEN + "Next")
            .build();

    public SpigotPaginatedMenuBuilder(SpigotRegistry registry) {
        super(SpigotMenuItem.class);
        this.registry = registry;
    }

    @Override
    public SpigotPaginatedMenuBuilder withItems(int size, @NotNull Function<Integer, SpigotMenuItem> supplier) {
        super.withItems(size, supplier);
        return this;
    }

    @Override
    public SpigotPaginatedMenuBuilder withTitle(@NotNull Function<Integer, String> supplier) {
        this.title = supplier;
        return this;
    }

    @Override
    public ISpigotPaginatedMenuBuilder withTitle(@NotNull String title) {
        return withTitle(index -> title);
    }

    @Override
    public ISpigotPaginatedMenuBuilder withTitle(@NotNull Component title) {
        return withTitle(LegacyComponentSerializer.legacySection().serialize(title));
    }

    @Override
    public SpigotPaginatedMenuBuilder withBackItem(@NotNull ItemStack itemStack) {
        this.back = itemStack;
        return this;
    }

    @Override
    public SpigotPaginatedMenuBuilder withBackItemName(@NotNull Component text) {
        this.back = ItemStackBuilder.of(back).withName(text).build();
        return this;
    }

    @Override
    public SpigotPaginatedMenuBuilder withBackItemName(@NotNull String text) {
        this.back = ItemStackBuilder.of(back).withName(text).build();
        return this;
    }

    @Override
    public SpigotPaginatedMenuBuilder withNextItem(@NotNull ItemStack itemStack) {
        this.next = itemStack;
        return this;
    }

    public SpigotPaginatedMenuBuilder withNextItemName(@NotNull Component text) {
        this.next = ItemStackBuilder.of(next).withName(text).build();
        return this;
    }

    @Override
    public SpigotPaginatedMenuBuilder withNextItemName(@NotNull String text) {
        this.next = ItemStackBuilder.of(next).withName(text).build();
        return this;
    }

    @Override
    public SpigotPaginatedMenuBuilder withHotbarItem(int index, @NotNull ItemStack itemStack, @NotNull Consumer<InventoryClickEvent> consumer) {
        super.withHotbarItem(index, new SpigotMenuItem(itemStack, consumer));
        return this;
    }

    @Override
    public SpigotPaginatedMenuBuilder withHotbarItem(int index, @NotNull ItemStack itemStack, @NotNull Function<InventoryClickEvent, Boolean> consumer) {
        this.withHotbarItem(index, itemStack, SpigotMenu.soundWrapper(consumer));
        return this;
    }

    //

    @Override
    public SpigotMenu build() {
        return page(0);
    }

    private SpigotMenu page(int page) {
        SpigotMenuItem back = new SpigotMenuItem(this.back, (event) -> page(page - 1).open(event.getWhoClicked()));
        SpigotMenuItem next = new SpigotMenuItem(this.next, (event) -> page(page + 1).open(event.getWhoClicked()));

        SpigotMenuItem[] items = super.compile(page, back, next);

        String title = null;
        if (this.title != null) {
            title = this.title.apply(page);
        }

        SpigotMenu menu = new SpigotMenu(registry, items.length, title);
        for (int i = 0; i < items.length; i++) {
            menu.setItem(i, items[i]);
        }

        return menu;
    }
}
