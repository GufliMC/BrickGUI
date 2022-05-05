# MasterGUI

A lightweight API for creating gui's for Minecraft.


## Platforms

* [x] Bukkit / Spigot / Paper (1.18+)

## Usage
### Gradle
```
repositories {
    maven { url "https://repo.jorisg.com/snapshots" }
}

dependencies {
    implementation 'com.guflimc.mastergui:bukkit:1.0-SNAPSHOT'
}
```

### Examples

Register events when the plugin enables.
```java
BukkitMasterGUI.register(this);
```

Using the itemstack builder
```java
ItemStack back = ItemStackBuilder.of(Material.PAPER).withName(ChatColor.GREEN + "Back").build();

// there are other builders too: skulls, leather armor, books, firework, potions, banners
ItemStack skull = ItemStackBuilder.skull().withSkullTexture("eyJ0ZXh0dX....").build();
ItemStack book = ItemStackBuilder.book(BookType.WRITTEN).withAuthor("Joris Guffens").withPage("Awesome!").build();

// and colored items: wool, terracotta, glass, beds, carpets, dye
ItemStack wool = ItemStackBuilder.wool(DyeColor.BLUE).withName("I'm blue dabe die dabe daa").build();

```

Creating your own menu with full control.
```java
// create a simple menu
IBukkitMenu menu = BukkitMasterGUI.create(45, "Fancy Title");

// add item
menu.setItem(back, (event) -> {
    event.getWhoClicked().sendMessage("clicked on back item!");
});

// custom listeners
menu.addClickListener(event -> {
    event.getWhoClicked().sendMessage("clicked anywhere!");
});

menu.addCloseListener(player -> {
    player.sendMessage("closed gui!");
});
```

Using the menu builder, this will generate a symmetrical gui. Limited to 10 items.
```java
IBukkitMenu menu = BukkitMasterGUI.builder()
        .withTitle("Fancy Title")
        .withItem(skull, (event) -> {
            event.getWhoClicked().sendMessage("clicked skull!");
        })
        .build();
```

Using a paginated gui. 
```java
List<Thing> items = ...
IBukkitMenu menu = BukkitMasterGUI.paginatedBuilder()
        .withTitle("Paginated inventory")
        .withBackItem(back)
        .withNextItem(next)
        .withItems(items.size(), (index) -> {
            // will only create/render an itemstack when this page is opened.
            return items.get(index).itemStack();
        })
        .build()
```
