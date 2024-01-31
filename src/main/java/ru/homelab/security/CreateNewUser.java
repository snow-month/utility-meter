package ru.homelab.security;

import ru.homelab.model.MeterImpl;
import ru.homelab.model.Role;
import ru.homelab.model.Table;
import ru.homelab.model.User;

import java.util.ArrayList;

/**
 * Класс для создания нового пользователя.
 *
 * @author Petr "mypost@home.ru"
 * @version 1.0
 */
public class CreateNewUser {
    /**
     * Метод для создания нового пользователя.
     *
     * @param login    the login
     * @param password the password
     * @return the user
     */
    public User createNewUser(String login, String password) {
        User user = new User(login, Role.USER, password);

        Table.USERS.put(login, user);
        Table.HEATING.put(login, new MeterImpl());
        Table.WATER_COLD.put(login, new MeterImpl());
        Table.WATER_HOT.put(login, new MeterImpl());
        Table.LOGGER.put(login, new ArrayList<>());
        return user;
    }
}
