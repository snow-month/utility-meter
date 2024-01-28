package ru.homelab.security;

import ru.homelab.in.InputConsole;
import ru.homelab.model.Table;
import ru.homelab.model.User;

/**
 * The type Authorization.
 */
public class Authorization {
    private User user;
    private final InputConsole inputConsole;

    /**
     * Instantiates a new Authorization.
     *
     * @param inputConsole the input console
     */
    public Authorization(InputConsole inputConsole) {
        this.inputConsole = inputConsole;
    }

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
    public boolean authorization() {
        System.out.println("Ваш логин");
        String login = inputConsole.readingStr();
        System.out.println("Введите пароль");
        String password = inputConsole.readingStr();

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
