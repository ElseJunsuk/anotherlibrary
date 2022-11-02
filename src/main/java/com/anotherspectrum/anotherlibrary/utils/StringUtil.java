package com.anotherspectrum.anotherlibrary.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.title.Title;
import org.jetbrains.annotations.TestOnly;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * 문자열을 당신의 입맛대로 꾸밀 수 있습니다.
 *
 * @since 0.1.0
 */
public class StringUtil {

    /**
     * 문자열을 미니메시지(Minimessage)로 반환합니다.
     *
     * @param msg
     * @return {@link MiniMessage}
     */
    public static Component format(String msg) {
        return MiniMessage.miniMessage().deserialize(msg).decoration(TextDecoration.ITALIC, false);
    }

    /**
     * 타이틀을 미니메시지로 간편하게 제작할 수 있습니다.
     * @param title
     * @param subtitle
     * @param fadeIn
     * @param stay
     * @param fadeOut
     * @return {@link Title}
     */
    public static Title title(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        Title.Times times = Title.Times.times(Duration.ofSeconds(fadeIn / 200), Duration.ofSeconds(stay / 20), Duration.ofSeconds(fadeOut / 20));
        return Title.title(format(title), format(subtitle), times);
    }

    /**
     * {@link String} 클래스 속 replace 메소드는
     * 메모리를 많이 잡아먹습니다.
     * 이를 해결하기 위한 메소드입니다.
     * @param source - 리플레이스 할 메시지 (베이스)
     * @param os - 이전 문자열
     * @param ns - 바꿀 문자열
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
                buf.append (sourceArray, j, i - j).append(nsArray);
                i += oLength;
                j = i;
            }
            buf.append (sourceArray, j, sourceArray.length - j);
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
     * 현재 테스트중인 메소드입니다.
     * @param source
     * @param os
     * @param ns
     * @return
     */
    @TestOnly
    @Deprecated
    public static Component replace(TextComponent source, String os, String ns) {
        if (source == null) return null;
        int i = 0;
        if ((i = source.content().indexOf(os, i)) >= 0) {
            char[] sourceArray = source.content().toCharArray();
            char[] nsArray = ns.toCharArray();
            int oLength = os.length();
            StringBuilder buf = new StringBuilder(sourceArray.length);
            buf.append(sourceArray, 0, i).append(nsArray);
            i += oLength;
            int j = i;
            // oldString 을 newString 으로 대치합니다.
            while ((i = source.content().indexOf(os, i)) > 0) {
                buf.append (sourceArray, j, i - j).append(nsArray);
                i += oLength;
                j = i;
            }
            buf.append (sourceArray, j, sourceArray.length - j);
            source = Component.text(buf.toString());
            buf.setLength(0);
        }
        return source;
    }

    /**
     * 엘립시스 문자열을 읽고 리스트 Component 형태로 반환합니다.
     * 아이템에 lore 를 제작하거나, 무언가의 설명글을 추가할 떄 편리합니다.
     * @param messages - "", "", "", ...
     * @return {@link List<Component>}
     */
    public static List<Component> ellipsis(String... messages) {
        List<Component> list = new ArrayList<>();
        for (String comps : messages)
            list.add(format(comps));
        return list;
    }

}
