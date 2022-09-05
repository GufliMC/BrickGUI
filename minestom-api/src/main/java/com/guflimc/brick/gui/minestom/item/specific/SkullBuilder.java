package com.guflimc.brick.gui.minestom.item.specific;

import com.guflimc.brick.gui.minestom.item.AbstractItemStackBuilder;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.metadata.PlayerHeadMeta;

import java.util.UUID;

public class SkullBuilder extends AbstractItemStackBuilder<SkullBuilder> {

    //

    public static SkullBuilder create() {
        return new SkullBuilder(ItemStack.of(Material.PLAYER_HEAD));
    }

    //

    private SkullBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    public ItemStack build() {
        return this.itemStack;
    }

    //

    public SkullBuilder withSkullOwner(UUID owner) {
        return applyMeta(PlayerHeadMeta.class, meta -> meta.skullOwner(owner));
    }

    public SkullBuilder withSkullTexture(PlayerSkin skin) {
        return applyMeta(PlayerHeadMeta.class, meta -> meta.playerSkin(skin));
    }

    public SkullBuilder withSkullTexture(String textures, String signature) {
        return withSkullTexture(new PlayerSkin(textures, signature));
    }

}
