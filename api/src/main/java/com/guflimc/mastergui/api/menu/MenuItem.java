package com.guflimc.mastergui.api.menu;

import java.util.function.Consumer;

public interface MenuItem {

    Object handle();

    Consumer<?> callback();
}
