package com.guflimc.brick.gui.api.menu;

public interface ItemContainer<MIT extends MenuItem> {

    int size();

    void setItem(int index, MIT item);

    void remove(int index);

}
