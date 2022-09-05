package com.guflimc.brick.gui.spigot.menu;

import com.guflimc.brick.gui.spigot.hotbar.SpigotHotbar;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SpigotRegistry {

    private final Map<Player, SpigotISimpleMenu> menus = new HashMap<>();
    private final Map<Player, SpigotHotbar> hotbars = new HashMap<>();

    public void register(Player player, SpigotISimpleMenu menu) {
        menus.put(player, menu);
    }

    public Optional<SpigotISimpleMenu> findMenu(Player player) {
        return Optional.ofNullable(menus.get(player));
    }

    public void register(Player player, SpigotHotbar hotbar) {
        hotbars.put(player, hotbar);
    }

    public void unregister(Player player) {
        menus.remove(player);
        hotbars.remove(player);
    }

    public Optional<SpigotHotbar> findHotbar(Player player) {
        return Optional.ofNullable(hotbars.get(player));
    }
}
