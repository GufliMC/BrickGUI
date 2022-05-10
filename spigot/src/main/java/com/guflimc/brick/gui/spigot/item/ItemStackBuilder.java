package com.guflimc.brick.gui.spigot.item;

import com.guflimc.brick.gui.spigot.bukkit.item.specific.*;
import com.guflimc.brick.gui.spigot.item.specific.*;
import com.guflimc.mastergui.bukkit.item.specific.*;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemStackBuilder extends AbstractItemStackBuilder<ItemStackBuilder> {

    protected ItemStackBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    //

    public static ItemStackBuilder of(Material material) {
        return new ItemStackBuilder(new ItemStack(material)).hideAttributes();
    }

    public static ItemStackBuilder of(ItemStack itemStack) {
        return new ItemStackBuilder(itemStack).hideAttributes();
    }

    private static ItemStackBuilder of(String material) {
        return ItemStackBuilder.of(Material.valueOf(material));
    }

    // HELPERS

    public static SkullBuilder skull() {
        return SkullBuilder.create();
    }

    public static LeatherArmorBuilder leatherArmor(LeatherArmorBuilder.ArmorType type) {
        return LeatherArmorBuilder.create(type);
    }

    public static BookBuilder book(BookBuilder.BookType type) {
        return BookBuilder.create(type);
    }

    public static FireworkBuilder firework() {
        return FireworkBuilder.create();
    }

    public static PotionBuilder potion(PotionBuilder.PotionType type) {
        return PotionBuilder.create(type);
    }

    public static BannerBuilder banner(DyeColor color) {
        return BannerBuilder.create(color);
    }

    public static ItemStackBuilder wool(DyeColor color) {
        return ItemStackBuilder.of(color.name() + "_WOOL");
    }

    public static ItemStackBuilder terracotta(DyeColor color) {
        return ItemStackBuilder.of(color.name() + "_TERRACOTTA");
    }

    public static ItemStackBuilder glass(DyeColor color) {
        return ItemStackBuilder.of(color.name() + "_STAINED_GLASS");
    }

    public static ItemStackBuilder bed(DyeColor color) {
        return ItemStackBuilder.of(color.name() + "_BED");
    }

    public static ItemStackBuilder carpet(DyeColor color) {
        return ItemStackBuilder.of(color.name() + "_CARPET");
    }

    public static ItemStackBuilder dye(DyeColor color) {
        return ItemStackBuilder.of(color.name() + "_DYE");
    }

}
