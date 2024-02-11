package ru.homelab.exception;

import ru.homelab.validator.Error;

import java.util.List;

public class ValidationException extends Exception {
    private final List<Error> errors;

    public ValidationException(List<Error> errors) {
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }
}
