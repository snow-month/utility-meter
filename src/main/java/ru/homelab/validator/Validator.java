package ru.homelab.validator;

public interface Validator<T> {
    ValidationResult isValid(T object);
}
