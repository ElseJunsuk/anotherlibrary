package com.anotherspectrum.anotherlibrary.menu.pagination;

import com.anotherspectrum.anotherlibrary.menu.MenuManager;
import com.anotherspectrum.anotherlibrary.menu.content.InventoryContent;
import com.anotherspectrum.anotherlibrary.menu.item.ClickableItem;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class SlotIterator {

    private int pos;

    private boolean started = false;
    private boolean allowOverride = true;

    private final MenuManager menuManager;

    private final InventoryContent inventoryContent;

    private final Set<Integer> blacklisted;

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

    public Optional<ClickableItem> get() {
        return inventoryContent.get(pos);
    }

    public SlotIterator set(ClickableItem item) {
        if (canPlace())
            inventoryContent.set(pos, item);
        return this;
    }

    public SlotIterator previous() {
        if (pos == 0) {
            this.started = true;
            return this;
        }
        do {
            if (!this.started) {
                this.started = true;
            } else {
                pos --;
            }
        } while (!canPlace() && pos != 0);
        return this;
    }

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

    public boolean ended() {
        final int size = menuManager.getRows() * 9;
        return pos >= size - 1;
    }

    public boolean isBlacklistSlot(int slot) {
        return blacklisted.contains(slot);
    }

    public boolean started() {
        return started;
    }

    public boolean doseAllowOverride() {
        return allowOverride;
    }

    public SlotIterator allowOverride(boolean allowOverride) {
        this.allowOverride = allowOverride;
        return this;
    }

    private boolean canPlace() {
        return !blacklisted.contains(pos) && (allowOverride || !this.get().isPresent());
    }

}
