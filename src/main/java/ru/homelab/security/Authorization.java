package ru.homelab.security;

import ru.homelab.in.InputConsole;
import ru.homelab.model.Table;
import ru.homelab.model.User;

public class Authorization {
    private User user;

    public User currentUser() {
        return user;
    }

    public boolean authorization() {
        System.out.println("Ваш логин");
        String login = InputConsole.readingStr();
        System.out.println("Введите пароль");
        String password = InputConsole.readingStr();

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

    public void logout() {
        user = null;
    }
}
