package com.guflimc.brick.gui.spigot.item.specific;

import com.guflimc.brick.gui.spigot.item.AbstractItemStackBuilder;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class SkullBuilder extends AbstractItemStackBuilder<SkullBuilder> {

    //

    public static SkullBuilder create() {
        return new SkullBuilder(new ItemStack(Material.PLAYER_HEAD));
    }

    //

    private SkullBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    public ItemStack build() {
        return this.itemStack;
    }

    //

    public SkullBuilder withPlayer(OfflinePlayer owner) {
        return applyMeta(SkullMeta.class, meta -> {
            meta.setOwningPlayer(owner);
        });
    }

    public SkullBuilder withPlayer(PlayerProfile profile) {
        return applyMeta(SkullMeta.class, meta -> {
            meta.setOwnerProfile(profile);
        });
    }

    public SkullBuilder withPlayer(UUID uuid, String name) {
        return withPlayer(Bukkit.createPlayerProfile(uuid, name));
    }

    public SkullBuilder withPlayer(UUID uuid) {
        return withPlayer(Bukkit.createPlayerProfile(uuid, RandomStringUtils.randomAlphanumeric(16)));
    }

    public SkullBuilder withTexture(String textureId) {
        PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID(), RandomStringUtils.randomAlphanumeric(16));
        try {
            profile.getTextures().setSkin(new URL("https://textures.minecraft.net/texture/" + textureId));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return withPlayer(profile);
    }

}
