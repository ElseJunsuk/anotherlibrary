package com.anotherspectrum.anotherlibrary.menu.action;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public interface MenuCloseAction {

    /**
     * 인벤토리를 닫을 때 발생하는 이벤트를 구성합니다.
     *
     * @param event {@link InventoryCloseEvent}
     */
    default void menuCloseAction(Player player, InventoryCloseEvent event) {}

}
