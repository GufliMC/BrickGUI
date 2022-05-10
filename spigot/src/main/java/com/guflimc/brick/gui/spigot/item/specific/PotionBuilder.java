package com.guflimc.brick.gui.spigot.item.specific;

import com.guflimc.brick.gui.spigot.item.AbstractItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;

public class PotionBuilder extends AbstractItemStackBuilder<PotionBuilder> {

    public enum PotionType {
        NORMAL(""),
        SPLASH("SPLASH_"),
        LINGERING("LINGERING_");

        private final String prefix;
        private PotionType(String prefix) {
            this.prefix = prefix;
        }
    }

    public static PotionBuilder create(PotionType type) {
        return new PotionBuilder(new ItemStack(Material.valueOf(type.prefix + "POTION")));
    }

    //

    protected PotionBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    //

    public PotionBuilder withPotionEffect(PotionEffect effect) {
        return applyMeta(PotionMeta.class, meta ->
                meta.addCustomEffect(effect, true));
    }

    public PotionBuilder withPotionEffects(PotionEffect... effects) {
        return withPotionEffects(Arrays.asList(effects));
    }

    public PotionBuilder withPotionEffects(List<PotionEffect> effects) {
        return applyMeta(PotionMeta.class, meta -> {
            for ( PotionEffect effect : effects ) {
                meta.addCustomEffect(effect, true);
            }
        });
    }

    public PotionBuilder withPotionEffect(PotionEffectType type) {
        return applyMeta(PotionMeta.class, meta ->
                meta.addCustomEffect(new PotionEffect(type, 1200, 0), true));
    }

    public PotionBuilder withPotionEffect(PotionEffectType type, int duration) {
        return applyMeta(PotionMeta.class, meta ->
                meta.addCustomEffect(new PotionEffect(type, duration, 0), true));
    }

    public PotionBuilder withPotionEffect(PotionEffectType type, int duration, int amplifier) {
        return applyMeta(PotionMeta.class, meta ->
                meta.addCustomEffect(new PotionEffect(type, duration, amplifier), true));
    }

    public PotionBuilder withPotionEffect(PotionEffectType type, int duration, int amplifier, boolean ambient) {
        return applyMeta(PotionMeta.class, meta ->
                meta.addCustomEffect(new PotionEffect(type, duration, amplifier, ambient), true));
    }

    public PotionBuilder withPotionEffect(PotionEffectType type, int duration, int amplifier, boolean ambient, boolean particles) {
        return applyMeta(PotionMeta.class, meta ->
                meta.addCustomEffect(new PotionEffect(type, duration, amplifier, ambient, particles), true));
    }
}
