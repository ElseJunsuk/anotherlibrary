package com.anotherspectrum.anotherlibrary.menu.action;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface BottomMenuClickAction {

    /**
     * 바텀 인벤토리 속 아이템을 클릭했을 때 발생하는 이벤트를 구성합니다.
     *
     * @param clicker 바텀 인벤토리의 아이템을 클릭한 플레이어
     * @param event   {@link InventoryClickEvent}
     */
    default void bottomMenuClickAction(Player clicker, InventoryClickEvent event) {
    }

}
