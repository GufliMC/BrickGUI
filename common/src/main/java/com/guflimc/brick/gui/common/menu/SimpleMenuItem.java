package com.guflimc.brick.gui.common.menu;

import com.guflimc.brick.gui.api.click.ClickFunction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleMenuItem<H, E> implements com.guflimc.brick.gui.api.menu.MenuItem {

    private final H handle;
    private final ClickFunction<E> clickEvent;

    public SimpleMenuItem(@NotNull H handle, @Nullable ClickFunction<E> clickEvent) {
        this.handle = handle;
        this.clickEvent = clickEvent;
    }

    @Override
    public H handle() {
        return handle;
    }

    @Override
    public ClickFunction<E> clickEvent() {
        return clickEvent;
    }
}
