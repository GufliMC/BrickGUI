package com.guflimc.brick.gui.minestom;

import com.guflimc.brick.gui.minestom.api.*;
import com.guflimc.brick.gui.minestom.api.IMinestomMenu;
import com.guflimc.brick.gui.minestom.builder.SimpleMinestomLayoutMenuBuilder;
import com.guflimc.brick.gui.minestom.hotbar.MinestomHotbar;
import com.guflimc.brick.gui.minestom.listener.MinestomHotbarListener;
import com.guflimc.brick.gui.minestom.listener.MinestomMenuListener;
import com.guflimc.brick.gui.minestom.menu.MinestomISimpleMenu;
import com.guflimc.brick.gui.minestom.menu.SimpleMinestomMenu;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class MinestomBrickGUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinestomBrickGUI.class);

    private static final MinestomRegistry registry = new MinestomRegistry();

    private static MinestomMenuListener menuListener;
    private static MinestomHotbarListener hotbarListener;

    public static MinestomRegistry registry() {
        if (menuListener == null || hotbarListener == null) {
            menuListener = new MinestomMenuListener(registry);
            hotbarListener = new MinestomHotbarListener(registry);
        }
        return registry;
    }

    private static void assertListener() {
        if (menuListener == null) {
            throw new IllegalStateException("MasterGUI events are not registered. Call BukkitMasterGUI.register(plugin) first.");
        }
    }

    public static MinestomMenu create(int size) {
        return SimpleMinestomMenu.of(size);
    }

    public static MinestomMenu create(int size, Component title) {
        return SimpleMinestomMenu.of(size, title);
    }

    public static SimpleMinestomLayoutMenuBuilder builder() {
        return new SimpleMinestomLayoutMenuBuilder();
    }

    public static SimpleMinestomLayoutMenuBuilder paginatedBuilder() {
        return // TODO
    }

    public static Optional<MinestomMenu> openedMenu(Player player) {
        return registry.findMenu(player).map(menu -> menu);
    }

    public static IMinestomHotbar createHotbar() {
        return new MinestomHotbar(registry);
    }

    public static Optional<IMinestomHotbar> openedHotbar(Player player) {
        return registry.findHotbar(player).map(hotbar -> hotbar);
    }


}
