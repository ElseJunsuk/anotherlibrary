package com.anotherspectrum.anotherlibrary.menu.action;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * 메뉴의 특정 행동으로 인하여 발생한 이벤트에 대해 정의하는 클래스입니다.
 *
 * @param <A> Menu Actions
 * @since 0.5.0-SNAPSHOT
 */
public interface ActionHandler<A> {

    ActionHandler<MenuClickAction> MENU_CLICK = new DefaultActionHandlerImplement<>(MenuClickAction.class);
    ActionHandler<MenuCloseAction> MENU_CLOSE = new DefaultActionHandlerImplement<>(MenuCloseAction.class);
    ActionHandler<MenuOpenAction> MENU_OPEN = new DefaultActionHandlerImplement<>(MenuOpenAction.class);
    ActionHandler<MenuDragAction> MENU_DRAG = new DefaultActionHandlerImplement<>(MenuDragAction.class);
    ActionHandler<BottomMenuClickAction> BOTTOM_MENU_CLICK = new DefaultActionHandlerImplement<>(BottomMenuClickAction.class);
    ActionHandler<RemoveViewerAction> REMOVE_VIEWER = new DefaultActionHandlerImplement<>(RemoveViewerAction.class);

    @NotNull
    Class<A> actionType();

    class DefaultActionHandlerImplement<A> implements ActionHandler<A> {

        private @NotNull
        @Getter
        final Class<A> actionClass;

        DefaultActionHandlerImplement(@NotNull Class<A> actionClass) {
            this.actionClass = actionClass;
        }

        @Override
        public @NotNull Class<A> actionType() {
            return actionClass;
        }
    }

}
