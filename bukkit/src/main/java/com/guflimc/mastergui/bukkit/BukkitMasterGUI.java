package com.guflimc.mastergui.bukkit;

import com.guflimc.mastergui.bukkit.api.IBukkitMenu;
import com.guflimc.mastergui.bukkit.api.IBukkitMenuBuilder;
import com.guflimc.mastergui.bukkit.api.IBukkitPaginatedMenuBuilder;
import com.guflimc.mastergui.bukkit.builder.BukkitMenuBuilder;
import com.guflimc.mastergui.bukkit.builder.BukkitPaginatedMenuBuilder;
import com.guflimc.mastergui.bukkit.listener.BukkitMenuListener;
import com.guflimc.mastergui.bukkit.menu.BukkitMenu;
import com.guflimc.mastergui.bukkit.menu.BukkitRegistry;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Optional;

public class BukkitMasterGUI {

    private static final BukkitRegistry registry = new BukkitRegistry();
    private static BukkitMenuListener listener;

    public static void register(JavaPlugin plugin) {
        if ( listener != null ) {
            plugin.getLogger().warning("MasterGUI events are already registered. Maybe by another plugin?");
            return;
        }
        listener = new BukkitMenuListener(registry);
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }

    private static void assertListener() {
        if ( listener == null ) {
            throw new IllegalStateException("MasterGUI events are not registered. Call BukkitMasterGUI.register(plugin) first.");
        }
    }

    public static IBukkitMenu create(int size) {
        assertListener();
        return new BukkitMenu(registry, size);
    }

    public static IBukkitMenu create(int size, String title) {
        assertListener();
        return new BukkitMenu(registry, size, title);
    }

    public static IBukkitMenuBuilder builder() {
        assertListener();
        return new BukkitMenuBuilder(registry);
    }

    public static IBukkitPaginatedMenuBuilder paginatedBuilder() {
        assertListener();
        return new BukkitPaginatedMenuBuilder(registry);
    }

    public static Optional<IBukkitMenu> openedMenu(Player player) {
        assertListener();
        return registry.get(player).map(menu -> menu);
    }
}
