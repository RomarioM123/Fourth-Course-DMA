package com.ntukhpi.otp.momot.fourth_course_dma.lab7.util;

public class YearGenerator {
    private static final int MIN_YEAR = 1950;
    private static final int MAX_YEAR = 2023;

    private YearGenerator() {}

    public static int getRandomYear() {
        return (int) (MIN_YEAR + Math.round(Math.random() * (MAX_YEAR - MIN_YEAR)));
    }
}
