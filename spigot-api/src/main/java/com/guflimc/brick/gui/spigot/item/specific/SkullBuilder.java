package com.guflimc.brick.gui.spigot.item.specific;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.guflimc.brick.gui.spigot.item.AbstractItemStackBuilder;
import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.StreamSupport;

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

    public SkullBuilder withPlayer(UUID uuid) {
        return withPlayer(Bukkit.getOfflinePlayer(uuid));
    }

    public SkullBuilder withTexture(String textureId) {
        try {
            return withTexture(new URL("https://textures.minecraft.net/texture/" + textureId));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public SkullBuilder withTexture(URL textureUrl) {
        PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID(), RandomStringUtils.randomAlphanumeric(16));
        profile.getTextures().setSkin(textureUrl);
        return withPlayer(profile);
    }

    public CompletableFuture<SkullBuilder> withTexture(@NotNull UUID playerId) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(playerId);
        if (player.getPlayerProfile().getTextures().getSkin() != null) {
            return CompletableFuture.completedFuture(withPlayer(player));
        }
        return skinUrl(playerId).thenApply(url -> {
            if (url == null) {
                return this;
            }
            return withTexture(url);
        });
    }

    /**
     * Retrieves the skin url of the player with the given uuid. This only works for premium players.
     * @param playerId The uuid of the player
     * @return The skin url of the player or null if the player does not exist or does not have a skin.
     */
    public static CompletableFuture<URL> skinUrl(@NotNull UUID playerId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                URL url = new URL("https://sessionserver.mojang.com/session/minecraft/profile/"
                        + playerId.toString().replace("-", ""));

                JsonElement profile;
                try (InputStreamReader isr = new InputStreamReader(url.openStream())) {
                    profile = JsonParser.parseReader(isr);
                }

                if (profile.isJsonNull()) {
                    return null;
                }

                // array of objects of format: { name: "name", value: "value" }
                JsonArray properties = profile.getAsJsonObject().get("properties").getAsJsonArray();

                // get the first object with name "textures"
                JsonObject property = StreamSupport.stream(properties.spliterator(), false)
                        .map(JsonElement::getAsJsonObject)
                        .filter(prop -> prop.get("name").getAsString().equals("textures"))
                        .findFirst().orElse(null);

                if (property == null) {
                    return null;
                }

                // the value is base64 encoded json, decode it into bytes
                byte[] value = Base64.getDecoder().decode(property.get("value").getAsString());

                // parse the json value, result is a new object
                JsonObject propertyValue = JsonParser.parseString(new String(value)).getAsJsonObject();

                // gets the textures of the property value, this is an object
                JsonObject textures = propertyValue.get("textures").getAsJsonObject();

                // gets the skin of the textures object, this is an object
                JsonObject skin = textures.get("SKIN").getAsJsonObject();

                // gets the url of the skin object
                String result = skin.get("url").getAsString();
                return new URL(result);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }).exceptionally(ex -> {
            ex.printStackTrace();
            return null;
        });
    }

}
