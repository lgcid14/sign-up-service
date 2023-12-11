package com.mycompany.signupservice.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PassValidator {

    private static final String CUSTOM_REGEX = "^(?=.*[A-Z])(?=.*\\d.*\\d)[a-zA-Z\\d]*$";
    private static final Pattern CUSTOM_PATTERN = Pattern.compile(CUSTOM_REGEX);
    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 12;

    public static boolean isValidPass(String input) {
        Matcher matcher = CUSTOM_PATTERN.matcher(input);
        long digitCount = input.chars().filter(Character::isDigit).count();
        long uppercaseCount = input.chars().filter(Character::isUpperCase).count();
        return input.length() >= MIN_LENGTH && input.length() <= MAX_LENGTH && matcher.matches()
                && digitCount == 2 && uppercaseCount == 1;
    }
}
