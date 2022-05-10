package com.guflimc.brick.gui.spigot.menu;

import org.bukkit.entity.HumanEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SpigotRegistry {

    private final Map<HumanEntity, SpigotMenu> menus = new HashMap<>();

    public void register(HumanEntity entity, SpigotMenu menu) {
        menus.put(entity, menu);
    }

    public void unregister(HumanEntity entity) {
        menus.remove(entity);
    }

    public Optional<SpigotMenu> get(HumanEntity entity) {
        return Optional.ofNullable(menus.get(entity));
    }
}
