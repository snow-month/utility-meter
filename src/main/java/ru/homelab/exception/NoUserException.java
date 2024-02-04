package ru.homelab.exception;

public class NoUserException extends Exception {
    private final String message;

    public NoUserException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
