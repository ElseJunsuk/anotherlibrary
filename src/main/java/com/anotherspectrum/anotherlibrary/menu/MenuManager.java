package com.anotherspectrum.anotherlibrary.menu;

import com.anotherspectrum.anotherlibrary.utils.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * 해당 클래스를 상속받으면 메뉴를 간편하게 제작할 수 있습니다.
 *
 * @since 0.1.0
 */
public class MenuManager {

    private static final Map<UUID, MenuManager> openMenus = new HashMap<>();
    private static final Map<String, Set<UUID>> viewers = new HashMap<>();

    private final Map<Integer, MenuClick> menuClickActions = new HashMap<>();

    private MenuClick generalClickAction;
    private MenuClick generalInvClickAction;
    private MenuDrag generalDragAction;

    private MenuOpen openAction;
    private MenuClose closeAction;

    public final UUID uuid;
    private final Inventory inventory;
    private final String viewerID;

    private boolean cancelled;

    private int rows;

    public MenuManager(int rows, String name, boolean cancelled) {
        this.rows = rows;
        this.uuid = UUID.randomUUID();
        this.inventory = Bukkit.createInventory(null, rows * 9, StringUtil.format(name));
        this.viewerID = null;
        this.cancelled = cancelled;
    }

    public MenuManager(int rows, String name, String viewerID, boolean cancelled) {
        this.rows = rows;
        this.uuid = UUID.randomUUID();
        this.inventory = Bukkit.createInventory(null, rows * 9, StringUtil.format(name));
        this.viewerID = viewerID;
        this.cancelled = cancelled;
    }

    public static MenuManager getMenu(Player player) {
        return openMenus.getOrDefault(player.getUniqueId(), null);
    }

    public void open(Player player) {
        player.openInventory(inventory);
        openMenus.put(player.getUniqueId(), this);
        if (viewerID != null) addViewer(player);
        if (openAction != null) openAction.open(player);
    }

    public void remove() {
        openMenus.entrySet().removeIf(entry -> {
            if (entry.getValue().getUuid().equals(uuid)) {
                Player player = Bukkit.getPlayer(entry.getKey());
                if (player != null) {
                    if (viewerID != null) removeViewer(player);
                    if (closeAction != null) closeAction.close(player);
                }
                return true;
            }
            return false;
        });
    }

    public UUID getUuid() {
        return uuid;
    }

    public void close(Player player) {
        player.closeInventory();
        openMenus.entrySet().removeIf(entry -> {
            if (entry.getKey().equals(player.getUniqueId())) {
                if (viewerID != null) removeViewer(player);
                if (closeAction != null) closeAction.close(player);
                return true;
            }
            return false;
        });
    }

    private void addViewer(Player player) {
        if (viewerID == null) return;
        Set<UUID> list = viewers.getOrDefault(viewerID, new HashSet<>());
        list.add(player.getUniqueId());
        viewers.put(viewerID, list);
    }

    private void removeViewer(Player player) {
        if (viewerID == null) return;
        Set<UUID> list = viewers.getOrDefault(viewerID, null);
        if (list == null) return;
        list.remove(player.getUniqueId());
        if (list.isEmpty()) viewers.remove(viewerID);
        else viewers.put(viewerID, list);
    }

    public Set<Player> getViewers() {
        if (viewerID == null) return new HashSet<>();
        Set<Player> viewerList = new HashSet<>();
        for (UUID uuid : viewers.getOrDefault(viewerID, new HashSet<>())) {
            Player player = Bukkit.getPlayer(uuid);
            if (player == null) continue;
            viewerList.add(player);
        }
        return viewerList;
    }

    public MenuClick getAction(int index) {
        return menuClickActions.getOrDefault(index, null);
    }

    public MenuClick getGeneralClickAction() {
        return generalClickAction;
    }

    protected void setGeneralClickAction(MenuClick generalClickAction) {
        this.generalClickAction = generalClickAction;
    }

    public MenuClick getGeneralInvClickAction() {
        return generalInvClickAction;
    }

    protected void setGeneralInvClickAction(MenuClick generalInvClickAction) {
        this.generalInvClickAction = generalInvClickAction;
    }

    public MenuDrag getGeneralDragAction() {
        return generalDragAction;
    }

    protected void setGeneralDragAction(MenuDrag generalDragAction) {
        this.generalDragAction = generalDragAction;
    }

    protected void setOpenAction(MenuOpen openAction) {
        this.openAction = openAction;
    }

    protected void setCloseAction(MenuClose closeAction) {
        this.closeAction = closeAction;
    }

    public interface MenuClick {
        void click(Player player, InventoryClickEvent event);
    }

    public interface MenuDrag {
        void drag(Player player, InventoryDragEvent event);
    }

    public interface MenuOpen {
        void open(Player player);
    }

    public interface MenuClose {
        void close(Player player);
    }

    public void setItem(int index, ItemStack item) {
        inventory.setItem(index, item);
    }

    public void setItem(int index, ItemStack item, MenuClick action) {
        inventory.setItem(index, item);
        if (action == null) menuClickActions.remove(index);
        else menuClickActions.put(index, action);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * 인벤토리의 rows 를 계산하고
     * 테두리(보더)를 해당 아이템으로 채웁니다.
     * @param fillItem
     */
    public void fillBorders(ItemStack fillItem) {
        this.fillRows(1, fillItem); this.fillRows(rows, fillItem);
        this.fillColumn(1, fillItem); this.fillColumn(9, fillItem);
    }

    /**
     * 인벤토리 특정한 rows 를 해당 아이템으로 채웁니다.
     * @help Gy_o, qawaea, kyominna
     * @param row
     * @param fillItem
     */
    public void fillRows(int row, ItemStack fillItem) {
        if ((row < rows) || row < 1) return;
        for (int i = (row - 1) * 9; i <= ((row * 9) - 1); i++)
            inventory.setItem(i, fillItem);
    }

    /**
     * 인벤토리의 특정한 columns 를 해당 아이템으로 채웁니다.
     * @help Gy_o, qawaea, kyominna
     * @param column
     * @param fillItem
     */
    public void fillColumn(int column, ItemStack fillItem) {
        if (column < 1) return;
        for (int i = (column - 1); i < (column - 1) + (rows * 9); i+=9)
            getInventory().setItem(i, fillItem);
    }

}
