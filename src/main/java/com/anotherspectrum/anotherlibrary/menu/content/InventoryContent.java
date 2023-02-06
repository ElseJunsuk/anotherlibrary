package com.anotherspectrum.anotherlibrary.menu.content;

import com.anotherspectrum.anotherlibrary.menu.MenuManager;
import com.anotherspectrum.anotherlibrary.menu.item.ClickableItem;
import com.anotherspectrum.anotherlibrary.menu.pagination.Pagination;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Optional;

/**
 * 인벤토리에 상세한 컨텐츠를 추가합니다.
 *
 * @since 0.4.5
 */
public interface InventoryContent {

    /**
     * 인벤토리의 페이지를 설정할 수 있습니다.
     *
     * @return {@link Pagination}
     */
    Pagination pagination();

    /**
     * 해당 슬롯에 아이템이 있으면 가져옵니다.
     *
     * @param slot 가져올 인벤토리 슬롯
     * @return {@link ClickableItem} 클래스가 인자로 포함된 {@link Optional}
     */
    Optional<ClickableItem> get(int slot);

    /**
     * 컨텐츠에 추가된 모든 아이템을 반환합니다.
     *
     * @return {@link ClickableItem}[]
     */
    ClickableItem[] all();

    /**
     * 인벤토리에 해당 아이템을 추가합니다.
     *
     * @param item 추가할 아이템
     */
    InventoryContent add(ClickableItem item);

    /**
     * 해당 슬롯에 아이템을 놓습니다.
     *
     * @param slot 아이템이 추가될 슬롯
     * @param item 추가할 {@link ClickableItem} 아이템
     */
    InventoryContent set(int slot, ClickableItem item);

    /**
     * 인벤토리의 rows 를 계산하고
     * 테두리(보더)를 해당 아이템으로 채웁니다.
     * <p>Help by Gy_o, qawaea, kyominna</p>
     *
     * @param item 채울 아이템
     */
    InventoryContent fillBorders(ClickableItem item);

    /**
     * 인벤토리 특정한 rows 를 해당 아이템으로 채웁니다.
     * <p>Help by Gy_o, qawaea, kyominna</p>
     *
     * @param row  채울 행
     * @param item 채울 아이템
     */
    InventoryContent fillRows(int row, ClickableItem item);

    /**
     * 인벤토리의 특정한 columns 를 해당 아이템으로 채웁니다.
     * <p>Help by Gy_o, qawaea, kyominna</p>
     *
     * @param column 채울 열
     * @param item   채울 아이템
     */
    InventoryContent fillColumns(int column, ClickableItem item);

    /**
     * 인벤토리의 특정 열과 행을 채웁니다.
     * <p>Help by Gy_o, qawaea, kyominna</p>
     *
     * @param row    채울 행
     * @param column 채울 열
     * @param item   채울 아이템
     */
    InventoryContent fill(int row, int column, ClickableItem item);

    class Impl implements InventoryContent {

        private final int rows;

        private Inventory inventory;
        private Player player;
        private ClickableItem[] contents;

        private Pagination pagination = new Pagination.Impl();

        public Impl(MenuManager menuManager, int rows, Player player) {
            this.inventory = menuManager.getInventory();
            this.player = player;
            this.rows = rows;
            this.contents = new ClickableItem[rows * 9];
        }

        @Override
        public Pagination pagination() {
            return pagination;
        }

        @Override
        public Optional<ClickableItem> get(int slot) {
            if (slot < 0)
                return Optional.empty();
            if (slot >= rows * 9)
                return Optional.empty();
            return Optional.ofNullable(contents[slot]);
        }

        @Override
        public ClickableItem[] all() {
            return contents;
        }

        @Override
        public InventoryContent add(ClickableItem item) {
            for (int slot = 0; slot < contents.length; slot++) {
                if (contents[slot] == null) {
                    set(slot, item);
                    return this;
                }
            }
            return this;
        }

        @Override
        public InventoryContent set(int slot, ClickableItem item) {
            if (slot < 0)
                return this;
            if (slot >= rows * 9)
                return this;

            contents[slot] = item;
            update(slot, item != null ? item.getItem() : null);
            return this;
        }

        @Override
        public InventoryContent fill(int row, int column, ClickableItem fillItem) {
            this.fillRows(row, fillItem);
            this.fillColumns(column, fillItem);
            return this;
        }

        @Override
        public InventoryContent fillBorders(ClickableItem fillItem) {
            this.fillRows(1, fillItem);
            this.fillRows(rows, fillItem);
            this.fillColumns(1, fillItem);
            this.fillColumns(9, fillItem);
            return this;
        }

        @Override
        public InventoryContent fillRows(int row, ClickableItem fillItem) {
            if ((row > rows) || row < 1) return this;
            for (int i = (row - 1) * 9; i <= ((row * 9) - 1); i++)
                set(i, fillItem);
            return this;
        }

        @Override
        public InventoryContent fillColumns(int column, ClickableItem fillItem) {
            if (column < 1) return this;
            for (int i = (column - 1); i < (column - 1) + (rows * 9); i += 9)
                set(i, fillItem);
            return this;
        }

        /**
         * 인벤토리의 특정 슬롯을 업데이트합니다.
         *
         * @param slot 업데이트 할 슬롯
         * @param item 업데이트 비포 {@link ItemStack}
         */
        private void update(int slot, ItemStack item) {
            inventory.setItem(slot, item);
        }
    }

}
