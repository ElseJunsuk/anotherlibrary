package com.anotherspectrum.anotherlibrary.utils.chat;

import lombok.Getter;

import java.util.Locale;

/**
 * 채팅 이벤트의 액션이 저장된 열거형 클래스입니다.
 *
 * @since 0.2.8
 */
public enum ActionType {

    /**
     * 마우스를 올리면 메시지가 보입니다.
     */
    SHOW_TEXT,
    /**
     * 마우스를 올리면 아이템이 보입니다.
     */
    SHOW_ITEM,
    /**
     * 마우스를 올리면 엔티티({@link org.bukkit.entity.EntityType})가 보입니다.
     */
    SHOW_ENTITY,

    /**
     * 클릭 시 책에서 다음 페이지로 이동합니다.
     */
    CHANGE_PAGE,
    /**
     * 클릭 시 특정 메시지, URL 을 복사합니다.
     */
    COPY_TO_CLIPBOARD,
    /**
     * 클릭 시 특정 파일을 오픈합니다.
     */
    OPEN_FILE,
    /**
     * 클릭 시 링크된 URL 을 오픈합니다.
     */
    OPEN_URL,
    /**
     * 클릭 시 특정 {@link org.bukkit.command.Command} 를 실행합니다.
     */
    RUN_COMMAND,
    /**
     * 클릭 시 플레이어의 채팅창에 커맨드나 메시지를 자동완성합니다.
     */
    SUGGEST_COMMAND;

    /**
     * 해당 클래스({@link ActionType})의 열거체의 이름을 소문자로 반환합니다.
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return name().toLowerCase(Locale.ROOT);
    }

}
