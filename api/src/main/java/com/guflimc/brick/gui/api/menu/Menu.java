package com.guflimc.brick.gui.api.menu;

import net.kyori.adventure.text.Component;

import java.util.function.Consumer;

public interface Menu<MIT extends MenuItem, EVT, OEVT, CEVT> extends ItemContainer<MIT> {

    Component title();

    void setTitle(Component title);

    void addClickListener(Consumer<EVT> callback);

    void addOpenListener(Consumer<OEVT> callback);

    void addCloseListener(Consumer<CEVT> callback);

}
