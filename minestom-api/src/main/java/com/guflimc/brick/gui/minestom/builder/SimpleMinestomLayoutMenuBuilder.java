package com.guflimc.brick.gui.minestom.builder;

import com.guflimc.brick.gui.common.builder.StandardLayoutMenuBuilder;
import com.guflimc.brick.gui.minestom.api.MinestomLayoutMenuBuilder;
import com.guflimc.brick.gui.minestom.api.MinestomMenu;
import com.guflimc.brick.gui.minestom.api.MinestomMenuItem;
import com.guflimc.brick.gui.minestom.menu.SimpleMinestomMenu;
import com.guflimc.brick.gui.minestom.menu.SimpleMinestomMenuItem;
import net.minestom.server.event.inventory.InventoryClickEvent;
import net.minestom.server.item.ItemStack;

public class SimpleMinestomLayoutMenuBuilder extends StandardLayoutMenuBuilder
        <ItemStack, InventoryClickEvent, MinestomMenuItem, MinestomMenu, SimpleMinestomLayoutMenuBuilder>
        implements MinestomLayoutMenuBuilder {

    public SimpleMinestomLayoutMenuBuilder() {
        super(MinestomMenuItem.class, SimpleMinestomMenuItem.factory(), SimpleMinestomMenu.factory());
    }
}