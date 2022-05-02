package com.guflimc.guibuilder.bukkit;

import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class BukkitGUIBuilder {

    static Map<HumanEntity, BukkitMenu> menus = new HashMap<>();

    public static void registerEvents(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new BukkitMenuListener(), plugin);
    }

    public static BukkitMenu create(int size) {
        return new BukkitMenu(size);
    }

    public static BukkitMenu create(int size, String title) {
        return new BukkitMenu(size, title);
    }



}
