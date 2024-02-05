package ru.homelab.exception;

/**
 * The type Value already exists exception.
 */
public class ValueAlreadyExistsException extends Exception {
    private final String message;

    /**
     * Instantiates a new Value already exists exception.
     *
     * @param message the message
     */
    public ValueAlreadyExistsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
