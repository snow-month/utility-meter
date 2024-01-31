package ru.homelab.security;

import ru.homelab.model.Table;
import ru.homelab.model.User;

/**
 * Класс для авторизации.
 *
 * @author Petr "mypost@home.ru"
 * @version 1.0
 */
public class Authorization {
    private User user;

    /**
     * Current user user.
     *
     * @return the user
     */
    public User currentUser() {
        return user;
    }

    /**
     * Метод для авторизации пользователя.
     *
     * @return the boolean
     */
    public boolean authorization(String login, String password) {
        User userTable;
        if (Table.USERS.containsKey(login)) {
            userTable = Table.USERS.get(login);
        } else {
            return false;
        }

        if (userTable.password().equals(password)) {
            user = userTable;
            return true;
        }
        return false;
    }

    /**
     * Метод для logout.
     */
    public void logout() {
        user = null;
    }
}
