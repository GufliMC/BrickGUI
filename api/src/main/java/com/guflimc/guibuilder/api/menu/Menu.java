package com.guflimc.guibuilder.api.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Menu<CLOSET, OPENT, CLICKT, T extends MenuItem<?, CLICKT>> {

    private Object[] items;

    private final List<Consumer<CLOSET>> closeListeners = new ArrayList<>();
    private final List<Consumer<OPENT>> openListeners = new ArrayList<>();
    private final List<Consumer<CLICKT>> clickListeners = new ArrayList<>();

    public Menu(int size) {
        this.items = new Object[size];
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

    public final T item(int index) {
        return (T) items[index];
    }

    public final T[] items() {
        return (T[]) items.clone();
    }

    public final void subscribeClose(Consumer<CLOSET> callable) {
        closeListeners.add(callable);
    }

    public final void subscribeOpen(Consumer<OPENT> callable) {
        openListeners.add(callable);
    }

    public final void subscribeClick(Consumer<CLICKT> callable) {
        clickListeners.add(callable);
    }

    //

    protected final void dispatchClose(CLOSET event) {
        closeListeners.forEach(listener -> listener.accept(event));
    }

    protected final void dispatchOpen(OPENT event) {
        openListeners.forEach(listener -> listener.accept(event));
    }

    protected final void dispatchClick(CLICKT event) {
        clickListeners.forEach(listener -> listener.accept(event));
    }

}
