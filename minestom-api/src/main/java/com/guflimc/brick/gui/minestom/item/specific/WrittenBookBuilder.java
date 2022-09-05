package com.guflimc.brick.gui.minestom.item.specific;

import com.guflimc.brick.gui.minestom.item.AbstractItemStackBuilder;
import net.kyori.adventure.text.Component;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.metadata.WrittenBookMeta;

import java.util.ArrayList;
import java.util.List;

public class WrittenBookBuilder extends AbstractItemStackBuilder<WrittenBookBuilder> {

    public static WrittenBookBuilder create() {
        return new WrittenBookBuilder(ItemStack.of(Material.WRITTEN_BOOK));
    }

    //

    protected WrittenBookBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    //

    public WrittenBookBuilder withAuthor(String author) {
        return applyMeta(WrittenBookMeta.class, meta ->
                meta.author(author));
    }

    public WrittenBookBuilder withAuthor(Component author) {
        return applyMeta(WrittenBookMeta.class, meta ->
                meta.author(author));
    }

    public WrittenBookBuilder withTitle(String title) {
        return applyMeta(WrittenBookMeta.class, meta ->
                meta.title(title));
    }

    public WrittenBookBuilder withTitle(Component title) {
        return applyMeta(WrittenBookMeta.class, meta ->
                meta.title(title));
    }

    public WrittenBookBuilder withPage(Component text) {
        List<Component> pages = new ArrayList<>(itemStack.meta(WrittenBookMeta.class).getPages());
        pages.add(text);
        return applyMeta(WrittenBookMeta.class, meta ->
                meta.pages(pages));
    }

    public WrittenBookBuilder withPages(Component... pages) {
        return applyMeta(WrittenBookMeta.class, meta ->
                meta.pages(pages));
    }

    public WrittenBookBuilder withPages(List<Component> pages) {
        return applyMeta(WrittenBookMeta.class, meta ->
                meta.pages(pages));
    }
}
