package com.anotherspectrum.anotherlibrary.menu.pagination;

import com.anotherspectrum.anotherlibrary.menu.item.ClickableItem;

import java.util.Arrays;

/**
 * 인벤토리에 Pagination 을 추가할 수 있습니다.
 * <p>MinusKube 님의 SmartInvs 라이브러리 코드를 참고했습니다.</p>
 *
 * @since 0.4.5
 */
public interface Pagination {

    /**
     * @return 한 페이지의 추가될 모든 {@link ClickableItem}[] 타입 아이템
     */
    ClickableItem[] getPageItems();

    /**
     * @return 현재 플레이어가 보고있는 페이지
     */
    int getPage();

    /**
     * 페이지를 설정합니다.
     *
     * @param page 설정할 페이지
     * @return {@link Pagination}
     */
    Pagination page(int page);

    /**
     * 다음 페이지로 설정합니다.
     *
     * @return {@link Pagination}
     */
    Pagination next();

    /**
     * 이전 페이지로 설정합니다.
     *
     * @return {@link Pagination}
     */
    Pagination previous();

    /**
     * 첫번째 페이지로 설정합니다.
     *
     * @return {@link Pagination}
     */
    Pagination first();

    /**
     * 마지막 페이지로 설정합니다.
     *
     * @return {@link Pagination}
     */
    Pagination last();

    /**
     * @return 해당 페이지가 처음 페이지인가
     */
    boolean isFirst();

    /**
     * @return 해당 페이지가 마지막 페이지인가
     */
    boolean isLast();

    /**
     * {@link Pagination} 에 반복 규칙을 추가합니다.
     *
     * @param iterator {@link SlotIterator}
     * @return {@link Pagination}
     */
    Pagination addIterator(SlotIterator iterator);

    /**
     * 해당 아이템을 페이지 아이템으로 설정합니다.
     *
     * @param items Ellipses {@link ClickableItem}...
     * @return {@link Pagination}
     */
    Pagination setItems(ClickableItem... items);

    /**
     * 한 페이지에 최대 몇 개의 아이템이 들어가는지 설정합니다.
     *
     * @param itemsPerPage 설정할 아이템 추가 횟수
     * @return {@link Pagination}
     */
    Pagination setItemsPerPage(int itemsPerPage);

    class Impl implements Pagination {

        private int currentPage;
        private int itemsPerPage = 5;
        private ClickableItem[] items = new ClickableItem[0];

        @Override
        public ClickableItem[] getPageItems() {
            return Arrays.copyOfRange(items,
                    currentPage * itemsPerPage,
                    (currentPage + 1) * itemsPerPage);
        }

        @Override
        public int getPage() {
            return this.currentPage;
        }

        @Override
        public Pagination page(int page) {
            this.currentPage = page;
            return this;
        }

        @Override
        public Pagination next() {
            if (!isLast())
                this.currentPage++;
            return this;
        }

        @Override
        public Pagination previous() {
            if (!isFirst())
                this.currentPage--;
            return this;
        }

        @Override
        public Pagination first() {
            this.currentPage = 0;
            return this;
        }

        @Override
        public Pagination last() {
            this.currentPage = (int) Math.ceil((double) this.items.length / this.itemsPerPage) - 1;
            return this;
        }

        @Override
        public boolean isFirst() {
            return this.currentPage == 0;
        }

        @Override
        public boolean isLast() {
            int pageCount = (int) Math.ceil((double) this.items.length / this.itemsPerPage);
            return this.currentPage >= pageCount - 1;
        }

        @Override
        public Pagination addIterator(SlotIterator iterator) {
            for (ClickableItem item : getPageItems()) {
                iterator.next().set(item);

                if (iterator.ended())
                    break;
            }
            return this;
        }

        @Override
        public Pagination setItems(ClickableItem... items) {
            this.items = items;
            return this;
        }

        @Override
        public Pagination setItemsPerPage(int itemsPerPage) {
            this.itemsPerPage = itemsPerPage;
            return this;
        }
    }

}
