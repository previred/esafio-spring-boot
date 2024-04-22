package com.company.util;

public class StringUtils {

    public static final String empty = "";

    public static boolean isEmpty(String value) {
        return !isNotEmpty(value);
    }


    public static boolean isNotEmpty(String value) {
        return value!=null && !value.equals("");
    }


}
