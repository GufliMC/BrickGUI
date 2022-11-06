package com.guflimc.brick.gui.api.builder;

import com.guflimc.brick.gui.api.menu.MenuItem;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

public class PaginatedMenuBuilder<T extends MenuItem> {

    private int size;
    private Function<Integer, T> supplier;
    protected final T[] hotbar;

    protected final Class<T> type;

    public PaginatedMenuBuilder(Class<T> type) {
        this.type = type;
        this.hotbar = (T[]) Array.newInstance(type, 9);
    }

    //

    public PaginatedMenuBuilder<T> withItems(int size, Function<Integer, T> supplier) {
        this.size = size;
        this.supplier = supplier;
        return this;
    }

    public final PaginatedMenuBuilder<T> withHotbarItem(int index, T item) {
        hotbar[index] = item;
        return this;
    }

    protected final T[] compile(int page, T back, T next) {
        if ( size <= 0 ) {
            throw new IllegalStateException("Size must be greater than 0");
        }
        if ( supplier == null ) {
            throw new IllegalStateException("Supplier cannot be null.");
        }

        int rows = (int) Math.ceil(this.size / 9.0);
        int pagesize = Math.min(rows, 4) * 9;
        int pages = (int) Math.ceil(rows / 4.0);

        int size = pagesize;
        if ( pages > 1 || Arrays.stream(hotbar).anyMatch(Objects::nonNull) ) {
            size += 18;
        }

        T[] items = (T[]) Array.newInstance(type, size);

        // fill items
        int start = page * pagesize;
        int index = 0;
        for ( int i = start; i < Math.min(start + pagesize, this.size); i++ ) {
            items[index] = supplier.apply(i);
            index++;
        }

        // fill hotbar
        System.arraycopy(hotbar, 0, items, size - 9, 9);

        // pagination buttons
        if ( pages > 1 && page > 0 ) {
            items[size - 7] = back;
        }

        if ( pages > 1 && page < pages - 1 ) {
            items[size - 3] = next;
        }

        return items;
    }

}
