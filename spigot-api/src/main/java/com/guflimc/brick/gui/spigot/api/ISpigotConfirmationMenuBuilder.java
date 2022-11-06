package com.guflimc.brick.gui.spigot.api;

import com.guflimc.brick.gui.spigot.builder.SpigotConfirmationMenuBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface ISpigotConfirmationMenuBuilder {

    SpigotConfirmationMenuBuilder withDisplay(@NotNull ItemStack item);

    SpigotConfirmationMenuBuilder withAcceptItem(@NotNull ItemStack item);

    SpigotConfirmationMenuBuilder withAcceptItemName(@NotNull Component text);

    SpigotConfirmationMenuBuilder withAcceptItemName(@NotNull String text);

    SpigotConfirmationMenuBuilder withDenyItem(@NotNull ItemStack item);

    SpigotConfirmationMenuBuilder withDenyItemName(@NotNull Component text);

    SpigotConfirmationMenuBuilder withDenyItemName(@NotNull String text);

    SpigotConfirmationMenuBuilder withTitle(@NotNull String title);

    SpigotConfirmationMenuBuilder withTitle(@NotNull Component title);

    SpigotConfirmationMenuBuilder withAccept(@NotNull Runnable action);

    SpigotConfirmationMenuBuilder withDeny(@NotNull Runnable action);

    ISpigotMenu build();

}
