package com.anotherspectrum.anotherlibrary.menu.item;

import lombok.Getter;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public final class ClickableItem {

    private @Getter final ItemStack item;
    private @Getter final Consumer<InventoryClickEvent> consumer;

    private ClickableItem(ItemStack item, Consumer<InventoryClickEvent> consumer) {
        this.item = item;
        this.consumer = consumer;
    }

    public static ClickableItem empty(ItemStack item) {
        return of(item, e -> {
        });
    }

    public static ClickableItem of(ItemStack item, Consumer<InventoryClickEvent> consumer) {
        return new ClickableItem(item, consumer);
    }

    public void run(InventoryClickEvent e) {
        consumer.accept(e);
    }
}
