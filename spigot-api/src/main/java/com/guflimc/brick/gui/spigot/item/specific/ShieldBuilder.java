package com.guflimc.brick.gui.spigot.item.specific;

import com.guflimc.brick.gui.spigot.item.AbstractItemStackBuilder;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.util.List;

public class ShieldBuilder extends AbstractItemStackBuilder<ShieldBuilder> {

    private final BannerBuilder bannerBuilder;

    public static ShieldBuilder create(DyeColor color) {
        return new ShieldBuilder(color);
    }

    //

    protected ShieldBuilder(DyeColor color) {
        super(new ItemStack(Material.SHIELD));
        this.bannerBuilder = new BannerBuilder(new ItemStack(Material.valueOf(color.name() + "_BANNER")));
    }

    //

    public ShieldBuilder withBannerPattern(Pattern pattern) {
        return withBanner(bannerBuilder.withBannerPattern(pattern).build());
    }

    public ShieldBuilder withBannerPattern(DyeColor color, PatternType type) {
        return withBanner(bannerBuilder.withBannerPattern(color, type).build());
    }

    public ShieldBuilder withBannerPattern(int layer, Pattern pattern) {
        return withBanner(bannerBuilder.withBannerPattern(layer, pattern).build());
    }

    public ShieldBuilder withBannerPattern(int layer, DyeColor color, PatternType type) {
        return withBanner(bannerBuilder.withBannerPattern(layer, color, type).build());
    }

    public ShieldBuilder withBannerPatterns(Pattern... patterns) {
        return withBanner(bannerBuilder.withBannerPatterns(patterns).build());
    }

    public ShieldBuilder withBannerPatterns(List<Pattern> patterns) {
        return withBanner(bannerBuilder.withBannerPatterns(patterns).build());
    }

    private ShieldBuilder withBanner(ItemStack item) {
        if (item.getType().name().contains("BANNER")) {
            throw new IllegalArgumentException("Item must be a banner!");
        }
        BannerMeta bmeta = (BannerMeta) item.getItemMeta();

        return applyMeta(BlockStateMeta.class, meta -> {
            Banner b = (Banner) meta.getBlockState();
            b.setPatterns(bmeta.getPatterns());
            b.setType(item.getType());
            meta.setBlockState(b);
        });
    }
}
