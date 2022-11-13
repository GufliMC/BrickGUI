package com.guflimc.brick.gui.api.builder;

import com.guflimc.brick.gui.api.menu.Menu;
import com.guflimc.brick.gui.api.menu.MenuItem;
import com.guflimc.brick.gui.api.scheme.RowScheme;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuRowBuilder<T extends MenuItem> {

    private final static RowScheme[] SCHEMES = new RowScheme[]{
            RowScheme.of("000010000"), // 1 item
            RowScheme.of("001000100"), // 2 items
            RowScheme.of("001010100"), // 3 items
            RowScheme.of("010101010"), // 4 items
            RowScheme.of("101010101"), // 5 items
            RowScheme.of("011101110"), // 6 items
            RowScheme.of("011111110"), // 7 items
            RowScheme.of("111101111"), // 8 items
            RowScheme.of("111111111"), // 9 items
    };


    protected final List<T> items = new ArrayList<>();
    protected final Class<T> type;

    public MenuRowBuilder(Class<T> type) {
        this.type = type;
    }

    public final MenuRowBuilder<T> withItem(T item) {
        items.add(item);
        return this;
    }

    public final MenuRowBuilder<T> withItems(T... items) {
        this.items.addAll(Arrays.asList(items));
        return this;
    }

    public final MenuRowBuilder<T> withItems(Iterable<T> items) {
        items.forEach(this.items::add);
        return this;
    }

    public final T[] compile() {
        RowScheme scheme = SCHEMES[items.size() - 1];

        T[] items = (T[]) Array.newInstance(type, 9);

        int index = 0;
        for (int slot : scheme.indices()) {
            items[slot] = this.items.get(index);
            index++;
        }

        return items;
    }

    public final void fill(Menu<T> menu, int row) {
        T[] items = compile();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                menu.setItem((row * 9) + i, items[i]);
            }
        }
    }

}
