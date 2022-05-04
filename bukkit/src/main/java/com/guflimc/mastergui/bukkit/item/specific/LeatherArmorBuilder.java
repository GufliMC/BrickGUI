package com.guflimc.mastergui.bukkit.item.specific;

import com.guflimc.mastergui.bukkit.item.AbstractItemStackBuilder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class LeatherArmorBuilder extends AbstractItemStackBuilder<LeatherArmorBuilder> {

    public enum ArmorType {
        HELMET, CHESTPLATE, LEGGINGS, BOOTS, HORSE_ARMOR
    }

    public static LeatherArmorBuilder create(ArmorType type) {
        return new LeatherArmorBuilder(new ItemStack(Material.valueOf("LEATHER_" + type.name())));
    }

    //

    protected LeatherArmorBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    //

    public LeatherArmorBuilder withArmorColor(Color color) {
        Material type = itemStack.getType();
        if (type != Material.LEATHER_BOOTS && type != Material.LEATHER_CHESTPLATE
                && type != Material.LEATHER_HELMET && type != Material.LEATHER_LEGGINGS) {
            return this;
        }

        return applyMeta(LeatherArmorMeta.class, meta -> {
            meta.setColor(color);
        });
    }

    public LeatherArmorBuilder withArmorColor(int rgb) {
        return withArmorColor(Color.fromRGB(rgb));
    }

    public LeatherArmorBuilder withArmorColor(String hex) {
        if ( hex.charAt(0) == '#' ) hex = hex.substring(1);
        return withArmorColor(Color.fromRGB(Integer.parseInt(hex, 16)));
    }
}
