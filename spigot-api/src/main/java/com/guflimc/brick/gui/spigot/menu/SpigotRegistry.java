package com.guflimc.brick.gui.spigot.menu;

import com.guflimc.brick.gui.spigot.hotbar.SpigotHotbar;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SpigotRegistry {

    private final Map<HumanEntity, SpigotMenu> menus = new HashMap<>();
    private final Map<Player, SpigotHotbar> hotbars = new HashMap<>();

    public void register(HumanEntity entity, SpigotMenu menu) {
        menus.put(entity, menu);
    }

    public void unregister(HumanEntity entity) {
        menus.remove(entity);
    }

    public Optional<SpigotMenu> findMenu(HumanEntity entity) {
        return Optional.ofNullable(menus.get(entity));
    }

    public void register(Player player, SpigotHotbar hotbar) {
        hotbars.put(player, hotbar);
    }

    public void unregister(Player player) {
        hotbars.remove(player);
    }

    public Optional<SpigotHotbar> findHotbar(Player player) {
        return Optional.ofNullable(hotbars.get(player));
    }

    public void closeAll() {
        menus.keySet().forEach(HumanEntity::closeInventory);
        // TODO hotbars
    }
}
