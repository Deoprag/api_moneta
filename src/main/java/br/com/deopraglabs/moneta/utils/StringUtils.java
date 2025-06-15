package br.com.deopraglabs.moneta.utils;

public class StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.isBlank();
    }

    public static String removeSpecialCharacters(String str) {
        return str.replaceAll("[^a-zA-Z0-9]", "");
    }

}
