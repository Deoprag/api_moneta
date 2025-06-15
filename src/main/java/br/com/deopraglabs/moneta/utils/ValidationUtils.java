package br.com.deopraglabs.moneta.utils;

import java.util.List;

public class ValidationUtils {
    public static void checkField(List<String> validations, boolean condition, String message) {
        if (condition) validations.add(message);
    }
}
