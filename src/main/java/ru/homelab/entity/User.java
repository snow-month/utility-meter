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
    private final String login;
    private final String password;
    private final String role;
    // todo
//    private List<Role> roles;

    /**
     * Instantiates a new User.
     *
     * @param login    the login
     * @param password the password
     * @param role     the role
     */
    public User(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    /**
     * Instantiates a new User.
     *
     * @param id       the id
     * @param login    the login
     * @param password the password
     * @param role     the role
     */
    public User(Long id, String login, String password, String role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }
}
