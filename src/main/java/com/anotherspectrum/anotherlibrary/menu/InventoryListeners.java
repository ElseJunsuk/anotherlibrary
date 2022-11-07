package com.anotherspectrum.anotherlibrary.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @since 0.2.6-SNAPSHOT
 */
public class InventoryListeners implements Listener {

    public InventoryListeners(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void inventoryDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        MenuManager menu = MenuManager.getMenu(player);
        if (menu != null) {
            event.setCancelled(true);
            if (menu.getGeneralDragAction() != null) menu.getGeneralDragAction().drag(player, event);
        }
    }

    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        MenuManager menu = MenuManager.getMenu(player);
        if (menu != null) {

            if (menu.isCancelled())
                event.setCancelled(true);
            else event.setCancelled(false);

            if (event.getClickedInventory() != null) {
                // 메뉴의 인벤토리를 클릭했을 때.
                if (event.getRawSlot() > event.getClickedInventory().getSize()) {
                    if (menu.getGeneralInvClickAction() != null) menu.getGeneralInvClickAction().click(player, event);
                } else if (menu.getGeneralClickAction() != null) { // 메뉴를 클릭했을 떄.
                    menu.getGeneralClickAction().click(player, event);
                }
            }

            MenuManager.MenuClick menuClick = menu.getAction(event.getRawSlot());
            if (menuClick != null) menuClick.click(player, event);
        }
    }

    @EventHandler
    public void inventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        MenuManager menu = MenuManager.getMenu(player);
        if (menu != null) menu.remove();
    }

}
