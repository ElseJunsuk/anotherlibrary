package com.anotherspectrum.anotherlibrary.menu.pagination;

import com.anotherspectrum.anotherlibrary.menu.MenuManager;
import com.anotherspectrum.anotherlibrary.menu.content.InventoryContent;
import com.anotherspectrum.anotherlibrary.menu.item.ClickableItem;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * 아이템 반복 규칙을 추가할 수 있습니다.
 * <p>MinusKube 님의 SmartInvs 라이브러리 코드를 참고했습니다.</p>
 *
 * @since 0.4.5
 */
public final class SlotIterator {

    private int pos;

    private boolean started = false;
    private boolean allowOverride = true;

    private final MenuManager menuManager;

    private final InventoryContent inventoryContent;

    private final Set<Integer> blacklisted;

    /**
     * 반복 규칙 인스턴스를 설정합니다.
     *
     * @param menuManager {@link MenuManager}
     * @param content     {@link InventoryContent}
     * @param startPos    시작 슬롯
     */
    public SlotIterator(MenuManager menuManager, InventoryContent content, int startPos) {
        this.menuManager = menuManager;
        this.inventoryContent = content;
        this.pos = startPos;
        this.blacklisted = new HashSet<>();
    }

    /**
     * 해당 슬롯을 블랙리스트 합니다.
     * <p>해당 슬롯엔 아이템이 추가되지 않습니다.</P.
     *
     * @param slot 블랙리스트 할 슬롯
     */
    public SlotIterator blacklist(int slot) {
        blacklisted.add(slot);
        return this;
    }

    /**
     * 해당 슬롯에 아이템이 있는지 반환합니다.
     *
     * @return {@link ClickableItem} 인자를 가진 {@link Optional}
     */
    public Optional<ClickableItem> get() {
        return inventoryContent.get(pos);
    }

    /**
     * 해당 슬롯에 아이템을 추가합니다.
     *
     * @param item {@link ClickableItem}
     * @return {@link SlotIterator}
     */
    public SlotIterator set(ClickableItem item) {
        if (canPlace())
            inventoryContent.set(pos, item);
        return this;
    }

    /**
     * 반복 중, 이전 슬롯을 설정합니다.
     *
     * @return {@link SlotIterator}
     */
    public SlotIterator previous() {
        if (pos == 0) {
            this.started = true;
            return this;
        }
        do {
            if (!this.started) {
                this.started = true;
            } else {
                pos--;
            }
        } while (!canPlace() && pos != 0);
        return this;
    }

    /**
     * 반복 중, 다음 슬롯을 설정합니다.
     *
     * @return {@link SlotIterator}
     */
    public SlotIterator next() {
        if (ended()) {
            this.started = true;
            return this;
        }
        do {
            if (!this.started) {
                this.started = true;
            } else {
                pos++;
            }
        } while (!canPlace() && !ended());

        return this;
    }

    /**
     * @return 반복 중, 아이템이 더 이상 추가될 수 없는가
     */
    public boolean ended() {
        final int size = menuManager.getRows() * 9;
        return pos >= size - 1;
    }

    /**
     * 해당 슬롯이 블랙리스트된 슬롯인지 확인합니다.
     *
     * @param slot 확인할 슬롯
     * @return 해당 슬롯이 블랙리스트 되어있는가
     */
    public boolean isBlacklistSlot(int slot) {
        return blacklisted.contains(slot);
    }

    /**
     * 반복을 시작했는지 확인합니다.
     *
     * @return 반복을 시작했는가
     */
    public boolean started() {
        return started;
    }

    /**
     * 반복 중, 슬롯을 복제했는지 확인합니다.
     *
     * @return 슬롯을 복제했는가
     */
    public boolean doseAllowOverride() {
        return allowOverride;
    }

    /**
     * 반복 중, 슬롯을 복제할건지 설정합니다.
     *
     * @param allowOverride {@link Boolean}
     * @return {@link SlotIterator}
     */
    public SlotIterator allowOverride(boolean allowOverride) {
        this.allowOverride = allowOverride;
        return this;
    }

    /**
     * 해당 슬롯에 아이템을 배치할 수 있는지 확인합니다.
     *
     * @return 해당 슬롯에 아이템을 배치할 수 있는가
     */
    private boolean canPlace() {
        return !blacklisted.contains(pos) && (allowOverride || !this.get().isPresent());
    }

}
