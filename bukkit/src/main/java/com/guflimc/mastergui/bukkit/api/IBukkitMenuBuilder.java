package com.guflimc.mastergui.bukkit.api;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public interface IBukkitMenuBuilder {

    IBukkitMenuBuilder withTitle(String title);

    IBukkitMenuBuilder withItem(ItemStack itemStack);

    IBukkitMenuBuilder withItem(ItemStack itemStack, Consumer<InventoryClickEvent> consumer);

    IBukkitMenu build();

}
