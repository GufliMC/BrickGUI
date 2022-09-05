package com.guflimc.brick.gui.common.builder;

import com.guflimc.brick.gui.api.menu.MenuItem;
import com.guflimc.brick.gui.api.scheme.MenuScheme;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SimpleLayoutMenuBuilder<MIT extends MenuItem> {

    private final static MenuScheme[] SCHEMES = new MenuScheme[] {
            MenuScheme.of("000000000", "000010000", "000000000"), // 1 item
            MenuScheme.of("000000000", "000101000", "000000000"), // 2 items
            MenuScheme.of("000000000", "001010100", "000000000"), // 3 items
            MenuScheme.of("000000000", "010101010", "000000000"), // 4 items
            MenuScheme.of("000000000", "001000100", "000010000", "001000100", "000000000"), // 5 items
            MenuScheme.of("000000000", "001010100", "001010100", "000000000"), // 6 items
            MenuScheme.of("000000000", "010101010", "001010100", "000000000"), // 7 items
            MenuScheme.of("000000000", "010101010", "010101010", "000000000"), // 8 items
            MenuScheme.of("000000000", "010101010", "001010100", "010101010", "000000000"), // 9 items
            MenuScheme.of("000000000", "010101010", "000101000", "010101010", "000000000") // 10 items
    };

    //

    protected final List<MIT> items = new ArrayList<>();
    protected final MIT[] hotbar;
    protected final Class<MIT> type;

    public SimpleLayoutMenuBuilder(Class<MIT> type) {
        this.type = type;
        this.hotbar = (MIT[]) Array.newInstance(type, 9);
    }
    //

    public final SimpleLayoutMenuBuilder<MIT> withItem(MIT item) {
        items.add(item);
        return this;
    }

    public final SimpleLayoutMenuBuilder<MIT> withItems(MIT... items) {
        this.items.addAll(Arrays.asList(items));
        return this;
    }

    public final SimpleLayoutMenuBuilder<MIT> withItems(Iterable<MIT> items) {
        items.forEach(this.items::add);
        return this;
    }

    public final SimpleLayoutMenuBuilder<MIT> withActionbarItem(int index, MIT item) {
        hotbar[index] = item;
        return this;
    }

    protected final MIT[] compile() {
        if ( items.size() > 10 ) {
            throw new UnsupportedOperationException("More than 10 items is not supported.");
        }

        MenuScheme scheme = SCHEMES[items.size() - 1];

        int size = scheme.size();
        if ( Arrays.stream(hotbar).anyMatch(Objects::nonNull) ) {
            size += 1;
        }

        MIT[] items = (MIT[]) Array.newInstance(type, size * 9);

        // fill items
        int index = 0;
        for ( int slot : scheme.indices() ) {
            items[slot] = this.items.get(index);
            index++;
        }

        // fill hotbar
        System.arraycopy(hotbar, 0, items, (size - 1) * 9, 9);

        return items;
    }
    
}
