package com.anotherspectrum.anotherlibrary.utils;

/**
 * 숫자를 간편하게 반올림, 반내림하거나
 * 어려운 연산을 처리할 때 편리합니다.
 *
 * @since 0.1.0
 */
public class NumberUtil {

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

}
