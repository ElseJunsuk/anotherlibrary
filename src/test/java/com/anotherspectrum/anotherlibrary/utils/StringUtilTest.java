package com.anotherspectrum.anotherlibrary.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.TestOnly;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@TestOnly
class StringUtilTest {

    @Test
    void componentReplaceTest() {
        TextComponent component = Component.text("Else Junsuk is very AwESoMe!!");
        System.out.println(" - " + StringUtil.replace(component, "AwESoMe", "CuTe"));

        System.out.println(" - " + StringUtil.format("<red>Test"));
    }

    @Test
    void randomIntegerTest() {
        System.out.println("1 부터 100 중 자연 난수: " + NumberUtil.random1to100() + "\n" +
                "특정 소숫점 반올림 체크: " + NumberUtil.roundDouble(10, 3.1415923124) + "\n" +
                "정수 난수 테스트: " + NumberUtil.randomInt(1, 3) + "\n" +
                "실수 난수 테스트: " + NumberUtil.randomDouble(1.5, 2.0) + "\n" +
                "실수 난수 테스트: " + NumberUtil.randomDouble(1.5, 2.0, 10000));
    }


}