package com.anotherspectrum.anotherlibrary.menu;

import com.anotherspectrum.anotherlibrary.menu.action.MenuClickAction;
import com.anotherspectrum.anotherlibrary.menu.action.MenuCloseAction;
import com.anotherspectrum.anotherlibrary.menu.action.MenuDragAction;
import com.anotherspectrum.anotherlibrary.menu.action.MenuOpenAction;
import com.anotherspectrum.anotherlibrary.menu.content.InventoryContent;
import com.anotherspectrum.anotherlibrary.menu.item.ClickableItem;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * 해당 클래스를 사용하면 메뉴를 간편하게 제작할 수 있습니다.
 *
 * @author Else_JunSuk
 * @since 0.1.0 - UPDATE FOR 0.4.5
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

    private @Getter InventoryContent inventoryContent;

    /**
     * 커스텀 인벤토리를 제작합니다.
     *
     * @param player 인벤토리를 오픈할 타겟 플레이어
     * @param rows   설정할 인벤토리의 열
     * @param title  설정할 인벤토리의 {@link Component} 형식 타이틀
     */
    public MenuManager(Player player, int rows, Component title) {
        this.player = player;
        this.rows = rows;
        this.title = title;
        this.inventory = Bukkit.createInventory(null, rows * 9, title);
        this.itemContents = new ClickableItem[rows * 9];
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

        player.openInventory(inventory);
    }

}
