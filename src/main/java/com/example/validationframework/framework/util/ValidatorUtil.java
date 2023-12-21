package com.example.validationframework.framework.util;

import java.util.regex.Pattern;

public class ValidatorUtil {
    public static boolean checkRegex(String value, String pattern) {
        Pattern regex = Pattern.compile(pattern);
        return regex.matcher(value).matches();
    }

}
