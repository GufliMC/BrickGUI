package com.guflimc.mastergui.bukkit.api;

import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public interface IBukkitPaginatedMenuBuilder {

    IBukkitPaginatedMenuBuilder withTitle(Function<Integer, String> supplier);

    IBukkitPaginatedMenuBuilder withBackItem(ItemStack itemStack);

    IBukkitPaginatedMenuBuilder withNextItem(ItemStack itemStack);

    IBukkitMenu build();

}
