package com.ntukhpi.otp.momot.fourth_course_dma.lab7.util;

public class CarNumberGenerator {
    private static final int CAR_NUMBER_LENGTH = 6;

    private CarNumberGenerator() {}

    public static String generateCarNumber() {
        StringBuilder carNumber = new StringBuilder(CAR_NUMBER_LENGTH);
        for (int i = 0; i < CAR_NUMBER_LENGTH; i++) {
            carNumber.append(Math.round( Math.random()) == 1 ? generateDigit() : generateLetters());
        }

        return carNumber.toString();
    }

    private static char generateDigit() {
        int n = '9' - '0' + 1;
        return (char) ('0' + Math.random() * n);
    }

    private static char generateLetters() {
        int n = 'Z' - 'A' + 1;
        return (char) ('A' + Math.random() * n);
    }
}
