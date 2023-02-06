package com.anotherspectrum.anotherlibrary.utils.chat;

import lombok.Getter;

import java.util.List;
import java.util.Locale;

/**
 * 채팅 이벤트가 저장된 열거형 클래스입니다.
 *
 * @since 0.2.8
 */
public enum EventType {

    HOVER(List.of(ActionType.SHOW_TEXT, ActionType.SHOW_ITEM, ActionType.SHOW_ENTITY)),
    CLICK(List.of(ActionType.CHANGE_PAGE, ActionType.COPY_TO_CLIPBOARD, ActionType.OPEN_FILE,
            ActionType.OPEN_URL, ActionType.RUN_COMMAND, ActionType.SUGGEST_COMMAND));

    private final @Getter List<ActionType> actionTypes;

    EventType(List<ActionType> actionType) {
        this.actionTypes = actionType;
    }

    @Override
    public String toString() {
        return name().toLowerCase(Locale.ROOT);
    }

}
