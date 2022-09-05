package com.guflimc.brick.gui.minestom.item;

import net.kyori.adventure.text.Component;
import net.minestom.server.item.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class AbstractItemStackBuilder<B extends AbstractItemStackBuilder<B>> {

    protected ItemStack itemStack;

    protected AbstractItemStackBuilder(ItemStack itemStack) {
        this.itemStack = Objects.requireNonNull(itemStack, "ItemStack cannot be null.");
    }

    public ItemStack build() {
        return this.itemStack;
    }

    // MODIFIERS

    private B thiz() {
        return (B) this;
    }

    public B transform(Function<ItemStack, ItemStack> is) {
        this.itemStack = is.apply(this.itemStack);
        return thiz();
    }

    public B apply(Consumer<B> consumer) {
        consumer.accept(thiz());
        return thiz();
    }

    public B apply(boolean condition, Consumer<B> consumer) {
        if (condition) {
            consumer.accept(thiz());
        }
        return thiz();
    }

    public B apply(boolean condition, Consumer<B> consumer, Consumer<B> elseConsumer) {
        if (condition) {
            consumer.accept(thiz());
        } else {
            elseConsumer.accept(thiz());
        }
        return thiz();
    }

    // BASIC

    public B applyMeta(Consumer<ItemMeta.Builder> meta) {
        itemStack = itemStack.withMeta(meta);
        return thiz();
    }

    public <V extends ItemMetaView.Builder, T extends ItemMetaView<V>> B applyMeta(Class<T> type, Consumer<V> meta) {
        itemStack = itemStack.withMeta(type, meta);
        return thiz();
    }

    public B withName(Component name) {
        return applyMeta(meta -> meta.displayName(name));
    }

    public B withType(Material material) {
        return transform(itemStack -> ItemStack.of(material).withMeta(itemStack.meta()));
    }

    public B withLore(Component... lines) {
        return withLore(Arrays.asList(lines));
    }

    public B withLore(List<Component> lines) {
        return applyMeta(meta -> meta.lore(lines));
    }

    public B clearLore() {
        return applyMeta(meta -> meta.lore(Collections.emptyList()));
    }

    public B withToolDamage(int damage) {
        return applyMeta(meta -> meta.damage(damage));
    }

    public B withAmount(int amount) {
        return transform(itemStack -> itemStack.withAmount(amount));
    }

    // ENCHANTMENTS

    public B withEnchantment(Enchantment enchantment, short level) {
        return applyMeta(meta -> meta.enchantment(enchantment, level));
    }

    public B withEnchantment(Enchantment enchantment) {
        return withEnchantment(enchantment, (short) 1);
    }

    public B clearEnchantments() {
        return applyMeta(ItemMeta.Builder::clearEnchantment);
    }

    // ITEMFLAGS

    public B withItemHideFlag(ItemHideFlag... flags) {
        return applyMeta(meta -> meta.hideFlag(flags));
    }

    public B clearItemHideFlags() {
        return applyMeta(meta -> meta.hideFlag(0));
    }

    // ATTRIBUTES

    public B hideAttributes() {
        return withItemHideFlag(ItemHideFlag.values());
    }

    public B showAttributes() {
        return clearItemHideFlags();
    }

}
