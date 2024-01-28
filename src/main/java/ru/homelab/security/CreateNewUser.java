package ru.homelab.security;

import ru.homelab.in.InputConsole;
import ru.homelab.model.MeterImpl;
import ru.homelab.model.Role;
import ru.homelab.model.Table;
import ru.homelab.model.User;

import java.util.ArrayList;

public class CreateNewUser {
    private final InputConsole inputConsole;

    public CreateNewUser(InputConsole inputConsole) {
        this.inputConsole = inputConsole;
    }

    public User createNewUser() {
        System.out.println("Создание нового пользователя");
        System.out.println("Введите логин");
        String login = inputConsole.readingStr();

        String password;
        String confirmation;
        do {
            System.out.println("Введите пароль");
            password = inputConsole.readingStr();
            System.out.println("Потвердите пароль");
            confirmation = inputConsole.readingStr();
            if (!password.equals(confirmation)) {
                System.out.println("Пароли не совпадают");
            }
        } while (!password.equals(confirmation));

        User user = new User(login, Role.USER, password);
        Table.USERS.put(login, user);
        Table.HEATING.put(login, new MeterImpl());
        Table.WATER_COLD.put(login, new MeterImpl());
        Table.WATER_HOT.put(login, new MeterImpl());
        Table.LOGGER.put(login, new ArrayList<>());
        return user;
    }
}
