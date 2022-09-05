package com.guflimc.brick.gui.common.menu;

import com.guflimc.brick.gui.api.menu.Menu;
import com.guflimc.brick.gui.api.menu.MenuItem;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SimpleMenu<MIT extends MenuItem, EVT, OEVT, CEVT> extends SimpleItemContainer<MIT> implements Menu<MIT, EVT, OEVT, CEVT> {

    protected final List<Consumer<EVT>> clickCallbacks = new ArrayList<>();
    protected final List<Consumer<OEVT>> openCallbacks = new ArrayList<>();
    protected final List<Consumer<CEVT>> closeCallbacks = new ArrayList<>();

    protected Component title;

    public SimpleMenu(Class<MIT> clazz, int size) {
        super(clazz, size);
    }

    public SimpleMenu(MIT[] items) {
        super(items);
    }

    @Override
    public Component title() {
        return title;
    }

    @Override
    public void setTitle(Component title) {
        this.title = title;
    }

    @Override
    public void addClickListener(Consumer<EVT> callback) {
        clickCallbacks.add(callback);
    }

    @Override
    public void addOpenListener(Consumer<OEVT> callback) {
        openCallbacks.add(callback);
    }

    @Override
    public void addCloseListener(Consumer<CEVT> callback) {
        closeCallbacks.add(callback);
    }

}
