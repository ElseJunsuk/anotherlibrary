package com.anotherspectrum.anotherlibrary.menu;

import com.anotherspectrum.anotherlibrary.menu.action.*;
import com.anotherspectrum.anotherlibrary.menu.content.InventoryContent;
import com.anotherspectrum.anotherlibrary.menu.item.ClickableItem;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * 해당 클래스를 사용하면 메뉴를 간편하게 제작할 수 있습니다.
 *
 * @author Else_JunSuk
 * @since 0.1.0 - UPDATE FOR 0.4.6
 */
public abstract class MenuManager implements MenuClickAction, MenuCloseAction, MenuDragAction, MenuOpenAction {

    private @Getter
    final int rows;
    private @Getter
    final Component title;

    private @Getter
    final Player player;

    private @Getter
    final Inventory inventory;

    private @Getter ClickableItem[] itemContents;

    private static final Map<Player, MenuManager> menuOpen = new HashMap<>();
    private static final Map<String, Set<UUID>> viewers = new HashMap<>();

    private final String viewerID;
    private @Getter
    final UUID uuid;

    private @Getter InventoryContent inventoryContent;

    /**
     * 커스텀 인벤토리를 제작합니다.
     *
     * @param player 인벤토리를 오픈할 타겟 플레이어
     * @param rows   설정할 인벤토리의 열
     * @param title  설정할 인벤토리의 {@link Component} 형식 타이틀
     */
    public MenuManager(Player player, int rows, Component title) {
        this(player, rows, title, null);
    }

    /**
     * 커스텀 인벤토리를 제작합니다.
     *
     * @param player   인벤토리를 오픈할 타겟 플레이어
     * @param rows     설정할 인벤토리의 열
     * @param title    설정할 인벤토리의 {@link Component} 형식 타이틀
     * @param viewerID 뷰어 ID
     */
    public MenuManager(Player player, int rows, Component title, String viewerID) {
        this.player = player;
        this.rows = rows;
        this.title = title;
        this.viewerID = viewerID;
        this.uuid = UUID.randomUUID();
        this.inventory = Bukkit.createInventory(null, rows * 9, title);
        this.itemContents = new ClickableItem[rows * 9];
    }

    /**
     * 해당 플레이어를 메뉴의 뷰어 목록에 추가합니다.
     *
     * @param player 타겟 플레이어
     */
    public void addViewer(Player player) {
        if (viewerID == null) return;
        Set<UUID> list = viewers.getOrDefault(viewerID, new HashSet<>());
        list.add(player.getUniqueId());
        viewers.put(viewerID, list);
    }

    /**
     * 해당 플레이어를 메뉴의 뷰어 목록에서 제외합니다.
     *
     * @param player 타겟 플레이어(뷰어)
     */
    public void removeViewer(Player player) {
        if (viewerID == null) return;
        Set<UUID> list = viewers.getOrDefault(viewerID, new HashSet<>());
        if (list == null) return;
        list.remove(player.getUniqueId());
        if (list.isEmpty()) viewers.remove(viewerID);
        else viewers.put(viewerID, list);
    }

    /**
     * 해당 인벤토리를 보고있는 모든 플레이어(뷰어)를 반환합니다.
     *
     * @return {@link Player} 인자를 포함한 {@link Set}
     */
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

    /**
     * 해당 플레이어의 메뉴를 가져옵니다.
     *
     * @param player 타겟 플레이어
     * @return {@link MenuManager}
     */
    public static MenuManager getMenu(Player player) {
        return menuOpen.getOrDefault(player, null);
    }

    /**
     * 인벤토리 내부 컨텐츠를 구성합니다.
     *
     * @param player  인벤토리를 보고있는 플레이어
     * @param content {@link InventoryContent} 로 구성된 인벤토리 컨텐츠
     */
    abstract public void contents(Player player, InventoryContent content);

    /**
     * 설정된 인벤토리를 오픈합니다.
     */
    public void open() {
        this.open(0);
    }

    /**
     * 설정된 인벤토리의 특정 페이지를 오픈합니다.
     */
    public void open(int page) {
        if (inventory == null)
            throw new NullPointerException("[Sententia] inventory 필드가 null 값을 가지기 때문에 인벤토리를 열 수 없습니다.");

        this.inventoryContent = new InventoryContent.Impl(this, rows, player);
        inventoryContent.pagination().page(page);
        contents(player, inventoryContent);

        menuOpen.put(player, this);

        if (viewerID != null) addViewer(player);

        player.openInventory(inventory);
    }

    /**
     * 플레이어를 해당 메뉴의 뷰어 목록에서 제외하며, 인벤토리를 닫습니다.
     */
    public void close() {
        this.close(player);
    }

    /**
     * 특정 플레이어를 해당 메뉴의 뷰어 목록에서 제외하며, 인벤토리를 닫습니다.
     *
     * @param viewer 타겟 플레이어
     */
    public void close(Player viewer) {
        player.closeInventory();
        menuOpen.entrySet().removeIf(entry -> {
            if (entry.getKey().equals(viewer)) {
                if (viewerID != null) removeViewer(viewer);
                return true;
            }
            return false;
        });
    }

    /**
     * 플레이어를 해당 메뉴의 뷰어 목록에서 제외합니다.
     */
    public void remove() {
        this.remove(null);
    }

    /**
     * 플레이어를 해당 메뉴의 뷰어 목록에서 제외함과 동시에
     * <p>{@link RemoveViewerAction} 인터페이스에 오버라이딩 된 메소드를 호출합니다.</p>
     *
     * @param removeViewerAction 실행할 인터페이스
     */
    public void remove(@Nullable RemoveViewerAction removeViewerAction) {
        menuOpen.entrySet().removeIf(entry -> {
            if (entry.getValue().getUuid().equals(uuid)) {
                Player player = entry.getKey();
                if (player != null) {
                    if (viewerID != null) removeViewer(player);
                    if (removeViewerAction != null) removeViewerAction.removeViewerAction(player);
                }
                return true;
            }
            return false;
        });
    }

}
