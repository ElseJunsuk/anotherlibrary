package com.anotherspectrum.anotherlibrary.menu.action;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

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
