package ru.homelab.model;

public record User(String login, Role role, String password) {
}
