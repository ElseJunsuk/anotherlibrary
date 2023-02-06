package com.anotherspectrum.anotherlibrary.menu.listener;

import com.anotherspectrum.anotherlibrary.menu.content.InventoryContent;
import com.anotherspectrum.anotherlibrary.menu.item.ClickableItem;
import com.anotherspectrum.anotherlibrary.menu.MenuManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.util.Objects;
import java.util.Optional;

/**
 * {@link MenuManager} 클래스의 인벤토리 제어 시스템을 최상위에서 관리하는 Listener 클래스입니다.
 *
 * @author Tainted_FrameWork and Tall edit for Else_JunSuk
 * @since 0.2.6
 */
public class InventoryListeners implements Listener {

    public InventoryListeners(JavaPlugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        if (Bukkit.getServer().getOnlinePlayers().size() > 0)
            Bukkit.getServer().getOnlinePlayers()
                    .stream()
                    .filter(t -> t.getOpenInventory().getType().equals(InventoryType.CHEST))
                    .forEach(HumanEntity::closeInventory);
    }

    @EventHandler
    public void inventoryDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();
        MenuManager menu = MenuManager.getMenu(player);
        if (menu != null) {
            event.setCancelled(true);
            menu.menuDragAction(player, event);
        }
    }

    @EventHandler
    public void inventoryOpen(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        MenuManager menuManager = MenuManager.getMenu(player);
        if (menuManager != null) {
            menuManager.menuOpenAction(player, event);
        }
    }

    @EventHandler
    public void inventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        MenuManager menuManager = MenuManager.getMenu(player);
        if (menuManager != null) {
            InventoryContent content = menuManager.getInventoryContent();
            if (content != null) {
                Inventory clickedInventory = event.getClickedInventory();
                if (clickedInventory == player.getOpenInventory().getBottomInventory()) {
                    if (event.getAction() == InventoryAction.COLLECT_TO_CURSOR || event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
                        event.setCancelled(true);
                        return;
                    }
                    if (event.getAction() == InventoryAction.NOTHING && event.getClick() != ClickType.MIDDLE) {
                        event.setCancelled(true);
                        return;
                    }
                }
                if (Objects.equals(event.getClickedInventory(), player.getOpenInventory().getTopInventory())) {
                    if (event.getClickedInventory() != null) {
                        Optional<ClickableItem> clickableItem = content.get(event.getRawSlot());
                        event.setCancelled(true);
                        clickableItem.ifPresent(item -> item.run(event));

                        menuManager.menuNormalClickAction(player, event);
                    }
                }
            }
        }
    }

    @EventHandler
    public void inventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        MenuManager menu = MenuManager.getMenu(player);
        if (menu != null) menu.menuCloseAction(player, event);
    }

}
