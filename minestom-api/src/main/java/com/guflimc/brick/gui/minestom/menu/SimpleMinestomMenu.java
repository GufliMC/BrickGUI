package com.guflimc.brick.gui.minestom.menu;

import com.guflimc.brick.gui.api.click.ClickFunction;
import com.guflimc.brick.gui.api.click.ClickResult;
import com.guflimc.brick.gui.common.factory.MenuFactory;
import com.guflimc.brick.gui.common.menu.SimpleMenu;
import com.guflimc.brick.gui.minestom.MinestomBrickGUI;
import com.guflimc.brick.gui.minestom.api.MinestomMenu;
import com.guflimc.brick.gui.minestom.api.MinestomMenuItem;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.minestom.server.coordinate.Point;
import net.minestom.server.entity.Player;
import net.minestom.server.event.inventory.InventoryClickEvent;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;

import java.util.Arrays;
import java.util.function.Consumer;

public class SimpleMinestomMenu extends SimpleMenu
        <MinestomMenuItem, InventoryClickEvent, Player, Player>
        implements MinestomMenu {

    public final Inventory inventory;

    private SimpleMinestomMenu(int size) {
        super(MinestomMenuItem.class, size);
        inventory = new Inventory(InventoryType.values()[size / 9 - 1], "");
    }

    private SimpleMinestomMenu(MinestomMenuItem[] items) {
        this(items.length);
        for (int i = 0; i < items.length; i++) {
            setItem(i, items[i]);
        }
    }

    //


    @Override
    public void setTitle(Component title) {
        super.setTitle(title);
        inventory.setTitle(title);
    }

    @Override
    public void setItem(int index, MinestomMenuItem item) {
        super.setItem(index, item);
        inventory.setItemStack(index, ((SimpleMinestomMenuItem) item).handle());
    }

    @Override
    public void remove(int index) {
        super.remove(index);
        inventory.setItemStack(index, ItemStack.AIR);
    }

    //

    @Override
    public ItemStack[] items() {
        return Arrays.stream(super.items).map(mi -> (ItemStack) mi.handle()).toArray(ItemStack[]::new);
    }

    @Override
    public void setItem(int index, ItemStack itemStack) {
        super.setItem(index, SimpleMinestomMenuItem.of(itemStack));
    }

    @Override
    public void setItem(int index, ItemStack itemStack, Consumer<InventoryClickEvent> onClick) {
        super.setItem(index, SimpleMinestomMenuItem.of(itemStack, ClickFunction.from(onClick)));
    }

    @Override
    public void setItem(int index, ItemStack itemStack, ClickFunction<InventoryClickEvent> onClick) {
        super.setItem(index, SimpleMinestomMenuItem.of(itemStack, onClick));
    }

    @Override
    public void open(Player player) {
        player.openInventory(inventory);
        MinestomBrickGUI.registry().register(player, this);

        dispatchOpen(player);
    }

    //

    public void dispatchClick(InventoryClickEvent event) {
        clickCallbacks.forEach(listener -> listener.accept(event));

        //event.setCancelled(true); // TODO

        SimpleMinestomMenuItem item = (SimpleMinestomMenuItem) super.items[event.getSlot()];
        if (item == null || item.clickEvent() == null) {
            return;
        }

        ClickResult result = item.clickEvent().apply(event);

        // play sound based on click result
        Sound sound;
        if (result == ClickResult.SUCCESS) {
            sound = Sound.sound(Key.key("ui.button.click"), Sound.Source.NEUTRAL, 1f, 1f);
        } else if (result == ClickResult.FAILURE) {
            sound = Sound.sound(Key.key("entity.villager.no"), Sound.Source.NEUTRAL, 1f, 1f);
        } else {
            return;
        }

        Player p = event.getPlayer();
        Point pos = p.getPosition().add(0, p.getEyeHeight(), 0);
        event.getPlayer().playSound(sound, pos.x(), pos.y(), pos.z());
    }

    public void dispatchClose(Player player) {
        closeCallbacks.forEach(listener -> listener.accept(player));
    }

    public void dispatchOpen(Player player) {
        openCallbacks.forEach(listener -> listener.accept(player));
    }

    //

    public static Factory factory() {
        return Factory.INSTANCE;
    }

    public static SimpleMinestomMenu of(int size) {
        return new SimpleMinestomMenu(size);
    }

    public static SimpleMinestomMenu of(int size, Component title) {
        SimpleMinestomMenu menu = new SimpleMinestomMenu(size);
        menu.setTitle(title);
        return menu;
    }

    public static class Factory implements MenuFactory
            <MinestomMenuItem, MinestomMenu> {

        private static final Factory INSTANCE = new Factory();

        @Override
        public MinestomMenu apply(Component title, MinestomMenuItem[] items) {
            SimpleMinestomMenu menu = new SimpleMinestomMenu(items);
            menu.setTitle(title);
            return menu;
        }
    }
}