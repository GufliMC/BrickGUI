package com.guflimc.brick.gui.common.builder;

import com.guflimc.brick.gui.api.builder.LayoutMenuBuilder;
import com.guflimc.brick.gui.api.click.ClickFunction;
import com.guflimc.brick.gui.api.menu.ItemContainer;
import com.guflimc.brick.gui.api.menu.MenuItem;
import com.guflimc.brick.gui.common.factory.ItemFactory;
import com.guflimc.brick.gui.common.factory.MenuFactory;
import net.kyori.adventure.text.Component;

import java.util.function.Consumer;

public class StandardLayoutMenuBuilder
        <IST, EVT, MIT extends MenuItem, MT extends ItemContainer<MIT>, B extends StandardLayoutMenuBuilder<IST, EVT, MIT, MT, B>>
        extends SimpleLayoutMenuBuilder<MIT> implements LayoutMenuBuilder<IST, EVT, MT> {

    private Component title = null;

    private final ItemFactory<IST, EVT, MIT> itemFactory;
    private final MenuFactory<MIT, MT> menuFactory;

    //

    public StandardLayoutMenuBuilder(Class<MIT> type, ItemFactory<IST, EVT, MIT> itemFactory, MenuFactory<MIT, MT> menuFactory) {
        super(type);
        this.itemFactory = itemFactory;
        this.menuFactory = menuFactory;
    }

    @Override
    public B withTitle(Component title) {
        this.title = title;
        return (B) this;
    }

    @Override
    public B withItem(IST itemStack) {
        super.withItem(itemFactory.apply(itemStack, null));
        return (B) this;
    }

    @Override
    public B withItem(IST itemStack, ClickFunction<EVT> consumer) {
        super.withItem(itemFactory.apply(itemStack, consumer));
        return (B) this;
    }

    @Override
    public B withItem(IST itemStack, Consumer<EVT> consumer) {
        return withItem(itemStack, ClickFunction.from(consumer));
    }

    @Override
    public B withActionbarItem(int index, IST itemStack, ClickFunction<EVT> consumer) {
        this.withActionbarItem(index, itemFactory.apply(itemStack, consumer));
        return (B) this;
    }

    @Override
    public B withActionbarItem(int index, IST itemStack, Consumer<EVT> consumer) {
        return this.withActionbarItem(index, itemStack, ClickFunction.from(consumer));
    }

    //

    @Override
    public MT build() {
        return menuFactory.apply(title, super.compile());
    }

}
