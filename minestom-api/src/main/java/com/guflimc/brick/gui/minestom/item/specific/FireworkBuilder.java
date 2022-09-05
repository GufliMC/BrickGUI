package com.guflimc.brick.gui.minestom.item.specific;

import com.guflimc.brick.gui.minestom.item.AbstractItemStackBuilder;
import net.minestom.server.color.Color;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.firework.FireworkEffect;
import net.minestom.server.item.firework.FireworkEffectType;
import net.minestom.server.item.metadata.FireworkMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FireworkBuilder extends AbstractItemStackBuilder<FireworkBuilder> {

    public static FireworkBuilder create() {
        return new FireworkBuilder(ItemStack.of(Material.FIREWORK_ROCKET));
    }

    //

    protected FireworkBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    //

    public FireworkBuilder withPower(byte power) {
        return applyMeta(FireworkMeta.class, meta ->
                meta.flightDuration(power));
    }

    public FireworkBuilder withEffect(FireworkEffect effect) {
        List<FireworkEffect> effects = new ArrayList<>(itemStack.meta(FireworkMeta.class).getEffects());
        effects.add(effect);
        return withEffects(effects);
    }

    public FireworkBuilder withEffects(FireworkEffect... effects) {
        return withEffects(Arrays.asList(effects));
    }

    public FireworkBuilder withEffects(List<FireworkEffect> effects) {
        return applyMeta(FireworkMeta.class, meta -> meta.effects(effects));
    }

    public FireworkBuilder withEffect(FireworkEffectType type, boolean flicker, boolean trail, Color... colors) {
        return withEffect(new FireworkEffect(flicker, trail, type, Arrays.asList(colors), Collections.emptyList()));
    }

    public FireworkBuilder withEffect(FireworkEffectType type, boolean flicker, boolean trail, List<Color> colors, List<Color> fadeColors) {
        return withEffect(new FireworkEffect(flicker, trail, type, colors, fadeColors));
    }

    public FireworkBuilder withEffect(FireworkEffectType type, boolean flicker, boolean trail, Color color, Color fadeColor) {
        return withEffect(new FireworkEffect(flicker, trail, type, List.of(color), List.of(fadeColor)));
    }
}
