package com.guflimc.brick.gui.minestom.api;

import net.minestom.server.entity.Player;
import net.minestom.server.event.inventory.InventoryClickEvent;
import net.minestom.server.event.player.PlayerBlockInteractEvent;
import net.minestom.server.event.player.PlayerEntityInteractEvent;
import net.minestom.server.item.ItemStack;

import java.util.function.Consumer;

public interface IMinestomHotbar {

    void setItem(int index, ItemStack itemStack);

    void setItem(int index, ItemStack itemStack, Consumer<Player> onClick);

    void setItem(int index, ItemStack itemStack,
                 Consumer<PlayerBlockInteractEvent> interact,
                 Consumer<PlayerEntityInteractEvent> interactEntity,
                 Consumer<InventoryClickEvent> click);

    ItemStack[] items();

    void open(Player player);

    // events

    void addInteractListener(Consumer<PlayerBlockInteractEvent> listener);

    void addInteractEntityListener(Consumer<PlayerEntityInteractEvent> listener);

    void addInventoryClickListener(Consumer<InventoryClickEvent> listener);

}
