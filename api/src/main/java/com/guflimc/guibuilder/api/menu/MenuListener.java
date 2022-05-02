package com.guflimc.guibuilder.api.menu;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class MenuListener<T> {

    private final MenuEventType type;
    private final Consumer<T> callable;

    public MenuListener(MenuEventType type, Consumer<T> callable) {
        this.type = type;
        this.callable = callable;
    }

    public MenuEventType type() {
        return type;
    }

    public Consumer<T> callable() {
        return callable;
    }

}
