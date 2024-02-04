package ru.homelab.exception;

public class NoValueException extends Exception {
    private final String message;

    public NoValueException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
