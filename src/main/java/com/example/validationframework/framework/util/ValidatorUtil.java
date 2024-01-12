package com.example.validationframework.framework.util;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class ValidatorUtil {
    public static boolean checkRegex(String value, String pattern) {
        Pattern regex = Pattern.compile(pattern);
        return regex.matcher(value).matches();
    }

    public static boolean isDateOfBirth(LocalDate dob) {
        if (dob == null) {
            return false;
        }

        return dob.compareTo(LocalDate.now()) <= 0;
    }
}
