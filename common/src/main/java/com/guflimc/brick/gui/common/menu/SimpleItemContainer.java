package com.guflimc.brick.gui.common.menu;

import com.guflimc.brick.gui.api.menu.MenuItem;
import com.guflimc.brick.gui.api.menu.ItemContainer;

import java.lang.reflect.Array;

public class SimpleItemContainer<MIT extends MenuItem> implements ItemContainer<MIT> {

    protected final MIT[] items;

    public SimpleItemContainer(Class<MIT> clazz, int size) {
        this.items = (MIT[]) Array.newInstance(clazz, size);
    }

    public SimpleItemContainer(MIT[] items) {
        this.items = items.clone();
    }

    public final int size() {
        return items.length;
    }

    @Override
    public void setItem(int index, MIT item) {
        items[index] = item;
    }

    @Override
    public void remove(int index) {
        items[index] = null;
    }

}
