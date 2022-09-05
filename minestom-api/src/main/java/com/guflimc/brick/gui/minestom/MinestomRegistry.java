package com.guflimc.brick.gui.minestom;

import com.guflimc.brick.gui.minestom.hotbar.MinestomHotbar;
import com.guflimc.brick.gui.minestom.menu.SimpleMinestomMenu;
import net.minestom.server.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MinestomRegistry {

    private final Map<Player, SimpleMinestomMenu> menus = new HashMap<>();
    private final Map<Player, MinestomHotbar> hotbars = new HashMap<>();

    public void register(Player player, SimpleMinestomMenu menu) {
        menus.put(player, menu);
    }

    public Optional<SimpleMinestomMenu> findMenu(Player player) {
        return Optional.ofNullable(menus.get(player));
    }

    public void register(Player player, MinestomHotbar hotbar) {
        hotbars.put(player, hotbar);
    }

    public void unregister(Player player) {
        menus.remove(player);
        hotbars.remove(player);
    }

    public Optional<MinestomHotbar> findHotbar(Player player) {
        return Optional.ofNullable(hotbars.get(player));
    }
}
