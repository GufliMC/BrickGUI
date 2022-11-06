package com.guflimc.brick.gui.spigot.builder;

import com.guflimc.brick.gui.spigot.api.ISpigotMenuBuilder;
import com.guflimc.brick.gui.api.builder.MenuBuilder;
import com.guflimc.brick.gui.spigot.menu.SpigotMenu;
import com.guflimc.brick.gui.spigot.menu.SpigotMenuItem;
import com.guflimc.brick.gui.spigot.menu.SpigotRegistry;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;
import java.util.function.Function;

public class SpigotMenuBuilder extends MenuBuilder<SpigotMenuItem> implements ISpigotMenuBuilder {

    private final SpigotRegistry registry;

    private String title = null;

    public SpigotMenuBuilder(SpigotRegistry registry) {
        super(SpigotMenuItem.class);
        this.registry =registry;
    }


    @Override
    public SpigotMenuBuilder withTitle(@NotNull String title) {
        this.title = title;
        return this;
    }

    @Override
    public final SpigotMenuBuilder withTitle(@NotNull Component title) {
        return withTitle(LegacyComponentSerializer.legacySection().serialize(title));
    }

    @Override
    public SpigotMenuBuilder withItem(@NotNull ItemStack itemStack) {
        super.withItem(new SpigotMenuItem(itemStack));
        return this;
    }

    @Override
    public SpigotMenuBuilder withItem(@NotNull ItemStack itemStack, @NotNull Consumer<InventoryClickEvent> consumer) {
        super.withItem(new SpigotMenuItem(itemStack, consumer));
        return this;
    }

    @Override
    public SpigotMenuBuilder withItem(@NotNull ItemStack itemStack, @NotNull Function<InventoryClickEvent, Boolean> consumer) {
        super.withItem(new SpigotMenuItem(itemStack, SpigotMenu.soundWrapper(consumer)));
        return this;
    }

    @Override
    public SpigotMenuBuilder withHotbarItem(int index, @NotNull ItemStack itemStack, @NotNull Consumer<InventoryClickEvent> consumer) {
        super.withHotbarItem(index, new SpigotMenuItem(itemStack, consumer));
        return this;
    }

    @Override
    public SpigotMenuBuilder withHotbarItem(int index, @NotNull ItemStack itemStack, @NotNull Function<InventoryClickEvent, Boolean> consumer) {
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
    public SpigotMenu build() {
        SpigotMenuItem[] items = super.compile();

        SpigotMenu menu = new SpigotMenu(registry, items.length, title);
        for ( int i = 0 ; i < items.length ; i++ ) {
           menu.setItem(i, items[i]);
        }

        return menu;
    }

}
