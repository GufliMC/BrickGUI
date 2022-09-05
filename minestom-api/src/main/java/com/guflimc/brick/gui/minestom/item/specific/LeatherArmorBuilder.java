package com.guflimc.brick.gui.minestom.item.specific;

import com.guflimc.brick.gui.minestom.item.AbstractItemStackBuilder;
import net.minestom.server.color.Color;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.metadata.LeatherArmorMeta;

public class LeatherArmorBuilder extends AbstractItemStackBuilder<LeatherArmorBuilder> {

    public enum ArmorType {
        HELMET, CHESTPLATE, LEGGINGS, BOOTS, HORSE_ARMOR
    }

    public static LeatherArmorBuilder create(ArmorType type) {
        return new LeatherArmorBuilder(ItemStack.of(Material.fromNamespaceId("leather_" + type.name().toLowerCase())));
    }

    //

    protected LeatherArmorBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    //

    public LeatherArmorBuilder withArmorColor(Color color) {
        Material type = itemStack.material();
        if (type != Material.LEATHER_BOOTS && type != Material.LEATHER_CHESTPLATE
                && type != Material.LEATHER_HELMET && type != Material.LEATHER_LEGGINGS) {
            return this;
        }
        return applyMeta(LeatherArmorMeta.class, meta -> meta.color(color));
    }

    public LeatherArmorBuilder withArmorColor(int rgb) {
        return withArmorColor(new Color(rgb));
    }

    public LeatherArmorBuilder withArmorColor(String hex) {
        if (hex.charAt(0) == '#') hex = hex.substring(1);
        return withArmorColor(Integer.parseInt(hex, 16));
    }
}
