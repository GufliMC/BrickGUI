package com.guflimc.mastergui.bukkit.item.specific;

import com.guflimc.mastergui.bukkit.item.AbstractItemStackBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    private static Class<?> GameProfile;
    private static Method GameProfile_getProperties;
    private static Class<?> Property;
    private static Method Property_getName;
    private static Method PropertyMap_put;
    private static Field CraftMetaSkull_profile;
    private static Method CraftMetaSkull_setOwningPlayer;

    private static String getServerVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().substring(23);
    }

    static {
        try {
            GameProfile = Class.forName("com.mojang.authlib.GameProfile");
            GameProfile_getProperties = GameProfile.getDeclaredMethod("getProperties");

            Property = Class.forName("com.mojang.authlib.properties.Property");
            Property_getName = Property.getMethod("getName");

            Class<?> PropertyMap = Class.forName("com.mojang.authlib.properties.PropertyMap");
            PropertyMap_put = PropertyMap.getMethod("put", Object.class, Object.class);

            Class<?> CraftMetaSkull = Class.forName("org.bukkit.craftbukkit." + getServerVersion() + ".inventory.CraftMetaSkull");
            CraftMetaSkull_profile = CraftMetaSkull.getDeclaredField("profile");
            CraftMetaSkull_profile.setAccessible(true);

            CraftMetaSkull_setOwningPlayer = CraftMetaSkull.getMethod("setOwningPlayer", OfflinePlayer.class);

            // Optional
            try {
                CraftMetaSkull_setOwningPlayer = CraftMetaSkull.getMethod("setOwningPlayer", OfflinePlayer.class);
            } catch (NoSuchMethodException ignored) {}

        } catch (NoSuchMethodException | ClassNotFoundException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public SkullBuilder withSkullOwner(OfflinePlayer owner) {
        if ( CraftMetaSkull_setOwningPlayer == null ) {
            return this;
        }
        return applyMeta(SkullMeta.class, meta -> {
            try {
                CraftMetaSkull_setOwningPlayer.invoke(meta, owner);
            } catch (IllegalAccessException | InvocationTargetException ignored) {}
        });
    }

    public SkullBuilder withSkullTexture(String texture) {
        try {
            UUID uuid = UUID.randomUUID();
            Object profile = GameProfile.getDeclaredConstructor(UUID.class, String.class).newInstance(uuid, uuid.toString().substring(0, 15));
            Object property = Property.getDeclaredConstructor(String.class, String.class).newInstance("textures", texture);
            Object properties = GameProfile_getProperties.invoke(profile);
            PropertyMap_put.invoke(properties, Property_getName.invoke(property), property);
            return withSkullProfile(profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public SkullBuilder withSkullTexture(UUID uuid) {
        try {
            Object profile = GameProfile.getDeclaredConstructor(UUID.class, String.class).newInstance(uuid, uuid.toString().substring(0, 15));
            return withSkullProfile(profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchMethodException | InstantiationException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    private SkullBuilder withSkullProfile(Object profile) {
        try {
            SkullMeta meta = (SkullMeta) this.itemStack.getItemMeta();
            CraftMetaSkull_profile.set(meta, profile);
            this.itemStack.setItemMeta(meta);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return this;
    }

}
