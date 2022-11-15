package com.guflimc.brick.gui.spigot.builder;

import com.guflimc.brick.gui.spigot.api.ISpigotConfirmationMenuBuilder;
import com.guflimc.brick.gui.spigot.api.ISpigotMenu;
import com.guflimc.brick.gui.spigot.item.ItemStackBuilder;
import com.guflimc.brick.gui.spigot.menu.SpigotMenu;
import com.guflimc.brick.gui.spigot.menu.SpigotRegistry;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SpigotConfirmationMenuBuilder implements ISpigotConfirmationMenuBuilder {

    private final SpigotRegistry registry;

    private ItemStack display;
    private String title;

    private ItemStack deny = ItemStackBuilder.of(Material.RED_TERRACOTTA)
            .withName(Component.text("Deny", NamedTextColor.RED))
            .build();
    private ItemStack accept = ItemStackBuilder.of(Material.GREEN_TERRACOTTA)
            .withName(Component.text("Accept", NamedTextColor.GREEN))
            .build();

    private Runnable denyAction = () -> {
    };
    private Runnable acceptAction = () -> {
    };

    public SpigotConfirmationMenuBuilder(SpigotRegistry registry) {
        this.registry = registry;
    }

    //

    public final SpigotConfirmationMenuBuilder withDisplay(@NotNull ItemStack item) {
        this.display = item;
        return this;
    }

    public final SpigotConfirmationMenuBuilder withAcceptItem(@NotNull ItemStack item) {
        this.accept = item;
        return this;
    }

    public final SpigotConfirmationMenuBuilder withAcceptItemName(@NotNull Component text) {
        this.accept = ItemStackBuilder.of(accept).withName(text).build();
        return this;
    }

    public final SpigotConfirmationMenuBuilder withAcceptItemName(@NotNull String text) {
        this.accept = ItemStackBuilder.of(accept).withName(text).build();
        return this;
    }

    public final SpigotConfirmationMenuBuilder withDenyItem(@NotNull ItemStack item) {
        this.deny = item;
        return this;
    }

    public final SpigotConfirmationMenuBuilder withDenyItemName(@NotNull Component text) {
        this.deny = ItemStackBuilder.of(deny).withName(text).build();
        return this;
    }

    public final SpigotConfirmationMenuBuilder withDenyItemName(@NotNull String text) {
        this.deny = ItemStackBuilder.of(deny).withName(text).build();
        return this;
    }

    public final SpigotConfirmationMenuBuilder withTitle(@NotNull String title) {
        this.title = title;
        return this;
    }

    public final SpigotConfirmationMenuBuilder withTitle(@NotNull Component title) {
        return withTitle(LegacyComponentSerializer.legacySection().serialize(title));
    }

    public final SpigotConfirmationMenuBuilder withAccept(@NotNull Runnable action) {
        this.acceptAction = action;
        return this;
    }

    public final SpigotConfirmationMenuBuilder withDeny(@NotNull Runnable action) {
        this.denyAction = action;
        return this;
    }

    public final ISpigotMenu build() {
        ISpigotMenu menu = new SpigotMenu(registry, 27, title);
        menu.setItem(11, deny, e -> {
            denyAction.run();
        });
        menu.setItem(15, accept, e -> {
            acceptAction.run();
        });
        if (display != null) {
            menu.setItem(13, display);
        }
        return menu;
    }

}
