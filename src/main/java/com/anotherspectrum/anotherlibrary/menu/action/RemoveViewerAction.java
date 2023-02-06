package com.anotherspectrum.anotherlibrary.menu.action;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryOpenEvent;

public interface RemoveViewerAction {

    /**
     * 플레이어가 메뉴의 뷰어 목록에서 제외되었을 때 발생하는 이벤트를 구성합니다.
     *
     * @param player 뷰어에서 제외된 플레이어
     */
    default void removeViewerAction(Player player) { }

}
