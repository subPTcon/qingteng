package com.qingteng.common.utils;

import java.util.Random;

public class VerificationCodeUtil {

    private static final Random RANDOM = new Random();

    public static String generateCode() {
        return String.format("%06d", RANDOM.nextInt(1000000));
    }
}
