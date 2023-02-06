package com.anotherspectrum.anotherlibrary.utils.chat;

import lombok.Getter;

import java.util.Locale;

/**
 * 채팅 이벤트의 액션이 저장된 열거형 클래스입니다.
 *
 * @since 0.2.8
 */
public enum ActionType {

    SHOW_TEXT,
    SHOW_ITEM,
    SHOW_ENTITY,

    CHANGE_PAGE,
    COPY_TO_CLIPBOARD,
    OPEN_FILE,
    OPEN_URL,
    RUN_COMMAND,
    SUGGEST_COMMAND;

    @Override
    public String toString() {
        return name().toLowerCase(Locale.ROOT);
    }

}
