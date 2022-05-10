package com.guflimc.brick.gui.spigot;

import com.guflimc.brick.gui.spigot.api.ISpigotMenu;
import com.guflimc.brick.gui.spigot.api.ISpigotMenuBuilder;
import com.guflimc.brick.gui.spigot.api.ISpigotPaginatedMenuBuilder;
import com.guflimc.brick.gui.spigot.builder.SpigotMenuBuilder;
import com.guflimc.brick.gui.spigot.builder.SpigotPaginatedMenuBuilder;
import com.guflimc.brick.gui.spigot.listener.SpigotMenuListener;
import com.guflimc.brick.gui.spigot.menu.SpigotMenu;
import com.guflimc.brick.gui.spigot.menu.SpigotRegistry;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public class SpigotBrickGUI {

    private static final SpigotRegistry registry = new SpigotRegistry();
    private static SpigotMenuListener listener;

    public static void register(JavaPlugin plugin) {
        if ( listener != null ) {
            plugin.getLogger().warning("MasterGUI events are already registered. Maybe by another plugin?");
            return;
        }
        listener = new SpigotMenuListener(registry);
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

    private static void assertListener() {
        if ( listener == null ) {
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

    public static Optional<ISpigotMenu> openedMenu(Player player) {
        assertListener();
        return registry.get(player).map(menu -> menu);
    }
}
