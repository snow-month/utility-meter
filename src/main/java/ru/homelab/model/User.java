package ru.homelab.model;

/**
 * The type User.
 */
public record User(String login, Role role, String password) {
}
