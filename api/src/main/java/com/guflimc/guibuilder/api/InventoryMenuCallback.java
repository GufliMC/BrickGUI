package com.guflimc.guibuilder.api;

import com.guflimc.guibuilder.api.menu.MenuItem;

public interface InventoryMenuCallback {

    void onOpen(BukkitPlayer player);

    void onClose(BukkitPlayer player);

    void onClick(BukkitPlayer player, ClickType clickType, int slot, MenuItem item);

}
