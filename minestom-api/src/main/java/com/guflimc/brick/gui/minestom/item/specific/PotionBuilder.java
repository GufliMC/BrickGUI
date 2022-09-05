package com.guflimc.brick.gui.minestom.item.specific;

import com.guflimc.brick.gui.minestom.item.AbstractItemStackBuilder;
import net.minestom.server.color.Color;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.metadata.PotionMeta;
import net.minestom.server.potion.CustomPotionEffect;
import net.minestom.server.potion.PotionEffect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PotionBuilder extends AbstractItemStackBuilder<PotionBuilder> {

    public enum PotionType {
        NORMAL(""),
        SPLASH("splash_"),
        LINGERING("lingering_");

        private final String prefix;

        private PotionType(String prefix) {
            this.prefix = prefix;
        }
    }

    public static PotionBuilder create(PotionType type) {
        return new PotionBuilder(ItemStack.of(Material.fromNamespaceId(type.prefix + "potion")));
    }

    //

    protected PotionBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    //

    public PotionBuilder withDisplayType(net.minestom.server.potion.PotionType type) {
        return applyMeta(PotionMeta.class, meta -> meta.potionType(type));
    }

    public PotionBuilder withColor(Color color) {
        return applyMeta(PotionMeta.class, meta -> meta.color(color));
    }

    public PotionBuilder withPotionEffect(CustomPotionEffect effect) {
        List<CustomPotionEffect> effects = new ArrayList<>(itemStack.meta(PotionMeta.class).getCustomPotionEffects());
        effects.add(effect);
        return applyMeta(PotionMeta.class, meta -> meta.effects(effects));
    }

    public PotionBuilder withPotionEffects(CustomPotionEffect... effects) {
        return withPotionEffects(Arrays.asList(effects));
    }

    public PotionBuilder withPotionEffects(List<CustomPotionEffect> effects) {
        return applyMeta(PotionMeta.class, meta -> meta.effects(effects));
    }

    public PotionBuilder withPotionEffect(PotionEffect effect) {
        return withPotionEffect(new CustomPotionEffect((byte) effect.id(), (byte) 0, 1200, true, true, true));
    }

    public PotionBuilder withPotionEffect(PotionEffect effect, int duration) {
        return withPotionEffect(new CustomPotionEffect((byte) effect.id(), (byte) 0, duration, true, true, true));
    }

    public PotionBuilder withPotionEffect(PotionEffect effect, int duration, byte amplifier) {
        return withPotionEffect(new CustomPotionEffect((byte) effect.id(), amplifier, duration, true, true, true));
    }

    public PotionBuilder withPotionEffect(PotionEffect effect, int duration, byte amplifier, boolean ambient) {
        return withPotionEffect(new CustomPotionEffect((byte) effect.id(), amplifier, duration, ambient, true, true));
    }

    public PotionBuilder withPotionEffect(PotionEffect effect, int duration, byte amplifier, boolean ambient, boolean particles) {
        return withPotionEffect(new CustomPotionEffect((byte) effect.id(), amplifier, duration, ambient, particles, true));
    }

    public PotionBuilder withPotionEffect(PotionEffect effect, int duration, byte amplifier, boolean ambient, boolean particles, boolean icon) {
        return withPotionEffect(new CustomPotionEffect((byte) effect.id(), amplifier, duration, ambient, particles, icon));
    }
}
