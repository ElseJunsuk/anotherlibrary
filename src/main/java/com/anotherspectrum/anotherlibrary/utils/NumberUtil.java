package com.anotherspectrum.anotherlibrary.utils;

import java.util.Random;

/**
 * 숫자를 간편하게 반올림, 반내림하거나
 * 어려운 연산을 처리할 때 편리합니다.
 *
 * @since 0.1.0 - UPDATE FOR 0.2.7
 */
public final class NumberUtil {

    /**
     * 값을 소수점의 특정한 자릿수까지 반올림합니다.
     * 이 때 point 파라미터에는 일의자리가 반드시 '1' 이어야 하고
     * 십의자리 부터는 '0' 이어야 합니다. 0의 갯수만큼 소수를 반올림합니다.
     *
     * @param point 포인트
     * @param value 값
     * @return 포인트만큼 반올림된 소수
     */
    public static double roundDouble(int point, double value) {
        return Math.round(value * point) / (double) point;
    }

    /**
     * 값을 소숫점 둘째 자리까지 반올림하여 반환합니다.
     *
     * @param value 값
     * @return 둘때 자리까지 반올림된 소수
     */
    public static double round2PDouble(double value) {
        return Math.round(value * 100) / 100.0;
    }

    /**
     * @return 1부터 100 사이의 정수형 난수
     */
    public static int random1to100() {
        return (int) (Math.random() * 100);
    }

    /**
     * 더블 형태의 최솟값과 최댓값 사이의 난수를
     * 소숫점 둘째 자리까지 반올림하여 반환합니다.
     *
     * @param minValue 최솟값
     * @param maxValue 최댓값
     * @return 소숫점 둘째 자리까지 반올림된 최솟값과 최댓값 사이의 난수 소수
     */
    public static double randomDouble(double minValue, double maxValue) {
        return round2PDouble(((Math.random() * (maxValue - minValue)) + minValue));
    }

    /**
     * 더블 형태의 최솟값과 최댓값 사이의 난수를 point 자리까지 반올림하여 반환합니다.
     * 이 때 point 파라미터에는 일의자리가 반드시 '1' 이어야 하고
     * 십의자리 부터는 '0' 이어야 합니다. 0의 갯수만큼 소수를 반올림합니다.
     *
     * @param minValue 최솟값
     * @param maxValue 최댓값
     * @param point    포인트
     * @return 포인트만큼 반올림 된 최솟값과 최댓값 사이의 난수 소수
     */
    public static double randomDouble(double minValue, double maxValue, int point) {
        return roundDouble(point, (Math.random() * (maxValue - minValue)) + minValue);
    }

    /**
     * 최솟값과 최댓값 사이의 정수 형태의 난수를 생성합니다.
     *
     * @param minValue 최솟값
     * @param maxValue 최댓값
     * @return 최솟값과 최댓값 사이의 정수 형태의 난수
     */
    public static int randomInt(int minValue, int maxValue) {
        Random rand = new Random();
        return rand.nextInt((maxValue - minValue) + 1) + minValue;
    }

    /**
     * 전체값에서 특정 퍼센트는 얼마인지 반환합니다.
     *
     * @param total   전체값
     * @param percent 구할 Percentage
     * @return 계산된 퍼센테이지
     */
    public static double whatPercentageOfTheSpecificValue(double total, double percent) {
        return total * percent / 100.0D;
    }

    /**
     * 전체값에서 특정값은 몇 퍼센트인지 반환합니다.
     *
     * @param total 전체값
     * @param value 특정값
     * @return 계산된 퍼센테이지
     */
    public static double whatPercentageOfTheTotalValue(double total, double value) {
        return value / total * 100.0D;
    }

}
