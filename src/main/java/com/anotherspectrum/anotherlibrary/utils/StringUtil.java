package com.anotherspectrum.anotherlibrary.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 문자열을 당신의 입맛대로 꾸밀 수 있습니다.
 *
 * @since 0.2.7 - UPDATE FOR 0.3.0
 */
public final class StringUtil {

    /**
     * 현재 시각을 해당 포멧에 맞춰 포맷할 수 있습니다.
     * <p>상세한 사용법은 아래와 같습니다.</p>
     * <pre>{@code StringUtil.DATE_FORMAT.format(Date); }</pre>
     */
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");

    /**
     * 문자열을 미니메시지(Minimessage)로 반환합니다.
     *
     * @param msg 시리얼라이징할 타겟 메시지
     * @return {@link MiniMessage}
     */
    public static Component format(String msg) {
        return format(msg, false);
    }

    public static Component format(String msg, boolean flag) {
        return MiniMessage.miniMessage().deserialize(msg).decoration(TextDecoration.ITALIC, flag);
    }

    /**
     * 미니메시지로 포맷된 메시지를 다시 문자열 형태로 반환합니다.
     * <p>특별 이벤트 적용 시 해당 메소드를 사용하여 어느 이벤트를</p>
     * <p>확인할 수 있습니다.</p>
     *
     * @param msg 디시리얼라이징 할 타겟 {@link Component}
     * @return {@link String}
     */
    public static String serialize(Component msg) {
        return MiniMessage.miniMessage().serialize(msg);
    }

    /**
     * 문자열 리스트를 컴포넌트로 변환합니다.
     *
     * @param msg 시리얼라이징할 타겟 문자열 인자를 가진 리스트
     * @return 컴포넌트 인자를 가진 리스트
     */
    public static List<Component> format(List<String> msg) {
        List<Component> list = new ArrayList<>();
        for (String keys : msg)
            list.add(format(keys));
        return list;
    }

    /**
     * 문자열 리스트에 포함된 os1 를
     * ns1 으로 대치합니다.
     *
     * @param stringList 문자열 인자를 가진 리플레이스 타겟 리스트
     * @param os1        바뀔 문자열
     * @param ns1        바꿀 문자열
     * @return 변경된 문자열 인자를 가진 리스트
     */
    public static List<String> replaceList(List<String> stringList, String os1, String ns1) {
        return stringList.stream()
                .filter(os1::equals)
                .map(s -> replace(s, os1, ns1))
                .collect(Collectors.toList());
    }

    /**
     * Component 인자를 가진 List 에서 os1 을 찾아 ns1 로 대치합니다.
     *
     * @param componentList {@link Component} 인자를 가진 리플레이스 타겟 리스트
     * @param os1           바뀔 문자열
     * @param ns1           바꿀 문자열
     * @return 변경된 {@link Component} 인자를 가진 리스트
     */
    public static List<Component> replaceComponentList(List<Component> componentList, String os1, String ns1) {
        return componentList.stream()
                .map(c -> format(replace(((TextComponent) c).content(), os1, ns1)))
                .collect(Collectors.toList());
    }

    /**
     * 타이틀을 미니메시지로 간편하게 제작할 수 있습니다.
     *
     * @param title    타이틀 (상단 라인)
     * @param subtitle 서브 타이틀 (하단 라인)
     * @param fadeIn   페이드 인 틱
     * @param stay     스테이 틱
     * @param fadeOut  페이드 아웃 틱
     * @return {@link Title}
     */
    public static Title title(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        Title.Times times = Title.Times.times(Duration.ofSeconds(fadeIn / 20), Duration.ofSeconds(stay / 20), Duration.ofSeconds(fadeOut / 20));
        return Title.title(format(title), format(subtitle), times);
    }

    /**
     * {@link String} 클래스 속 replace 메소드는
     * 메모리를 많이 잡아먹습니다.
     * 이를 해결하기 위한 메소드입니다.
     *
     * @param source 리플레이스 할 메시지 (베이스)
     * @param os     이전 문자열
     * @param ns     바꿀 문자열
     * @return {@link String}
     */
    public static String replace(String source, String os, String ns) {
        if (source == null) return null;
        int i = 0;
        if ((i = source.indexOf(os, i)) >= 0) {
            char[] sourceArray = source.toCharArray();
            char[] nsArray = ns.toCharArray();
            int oLength = os.length();
            StringBuilder buf = new StringBuilder(sourceArray.length);
            buf.append(sourceArray, 0, i).append(nsArray);
            i += oLength;
            int j = i;
            // oldString 을 newString 으로 대치합니다.
            while ((i = source.indexOf(os, i)) > 0) {
                buf.append(sourceArray, j, i - j).append(nsArray);
                i += oLength;
                j = i;
            }
            buf.append(sourceArray, j, sourceArray.length - j);
            source = buf.toString();
            buf.setLength(0);
        }
        return source;
    }

    public static String replace(String source, String os1, String ns1, String os2, String ns2) {
        return replace(replace(source, os1, ns1), os2, ns2);
    }

    public static String replace(String source, String os1, String ns1, String os2, String ns2, String os3, String ns3) {
        return replace(replace(replace(source, os1, ns1), os2, ns2), os3, ns3);
    }

    public static String replace(String source, String os1, String ns1, String os2, String ns2, String os3, String ns3, String os4, String ns4) {
        return replace(replace(replace(replace(source, os1, ns1), os2, ns2), os3, ns3), os4, ns4);
    }

    public static String replace(String source, String os1, String ns1, String os2, String ns2, String os3, String ns3, String os4, String ns4, String os5, String ns5) {
        return replace(replace(replace(replace(replace(source, os1, ns1), os2, ns2), os3, ns3), os4, ns4), os5, ns5);
    }

    public static String replace(String source, String os1, String ns1, String os2, String ns2, String os3, String ns3, String os4, String ns4, String os5, String ns5, String os6, String ns6) {
        return replace(replace(replace(replace(replace(replace(source, os1, ns1), os2, ns2), os3, ns3), os4, ns4), os5, ns5), os6, ns6);
    }

    public static String replace(String source, String os1, String ns1, String os2, String ns2, String os3, String ns3, String os4, String ns4, String os5, String ns5, String os6, String ns6, String ns7, String os7) {
        return replace(replace(replace(replace(replace(replace(replace(source, os1, ns1), os2, ns2), os3, ns3), os4, ns4), os5, ns5), os6, ns6), os7, ns7);
    }

    /**
     * 엘립시스 문자열을 읽고 리스트 Component 형태로 반환합니다.
     * 아이템에 lore 를 제작하거나, 무언가의 설명글을 추가할 떄 편리합니다.
     *
     * @param messages 문자 배열 ("", "", "", ...)
     * @return {@link List<Component>}
     */
    public static List<Component> ellipsis(String... messages) {
        List<Component> list = new ArrayList<>();
        for (String comps : messages)
            list.add(format(comps));
        return list;
    }

    /**
     * {@link Component} 를 문자열로 변환합니다.
     *
     * @param message {@link Component}
     * @return 문자열로 변형된 {@link Component}
     */
    public static String switching(Component message) {
        return ((TextComponent) message).content().toString();
    }

    /**
     * 문자열을 {@link Component} 로 변환합니다.
     *
     * @param message 문자열
     * @return {@link Component} 로 변형된 문자열
     */
    public static Component switching(String message) {
        return format(message);
    }

}
