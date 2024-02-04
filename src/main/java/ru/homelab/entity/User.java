package ru.homelab.entity;

// todo отдельная схема

/**
 * Класс пользователя.
 *
 * @author Petr "mypost@home.ru"
 * @version 1.0
 */
public class User {
    private Long id;
    private String login;
    private String password;
    private String role;
    // todo
//    private List<Role> roles;

    public User() {
    }

    public User(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(Long id, String login, String password, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
