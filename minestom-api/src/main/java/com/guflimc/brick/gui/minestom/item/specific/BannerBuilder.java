package com.guflimc.brick.gui.minestom.item.specific;

import com.guflimc.brick.gui.minestom.item.AbstractItemStackBuilder;
import net.minestom.server.color.DyeColor;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class BannerBuilder extends AbstractItemStackBuilder<BannerBuilder> {

    public static BannerBuilder create(DyeColor color) {
        return new BannerBuilder(ItemStack.of(Material.fromNamespaceId(color.name() + "_banner")));
    }

    //

    protected BannerBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    //

    // TODO not implemented by minestom yet
    /*
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
     */
}
