package com.guflimc.brick.gui.api.menu;

import com.guflimc.brick.gui.api.click.ClickFunction;

public interface MenuItem {

    Object handle();

    ClickFunction<?> clickEvent();
}
