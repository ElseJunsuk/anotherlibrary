package com.anotherspectrum.anotherlibrary.menu.action;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public interface MenuDragAction {

    /**
     * 인벤토리에서 아이템을 드래그할 때 발생하는 이벤트를 구성합니다.
     *
     * @param clicker 인벤토리 속 아이템을 클릭한 플레이어
     * @param event   {@link InventoryDragEvent}
     */
    default void menuDragAction(Player clicker, InventoryDragEvent event) {}

}
