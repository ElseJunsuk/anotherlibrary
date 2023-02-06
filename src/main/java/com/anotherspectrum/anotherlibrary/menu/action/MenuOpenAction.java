package com.anotherspectrum.anotherlibrary.menu.action;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public interface MenuOpenAction {

    /**
     * 인벤토리를 오픈할 때 발생하는 이벤트를 구성합니다.
     *
     * @param event {@link InventoryOpenEvent}
     */
    default void menuOpenAction(Player player, InventoryOpenEvent event) {}

}
