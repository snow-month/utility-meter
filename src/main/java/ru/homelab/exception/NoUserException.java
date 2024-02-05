package ru.homelab.exception;

/**
 * The type No user exception.
 */
public class NoUserException extends Exception {
    private final String message;

    /**
     * Instantiates a new No user exception.
     *
     * @param message the message
     */
    public NoUserException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
