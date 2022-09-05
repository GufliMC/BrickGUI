package com.guflimc.brick.gui.api.builder;

import com.guflimc.brick.gui.api.click.ClickFunction;
import com.guflimc.brick.gui.api.menu.ItemContainer;

import java.util.function.Consumer;

public interface LayoutMenuBuilder<IST, EVT, MT extends ItemContainer<?>> extends MenuBuilder {

    LayoutMenuBuilder<IST, EVT, MT> withItem(IST itemStack);

    LayoutMenuBuilder<IST, EVT, MT> withItem(IST itemStack, ClickFunction<EVT> consumer);

    LayoutMenuBuilder<IST, EVT, MT> withItem(IST itemStack, Consumer<EVT> consumer);

    LayoutMenuBuilder<IST, EVT, MT> withActionbarItem(int index, IST itemStack, Consumer<EVT> consumer);

    LayoutMenuBuilder<IST, EVT, MT> withActionbarItem(int index, IST itemStack, ClickFunction<EVT> consumer);

    //

    public MT build();

}
