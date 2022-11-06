package com.guflimc.brick.gui.spigot.item.specific;

import com.guflimc.brick.gui.spigot.item.AbstractItemStackBuilder;
import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.Arrays;
import java.util.List;

public class BookBuilder extends AbstractItemStackBuilder<BookBuilder> {

    private Component title;
    private Component author;
    private List<Component> pages;

    public static BookBuilder create() {
        return new BookBuilder(new ItemStack(Material.WRITTEN_BOOK));
    }

    //

    protected BookBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    //

    public BookBuilder withAuthor(String author) {
        return withAuthor(LegacyComponentSerializer.legacySection().deserialize(author));
    }

    public BookBuilder withAuthor(Component author) {
        this.author = author;
        return applyMeta(BookMeta.class, meta ->
                meta.setAuthor(LegacyComponentSerializer.legacySection().serialize(author)));
    }

    public BookBuilder withTitle(String title) {
        return withTitle(LegacyComponentSerializer.legacySection().deserialize(title));
    }

    public BookBuilder withTitle(Component title) {
        this.title = title;
        return applyMeta(BookMeta.class, meta ->
                meta.setTitle(LegacyComponentSerializer.legacySection().serialize(title)));
    }

    public BookBuilder withTitle(String... pages) {
        return withPages(Arrays.stream(pages)
                .map(page -> (Component) LegacyComponentSerializer.legacySection().deserialize(page))
                .toList());
    }

    public BookBuilder withPages(Component... pages) {
        return withPages(List.of(pages));
    }

    public BookBuilder withPages(List<Component> pages) {
        this.pages = List.copyOf(pages);
        return applyMeta(BookMeta.class, meta ->
                meta.spigot().setPages(pages.stream()
                        .map(page -> GsonComponentSerializer.gson().serialize(page))
                        .map(ComponentSerializer::parse)
                        .toList()));
    }

    public Book adventure() {
        if (title == null) {
            throw new IllegalStateException("Title is not set.");
        }
        if (author == null) {
            throw new IllegalStateException("Author is not set.");
        }
        if (pages == null) {
            throw new IllegalStateException("Pages is not set.");
        }
        return Book.book(title, author, pages);
    }

}
