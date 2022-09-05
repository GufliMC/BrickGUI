package com.guflimc.brick.gui.common.factory;

import net.kyori.adventure.text.Component;

import java.util.function.BiFunction;

public interface MenuFactory<MIT, MT> extends BiFunction<Component, MIT[], MT> {
}