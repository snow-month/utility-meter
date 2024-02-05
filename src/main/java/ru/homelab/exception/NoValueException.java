package ru.homelab.exception;

/**
 * The type No value exception.
 */
public class NoValueException extends Exception {
    private final String message;

    /**
     * Instantiates a new No value exception.
     *
     * @param message the message
     */
    public NoValueException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
