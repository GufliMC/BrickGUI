package com.guflimc.mastergui.api.menu;

import java.lang.reflect.Array;

public class Menu<T extends MenuItem> {

    protected final T[] items;

    public Menu(Class<T> clazz, int size) {
        this.items = (T[]) Array.newInstance(clazz, size);
    }

    public final int size() {
        return items.length;
    }

    public void removeItem(int index) {
        items[index] = null;
    }

    public void setItem(int index, T item) {
        items[index] = item;
    }

}
