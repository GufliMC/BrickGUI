package com.guflimc.mastergui.bukkit.item.specific;

import com.guflimc.mastergui.bukkit.item.AbstractItemStackBuilder;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import java.util.Arrays;
import java.util.List;

public class BannerBuilder extends AbstractItemStackBuilder<BannerBuilder> {

    public static BannerBuilder create(DyeColor color) {
        return new BannerBuilder(new ItemStack(Material.valueOf(color.name() + "_BANNER")));
    }

    //

    protected BannerBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    //

    public BannerBuilder withBannerPattern(Pattern pattern) {
        return applyMeta(BannerMeta.class, meta ->
                meta.addPattern(pattern));
    }

    public BannerBuilder withBannerPattern(DyeColor color, PatternType type) {
        return applyMeta(BannerMeta.class, meta ->
                meta.addPattern(new Pattern(color, type)));
    }

    public BannerBuilder withBannerPattern(int layer, Pattern pattern) {
        return applyMeta(BannerMeta.class, meta ->
                meta.setPattern(layer, pattern));
    }

    public BannerBuilder withBannerPattern(int layer, DyeColor color, PatternType type) {
        return applyMeta(BannerMeta.class, meta ->
                meta.setPattern(layer, new Pattern(color, type)));
    }

    public BannerBuilder withBannerPatterns(Pattern... patterns) {
        return withPatterns(Arrays.asList(patterns));
    }

    public BannerBuilder withPatterns(List<Pattern> patterns) {
        return applyMeta(BannerMeta.class, meta ->
                meta.setPatterns(patterns));
    }
}
