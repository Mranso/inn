package com.inn.inn.util;

public class StringUtils {

    public static String EMPTY_STRING = "";

    public static boolean isNotBlank(String string) {
        return string != null && string.trim().length() > 0;
    }

    public static boolean isBlank(String string) {
        return !isNotBlank(string);
    }
}
