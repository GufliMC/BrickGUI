package com.guflimc.mastergui.bukkit.menu;

import org.bukkit.entity.HumanEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BukkitRegistry {

    private final Map<HumanEntity, BukkitMenu> menus = new HashMap<>();

    public void register(HumanEntity entity, BukkitMenu menu) {
        menus.put(entity, menu);
    }

    public void unregister(HumanEntity entity) {
        menus.remove(entity);
    }

    public Optional<BukkitMenu> get(HumanEntity entity) {
        return Optional.ofNullable(menus.get(entity));
    }
}
