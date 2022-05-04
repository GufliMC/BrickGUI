package com.guflimc.mastergui.bukkit.item.specific;

import com.guflimc.mastergui.bukkit.item.AbstractItemStackBuilder;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Arrays;
import java.util.List;

public class FireworkBuilder extends AbstractItemStackBuilder<FireworkBuilder> {

    public static FireworkBuilder create() {
        return new FireworkBuilder(new ItemStack(Material.FIREWORK_ROCKET));
    }

    //

    protected FireworkBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    //

    public FireworkBuilder withPower(int power) {
        return applyMeta(FireworkMeta.class, meta ->
                meta.setPower(power));
    }

    public FireworkBuilder withEffect(FireworkEffect effect) {
        return applyMeta(FireworkMeta.class, meta ->
                meta.addEffect(effect));
    }

    public FireworkBuilder withEffects(FireworkEffect... effects) {
        return withEffects(Arrays.asList(effects));
    }

    public FireworkBuilder withEffects(List<FireworkEffect> effects) {
        return applyMeta(FireworkMeta.class, meta -> {
            meta.clearEffects();
            meta.addEffects(effects);
        });
    }
}
