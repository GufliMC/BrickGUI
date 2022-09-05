package com.guflimc.brick.gui.minestom.item;

import com.guflimc.brick.gui.minestom.item.specific.*;
import net.minestom.server.color.DyeColor;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class ItemStackBuilder extends AbstractItemStackBuilder<ItemStackBuilder> {

    protected ItemStackBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    //

    public static ItemStackBuilder of(Material material) {
        return new ItemStackBuilder(ItemStack.of(material)).hideAttributes();
    }

    public static ItemStackBuilder of(ItemStack itemStack) {
        return new ItemStackBuilder(itemStack).hideAttributes();
    }

    private static ItemStackBuilder of(String material) {
        return ItemStackBuilder.of(Material.fromNamespaceId(material));
    }

    // HELPERS

    public static SkullBuilder skull() {
        return SkullBuilder.create();
    }

    public static WrittenBookBuilder book() {
        return WrittenBookBuilder.create();
    }

    public static FireworkBuilder firework() {
        return FireworkBuilder.create();
    }

    public static LeatherArmorBuilder leatherArmor(LeatherArmorBuilder.ArmorType type) {
        return LeatherArmorBuilder.create(type);
    }

    public static PotionBuilder potion(PotionBuilder.PotionType type) {
        return PotionBuilder.create(type);
    }

    public static BannerBuilder banner(DyeColor color) {
        return BannerBuilder.create(color);
    }

    public static ItemStackBuilder wool(DyeColor color) {
        return ItemStackBuilder.of(color.name().toLowerCase() + "_wool");
    }

    public static ItemStackBuilder terracotta(DyeColor color) {
        return ItemStackBuilder.of(color.name().toLowerCase() + "_terracotta");
    }

    public static ItemStackBuilder glass(DyeColor color) {
        return ItemStackBuilder.of(color.name().toLowerCase() + "_stained_glass");
    }

    public static ItemStackBuilder bed(DyeColor color) {
        return ItemStackBuilder.of(color.name().toLowerCase() + "_bed");
    }

    public static ItemStackBuilder carpet(DyeColor color) {
        return ItemStackBuilder.of(color.name().toLowerCase() + "_carpet");
    }

    public static ItemStackBuilder dye(DyeColor color) {
        return ItemStackBuilder.of(color.name().toLowerCase() + "_dye");
    }

    public static ItemStackBuilder shulkerBox(DyeColor color) {
        return ItemStackBuilder.of(color.name().toLowerCase() + "_shulker_box");
    }

}
