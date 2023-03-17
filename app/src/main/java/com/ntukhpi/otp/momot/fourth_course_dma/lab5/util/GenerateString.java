package com.ntukhpi.otp.momot.fourth_course_dma.lab5.util;

import java.util.Random;

public class GenerateString {
    private static final char min = 'a';
    private static final char max = 'z';

    private GenerateString() {}

    public static String getText(int targetStringLength) {
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = min + (int) (random.nextFloat() * (max - min + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
