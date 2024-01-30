package ru.homelab.security;

import ru.homelab.model.Table;
import ru.homelab.model.User;

/**
 * The type Authorization.
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
     * Authorization boolean.
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
     * Logout.
     */
    public void logout() {
        user = null;
    }
}
