package com.anotherspectrum.anotherlibrary.utils;

import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class ChatUtilTest {

    @Test
    void test() {
        System.out.println(TenTwo.NUMBER == (Ten.THREE.getTest()));
    }

    enum Ten {
        ONE(TenTwo.TEST),
        TWO(TenTwo.TEST),
        THREE(TenTwo.NUMBER);

        private @Getter TenTwo test;

        Ten(TenTwo test) {
            this.test = test;
        }
    }

    enum TenTwo {
        NUMBER,
        TEST
    }
}