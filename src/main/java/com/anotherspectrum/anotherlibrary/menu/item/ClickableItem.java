package com.anotherspectrum.anotherlibrary.menu.item;

import lombok.Getter;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

/**
 * 클릭할 수 있는 아이템을 인벤토리에 추가할 수 있습니다.
 * <p>MinusKube 님의 SmartInvs 라이브러리 코드를 참고했습니다.</p>
 *
 * @since 0.4.5
 */
public final class ClickableItem {

    private @Getter
    final ItemStack item;
    private @Getter
    final Consumer<InventoryClickEvent> consumer;

    /**
     * {@link ItemStack} 아이템에 클릭 이벤트를 주입할 수 있습니다.
     *
     * @param item     타겟 아이템 {@link ItemStack}
     * @param consumer {@link InventoryClickEvent} 인자를 포함한 {@link Consumer}
     */
    private ClickableItem(ItemStack item, Consumer<InventoryClickEvent> consumer) {
        this.item = item;
        this.consumer = consumer;
    }

    /**
     * 클릭 시 아무런 이벤트가 발동되지 않는 아이템을 반환합니다.
     *
     * @param item {@link ItemStack}
     * @return {@link ClickableItem}
     */
    public static ClickableItem empty(ItemStack item) {
        return of(item, e -> {
        });
    }

    /**
     * 클릭 시 이벤트가 발동되는 아이템을 반환합니다.
     *
     * @param item     {@link ItemStack}
     * @param consumer {@link InventoryClickEvent} 인자를 포함한 {@link Consumer}
     * @return {@link ClickableItem}
     */
    public static ClickableItem of(ItemStack item, Consumer<InventoryClickEvent> consumer) {
        return new ClickableItem(item, consumer);
    }

    /**
     * {@link ClickableItem} 아이템 클릭 시 이벤트를 발동합니다.
     *
     * @param event {@link InventoryClickEvent}
     */
    public void run(InventoryClickEvent event) {
        consumer.accept(event);
    }
}
