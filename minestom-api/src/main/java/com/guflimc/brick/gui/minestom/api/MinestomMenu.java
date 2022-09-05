package com.guflimc.brick.gui.minestom.api;

import com.guflimc.brick.gui.api.click.ClickFunction;
import com.guflimc.brick.gui.api.menu.Menu;
import com.guflimc.brick.gui.api.menu.MenuItem;
import net.minestom.server.entity.Player;
import net.minestom.server.event.inventory.InventoryClickEvent;
import net.minestom.server.item.ItemStack;

import java.util.function.Consumer;
import java.util.function.Function;

public interface MinestomMenu extends Menu<MinestomMenuItem, InventoryClickEvent, Player, Player> {

    ItemStack[] items();

    void setItem(int index, ItemStack itemStack);

    void setItem(int index, ItemStack itemStack, Consumer<InventoryClickEvent> onClick);

    void setItem(int index, ItemStack itemStack, ClickFunction<InventoryClickEvent> onClick);

    void open(Player entity);

}
