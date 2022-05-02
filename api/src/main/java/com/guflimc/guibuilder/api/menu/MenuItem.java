package com.guflimc.guibuilder.api.menu;

import java.util.function.Consumer;

public class MenuItem<T, CLICKT>  {

    private final T handle;
    private final Consumer<CLICKT> callback;

    public MenuItem(T handle, Consumer<CLICKT> callback) {
        this.handle = handle;
        this.callback = callback;
    }

    public MenuItem(T handle) {
        this(handle, null);
    }

    public T handle() {
        return handle;
    }

    public Consumer<CLICKT> callback() {
        return callback;
    }
}
