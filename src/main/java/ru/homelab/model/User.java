package ru.homelab.model;

/**
 * Класс пользователя.
 *
 * @author Petr "mypost@home.ru"
 * @version 1.0
 */
public record User(String login, Role role, String password) {
}
