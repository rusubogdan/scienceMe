package com.scncm.helpers;

import org.springframework.util.StringUtils;

import java.util.Random;

public class AppUtil {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static String generateRandomString(String validChars, int length) {
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(validChars.charAt(random.nextInt(validChars.length())));
        }
        return result.toString();
    }

    public static boolean isValidEmail(String email) {
        return !StringUtils.isEmpty(email) && email.matches(EMAIL_PATTERN);
    }
}
