package com.guflimc.brick.gui.common.factory;

import com.guflimc.brick.gui.api.click.ClickFunction;

import java.util.function.BiFunction;

public interface ItemFactory<IST, EVT, MIT> extends BiFunction<IST, ClickFunction<EVT>, MIT> {
}