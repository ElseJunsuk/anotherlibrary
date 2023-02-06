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

    /**
     * 메시지에 마우스를 올려 Hover Component 를 확인합니다.
     */
    HOVER(List.of(ActionType.SHOW_TEXT, ActionType.SHOW_ITEM, ActionType.SHOW_ENTITY)),

    /**
     * 메시지를 클릭해 Click Action 을 실행합니다.
     */
    CLICK(List.of(ActionType.CHANGE_PAGE, ActionType.COPY_TO_CLIPBOARD, ActionType.OPEN_FILE,
            ActionType.OPEN_URL, ActionType.RUN_COMMAND, ActionType.SUGGEST_COMMAND));

    private final @Getter List<ActionType> actionTypes;

    /**
     * 채팅 컴포넌트({@link net.kyori.adventure.text.Component})에 메시지 이벤트를 추가합니다.
     *
     * @param actionType 각 이벤트에 맞는 {@link ActionType}
     */
    EventType(List<ActionType> actionType) {
        this.actionTypes = actionType;
    }

    /**
     * 해당 클래스({@link EventType})의 열거체의 이름을 소문자로 반환합니다.
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return name().toLowerCase(Locale.ROOT);
    }

}
