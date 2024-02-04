package ru.homelab.security;

import ru.homelab.controller.UserController;
import ru.homelab.entity.User;
import ru.homelab.exception.NoUserException;

/**
 * Класс для авторизации.
 *
 * @author Petr "mypost@home.ru"
 * @version 1.0
 */
public class Authorization {
    // todo getCurrentUser?
    public static final ThreadLocal<User> CURRENT_USER = new ThreadLocal<>();
    private final UserController userController;

    public Authorization(UserController userController) {
        this.userController = userController;
    }

    /**
     * Метод для авторизации пользователя.
     *
     * @return the boolean
     */
    public boolean authorization(String login, String password) {
        try {
            User userDb = userController.getUserByLogin(login);
            if (userDb.getPassword().equals(password)) {
                User user = new User(
                        userDb.getId(),
                        userDb.getLogin(),
                        null,
                        userDb.getRole()
                );
                CURRENT_USER.set(user);
                return true;
            }
        } catch (NoUserException e) {
            return false;
        }
        return false;
    }

    /**
     * Метод для logout.
     */
    public static void logout() {
        CURRENT_USER.remove();
    }
}
