package com.guflimc.brick.gui.spigot.hotbar;

import com.guflimc.brick.gui.api.menu.MenuItem;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class SpigotHotbarItemISimple implements MenuItem {

    private final ItemStack handle;
    private final Consumer<PlayerInteractEvent> interact;
    private final Consumer<PlayerInteractEntityEvent> entityInteract;
    private final Consumer<InventoryClickEvent> click;

    public SpigotHotbarItemISimple(ItemStack handle,
                                   Consumer<PlayerInteractEvent> interact,
                                   Consumer<PlayerInteractEntityEvent> entityInteract,
                                   Consumer<InventoryClickEvent> click) {
        this.handle = handle;
        this.interact = interact;
        this.entityInteract = entityInteract;
        this.click = click;
    }

    public SpigotHotbarItemISimple(ItemStack handle, Consumer<Player> consumer) {
        this(
                handle,
                (e) -> {
                    if ( e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK ){
                        consumer.accept(e.getPlayer());
                    }
                },
                (e) -> consumer.accept(e.getPlayer()),
                (e) -> {
                    if ( e.getWhoClicked() instanceof Player player ) {
                        consumer.accept(player);
                    }
                }
        );
    }

    public SpigotHotbarItemISimple(ItemStack handle) {
        this(handle, null, null, null);
    }

    @Override
    public ItemStack handle() {
        return handle;
    }

    @Override
    public Consumer<PlayerInteractEvent> callback() {
        return interact;
    }

    public Consumer<PlayerInteractEntityEvent> entityInteract() {
        return entityInteract;
    }

    public Consumer<InventoryClickEvent> click() {
        return click;
    }
}
