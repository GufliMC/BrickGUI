package com.guflimc.mastergui.bukkit.item.specific;

import com.guflimc.mastergui.bukkit.item.AbstractItemStackBuilder;
import com.guflimc.mastergui.bukkit.item.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.List;

public class BookBuilder extends AbstractItemStackBuilder<BookBuilder> {

    public enum BookType {
        WRITTEN, WRITABLE;
    }

    public static BookBuilder create(BookType type) {
        return new BookBuilder(new ItemStack(Material.valueOf(type.name() + "_BOOK")));
    }

    //

    protected BookBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    //

    public BookBuilder withAuthor(String author) {
        return applyMeta(BookMeta.class, meta ->
                meta.setAuthor(author));
    }

    public BookBuilder withTitle(String title) {
        return applyMeta(BookMeta.class, meta ->
                meta.setTitle(title));
    }

    public BookBuilder withPage(String contents) {
        return applyMeta(BookMeta.class, meta ->
                meta.addPage(contents));
    }

    public BookBuilder withPages(String... contents) {
        return applyMeta(BookMeta.class, meta ->
                meta.setPages(contents));
    }

    public BookBuilder withPages(List<String> contents) {
        return applyMeta(BookMeta.class, meta ->
                meta.setPages(contents));
    }
}
