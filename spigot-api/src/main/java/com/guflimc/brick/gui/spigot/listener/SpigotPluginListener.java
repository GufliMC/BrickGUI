package com.guflimc.brick.gui.spigot.listener;

import com.guflimc.brick.gui.spigot.menu.SpigotRegistry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotPluginListener implements Listener {

    private final JavaPlugin plugin;
    private final Runnable callback;

    public SpigotPluginListener(JavaPlugin plugin, Runnable callback) {
        this.plugin = plugin;
        this.callback = callback;
    }

    @EventHandler
    public void onDisable(PluginDisableEvent event) {
        if ( !event.getPlugin().equals(plugin) ) {
            return;
        }

        callback.run();
    }

}
