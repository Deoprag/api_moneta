package br.com.deopraglabs.moneta.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class BRValidationException extends RuntimeException {
    private final List<String> errors;
}
