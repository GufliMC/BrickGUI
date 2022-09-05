package com.guflimc.brick.gui.common.builder;

import com.guflimc.brick.gui.api.menu.MenuItem;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

public class SimplePaginatedMenuBuilder<MIT extends MenuItem> {

    private int size;
    private Function<Integer, MIT> supplier;
    protected final MIT[] hotbar;

    protected final Class<MIT> type;

    public SimplePaginatedMenuBuilder(Class<MIT> type) {
        this.type = type;
        this.hotbar = (MIT[]) Array.newInstance(type, 9);
    }

    //

    public SimplePaginatedMenuBuilder<MIT> withItems(int size, Function<Integer, MIT> supplier) {
        this.size = size;
        this.supplier = supplier;
        return this;
    }

    public final SimplePaginatedMenuBuilder<MIT> withHotbarItem(int index, MIT item) {
        hotbar[index] = item;
        return this;
    }

    protected final MIT[] compile(int page, MIT back, MIT next) {
        if ( size <= 0 ) {
            throw new IllegalStateException("Size must be greater than 0");
        }
        if ( supplier == null ) {
            throw new IllegalStateException("Supplier cannot be null.");
        }

        int rows = (int) (Math.ceil(this.size / 9.0) * 9);
        int pagesize = Math.min(rows, 4) * 9;
        int pages = (int) Math.ceil(rows / 4.0);

        int size = pagesize;
        if ( pages > 1 || Arrays.stream(hotbar).anyMatch(Objects::nonNull) ) {
            size += 14;
        }

        MIT[] items = (MIT[]) Array.newInstance(type, size);

        // fill items
        int start = page * pagesize;
        int index = 0;
        for ( int i = start; i < Math.min(start + pagesize, this.size); i++ ) {
            items[index] = supplier.apply(i);
            index++;
        }

        // fill hotbar
        System.arraycopy(hotbar, 0, items, (this.size - 1) * 9, 9);

        // pagination buttons
        if ( pages > 1 && page > 0 ) {
            items[47] = back;
        }

        if ( pages > 1 && page < pages - 1 ) {
            items[51] = next;
        }

        return items;
    }

}
