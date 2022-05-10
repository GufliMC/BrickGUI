package com.guflimc.brick.gui.spigot.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public abstract class AbstractItemStackBuilder<B extends AbstractItemStackBuilder<B>> {

    protected final ItemStack itemStack;

    protected AbstractItemStackBuilder(ItemStack itemStack) {
        this.itemStack = Objects.requireNonNull(itemStack, "ItemStack cannot be null.").clone();
    }

    public ItemStack build() {
        return this.itemStack;
    }

    // MODIFIERS

    private B thiz() {
        return (B) this;
    }

    public B transform(Consumer<ItemStack> is) {
        is.accept(this.itemStack);
        return thiz();
    }

    public B apply(Consumer<B> consumer) {
        consumer.accept(thiz());
        return thiz();
    }

    public B apply(boolean condition, Consumer<B> consumer) {
        if ( condition ) {
            consumer.accept(thiz());
        }
        return thiz();
    }

    public B apply(boolean condition, Consumer<B> consumer, Consumer<B> elseConsumer) {
        if ( condition ) {
            consumer.accept(thiz());
        } else {
            elseConsumer.accept(thiz());
        }
        return thiz();
    }

    // BASIC

    public B applyMeta(Consumer<ItemMeta> meta) {
        return applyMeta(ItemMeta.class, meta);
    }

    public <T extends ItemMeta> B applyMeta(Class<T> type, Consumer<T> meta) {
        T m = type.cast(this.itemStack.getItemMeta());
        if (m != null) {
            meta.accept(m);
            this.itemStack.setItemMeta(m);
        }
        return thiz();
    }

    public B withName(String name) {
        return applyMeta(meta -> meta.setDisplayName(name));
    }

    public B withType(Material material) {
        return transform(itemStack -> itemStack.setType(material));
    }

    public B withLore(String... lines) {
        return withLore(Arrays.asList(lines));
    }

    public B withLore(Iterable<String> lines) {
        return applyMeta(meta -> {
            List<String> lore = meta.getLore() == null ? new ArrayList<>() : meta.getLore();
            for (String line : lines) {
                lore.add(ChatColor.GRAY + ChatColor.translateAlternateColorCodes('&', line));
            }
            meta.setLore(lore);
        });
    }

    public B clearLore() {
        return applyMeta(meta -> meta.setLore(new ArrayList<>()));
    }

    public B withToolDamage(int damage) {
        return applyMeta(Damageable.class, meta -> meta.setDamage(damage));
    }

    public B withAmount(int amount) {
        return transform(itemStack -> itemStack.setAmount(amount));
    }

    // ENCHANTMENTS

    public B withEnchantment(Enchantment enchantment, int level) {
        return transform(itemStack -> itemStack.addUnsafeEnchantment(enchantment, level));
    }

    public B withEnchantment(Enchantment enchantment) {
        return transform(itemStack -> itemStack.addUnsafeEnchantment(enchantment, 1));
    }

    public B clearEnchantments() {
        return transform(itemStack -> itemStack.getEnchantments().keySet().forEach(itemStack::removeEnchantment));
    }

    // ITEMFLAGS

    public B withItemFlag(ItemFlag... flags) {
        return applyMeta(meta -> meta.addItemFlags(flags));
    }

    public B withoutItemFlag(ItemFlag... flags) {
        return applyMeta(meta -> meta.removeItemFlags(flags));
    }

    // ATTRIBUTES

    public B hideAttributes() {
        return withItemFlag(ItemFlag.values());
    }

    public B showAttributes() {
        return withoutItemFlag(ItemFlag.values());
    }

}
