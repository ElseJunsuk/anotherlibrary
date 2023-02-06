package com.anotherspectrum.anotherlibrary.utils;

import com.anotherspectrum.anotherlibrary.utils.chat.ActionType;
import com.anotherspectrum.anotherlibrary.utils.chat.EventType;

/**
 * 채팅에 이벤트를 부여하거나 수정할 수 있는 도구가 제공됩니다.
 *
 * @since 0.2.8 - UPDATE FOR 0.3.31-SNAPSHOT
 */
public final class ChatUtil {

    /**
     * 타겟 문자열(source)에 해당 이벤트를 부여합니다.
     *
     * @param source     타겟 문자열
     * @param eventType  {@link EventType}
     * @param actionType {@link ActionType}
     * @param value      액션 타입에 따른 실행값
     * @return 이벤트가 부여된 문자열
     */
    public static String applyEvent(String source, EventType eventType, ActionType actionType, String value) {
        if (source == null || source.isEmpty() || source.isBlank())
            throw new NullPointerException("[AnotherLibrary] 이벤트가 부여되는 Source 문자열은 null 일 수 없습니다.");
        if (value == null || value.isEmpty() || value.isBlank())
            throw new NullPointerException("[AnotherLibrary] 이벤트에 포함되는 문자열 값인 value 는 null 일 수 없습니다.");
        if (eventType == null)
            throw new NullPointerException("[AnotherLibrary] 이벤트 타입은 null 일 수 없습니다.");
        if (actionType == null)
            throw new NullPointerException("[AnotherLibrary] 이벤트에 따른 Action 타입은 null 일 수 없습니다.");
        if (!eventType.getActionTypes().contains(actionType))
            throw new NullPointerException("[AnotherLibrary] '" + actionType + "' 액션 타입은 '" + eventType + "' 이벤트에 포함된 액션 타입이 아닙니다.");
        StringBuilder result = new StringBuilder();
        // <event:action:value>message</event>
        if (eventType.equals(EventType.CLICK))
            result.append("<").append(eventType.toString()).append(":").append(actionType.toString()).append(":").append(value).append(">").append(source).append("</").append(eventType.toString()).append(">");
        if (eventType.equals(EventType.HOVER))
            result.append("<").append(eventType.toString()).append(":").append(actionType.toString()).append(":'").append(value).append("'>").append(source).append("</").append(eventType.toString()).append(">");
        return result.toString();
    }

}
