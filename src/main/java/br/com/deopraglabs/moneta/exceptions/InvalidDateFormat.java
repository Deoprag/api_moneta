package br.com.deopraglabs.moneta.exceptions;

public class InvalidDateFormat extends RuntimeException {
    public InvalidDateFormat(String message, String expectedFormat) {
        super(message + " (expected format: " + expectedFormat + ")");
    }

    public InvalidDateFormat(String message) {
        super(message);
    }
}
