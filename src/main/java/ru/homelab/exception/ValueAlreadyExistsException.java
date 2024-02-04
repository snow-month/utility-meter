package ru.homelab.exception;

public class ValueAlreadyExistsException extends Exception {
    private final String message;

    public ValueAlreadyExistsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
