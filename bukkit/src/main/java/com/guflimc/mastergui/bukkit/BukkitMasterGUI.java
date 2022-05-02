package com.guflimc.mastergui.bukkit;

import com.guflimc.mastergui.bukkit.api.IBukkitMenu;
import com.guflimc.mastergui.bukkit.api.IBukkitMenuBuilder;
import com.guflimc.mastergui.bukkit.api.IBukkitPaginatedMenuBuilder;
import com.guflimc.mastergui.bukkit.builder.BukkitMenuBuilder;
import com.guflimc.mastergui.bukkit.builder.BukkitPaginatedMenuBuilder;
import com.guflimc.mastergui.bukkit.listener.BukkitMenuListener;
import com.guflimc.mastergui.bukkit.menu.BukkitMenu;
import com.guflimc.mastergui.bukkit.menu.BukkitRegistry;
import org.bukkit.plugin.java.JavaPlugin;

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

    public static IBukkitMenu create(int size) {
        return new BukkitMenu(registry, size);
    }

    public static IBukkitMenu create(int size, String title) {
        return new BukkitMenu(registry, size, title);
    }

    public static IBukkitMenuBuilder builder() {
        return new BukkitMenuBuilder(registry);
    }

    public static IBukkitPaginatedMenuBuilder paginatedBuilder() {
        return new BukkitPaginatedMenuBuilder(registry);
    }
}
