package com.guflimc.brick.gui.spigot.api;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public interface ISpigotHotbar {

    void setItem(int index, ItemStack itemStack);

    void setItem(int index, ItemStack itemStack, Consumer<Player> onClick);

    void setItem(int index, ItemStack itemStack,
                 Consumer<PlayerInteractEvent> interact,
                 Consumer<PlayerInteractEntityEvent> interactEntity,
                 Consumer<InventoryClickEvent> click);

    ItemStack[] items();

    void open(Player player);

    // events

    void addInteractListener(Consumer<PlayerInteractEvent> listener);

    void addInteractEntityListener(Consumer<PlayerInteractEntityEvent> listener);

    void addInventoryClickListener(Consumer<InventoryClickEvent> listener);

}
