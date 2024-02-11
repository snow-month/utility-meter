package ru.homelab.entity;

import java.util.Arrays;
import java.util.Optional;

/**
 * Enum для ролей безопасности.
 *
 * @author Petr "mypost@home.ru"
 * @version 1.0
 */
public enum Role {
    /**
     * User role.
     */
    USER,
    /**
     * Admin role.
     */
    ADMIN;

    public static Optional<Role> find(String role) {
        return Arrays.stream(values())
                .filter(it -> it.name().equals(role))
                .findFirst();
    }
}
