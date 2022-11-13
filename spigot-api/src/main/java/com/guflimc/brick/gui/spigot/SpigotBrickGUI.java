package com.guflimc.brick.gui.spigot;

import com.guflimc.brick.gui.spigot.api.*;
import com.guflimc.brick.gui.spigot.builder.SpigotConfirmationMenuBuilder;
import com.guflimc.brick.gui.spigot.builder.SpigotMenuBuilder;
import com.guflimc.brick.gui.spigot.builder.SpigotMenuRowBuilder;
import com.guflimc.brick.gui.spigot.builder.SpigotPaginatedMenuBuilder;
import com.guflimc.brick.gui.spigot.hotbar.SpigotHotbar;
import com.guflimc.brick.gui.spigot.listener.SpigotHotbarListener;
import com.guflimc.brick.gui.spigot.listener.SpigotMenuListener;
import com.guflimc.brick.gui.spigot.listener.SpigotPluginListener;
import com.guflimc.brick.gui.spigot.menu.SpigotMenu;
import com.guflimc.brick.gui.spigot.menu.SpigotRegistry;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public class SpigotBrickGUI {

    private static final SpigotRegistry registry = new SpigotRegistry();

    private static SpigotMenuListener menuListener;
    private static SpigotHotbarListener hotbarListener;

    public static void register(JavaPlugin plugin) {
        if ( menuListener != null || hotbarListener != null) {
            plugin.getLogger().warning("BrickGUI events are already registered. Maybe by another plugin?");
            return;
        }

        menuListener = new SpigotMenuListener(registry);
        hotbarListener = new SpigotHotbarListener(registry);

        plugin.getServer().getPluginManager().registerEvents(menuListener, plugin);
        plugin.getServer().getPluginManager().registerEvents(hotbarListener, plugin);
        plugin.getServer().getPluginManager().registerEvents(new SpigotPluginListener(plugin, () -> {
            menuListener = null;
            hotbarListener = null;
            registry.closeAll();
        }), plugin);
    }

    private static void assertListener() {
        if ( menuListener == null ) {
            throw new IllegalStateException("MasterGUI events are not registered. Call BukkitMasterGUI.register(plugin) first.");
        }
    }

    public static ISpigotMenu create(int size) {
        assertListener();
        return new SpigotMenu(registry, size);
    }

    public static ISpigotMenu create(int size, String title) {
        assertListener();
        return new SpigotMenu(registry, size, title);
    }

    public static ISpigotMenuBuilder builder() {
        assertListener();
        return new SpigotMenuBuilder(registry);
    }

    public static ISpigotPaginatedMenuBuilder paginatedBuilder() {
        assertListener();
        return new SpigotPaginatedMenuBuilder(registry);
    }

    public static ISpigotConfirmationMenuBuilder confirmationBuilder() {
        assertListener();
        return new SpigotConfirmationMenuBuilder(registry);
    }

    public static ISpigotMenuRowBuilder rowBuilder() {
        assertListener();;
        return new SpigotMenuRowBuilder();
    }

    public static Optional<ISpigotMenu> openedMenu(Player player) {
        assertListener();
        return registry.findMenu(player).map(menu -> menu);
    }

    public static ISpigotHotbar createHotbar() {
        return new SpigotHotbar(registry);
    }

    public static Optional<ISpigotHotbar> openedHotbar(Player player) {
        return registry.findHotbar(player).map(hotbar -> hotbar);
    }


}
