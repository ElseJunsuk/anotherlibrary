package com.anotherspectrum.anotherlibrary.utils;

import java.util.Random;

/**
 * 숫자를 간편하게 반올림, 반내림하거나
 * 어려운 연산을 처리할 때 편리합니다.
 *
 * @since 0.1.0
 * @update 0.2.4/03-11-22
 */
public class NumberUtil {

    /**
     * 값을 소수점의 특정한 자릿수까지 반올림합니다.
     * @example 100 으로 설정 시 소수점의 둘째 자리까지 반올림합니다.
     * @param point
     * @param value
     * @return
     */
    public static double roundDouble(int point, double value) {
        return Math.round(value * point) / (double) point;
    }

    /**
     * 값을 소숫점 둘째 자리까지 반올림하여 반환합니다.
     * @param value
     * @return double
     */
    public static double round2PDouble(double value) {
        return Math.round(value * 100) / 100.0;
    }

    /**
     * 1부터 100까지의 정수형 난수를 반환합니다.
     * @return int
     */
    public static int random1to100() {
        return (int) (Math.random() * 100);
    }

    /**
     * 더블 형태의 최솟값과 최댓값 사이의 난수를
     * 소숫점 둘째 자리까지 반올림하여 반환합니다.
     * @param minValue
     * @param maxValue
     * @return double
     */
    public static double randomDouble(double minValue, double maxValue) {
        return round2PDouble(((Math.random() * (maxValue - minValue)) + minValue));
    }

    public static double randomDouble(double minValue, double maxValue, int point) {
        return roundDouble(point, (Math.random() * (maxValue - minValue)) + minValue);
    }

    /**
     * minValue부터 maxValue사이의 정수 형태의 난수를 생성합니다.
     * @param minValue
     * @param maxValue
     * @return int
     */
    public static int randomInt(int minValue, int maxValue) {
        Random rand = new Random();
        return rand.nextInt((maxValue - minValue) + 1) + minValue;
    }

}
