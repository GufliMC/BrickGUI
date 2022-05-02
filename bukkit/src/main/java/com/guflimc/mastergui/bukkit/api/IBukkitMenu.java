package com.guflimc.mastergui.bukkit.api;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public interface IBukkitMenu {

    void removeItem(int index);

    void setItem(int index, ItemStack itemStack);

    void setItem(int index, ItemStack itemStack, Consumer<InventoryClickEvent> onClick);

    ItemStack[] items();

    int size();

    void open(HumanEntity entity);

    // events

    void addOpenListener(Consumer<HumanEntity> listener);

    void addCloseListener(Consumer<HumanEntity> listener);

    void addClickListener(Consumer<InventoryClickEvent> listener);

}
