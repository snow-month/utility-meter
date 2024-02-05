package ru.homelab.service.impl;

import ru.homelab.controller.UserController;
import ru.homelab.entity.MessageAudit;
import ru.homelab.entity.User;
import ru.homelab.exception.NoUserException;
import ru.homelab.service.AuditService;
// todo service?

/**
 * Класс для авторизации.
 *
 * @author Petr "mypost@home.ru"
 * @version 1.0
 */
public class AuthorizationService {
    /**
     * The constant CURRENT_USER.
     */
// todo getCurrentUser?
    public static final ThreadLocal<User> CURRENT_USER = new ThreadLocal<>();
    private final UserController userController;
    private final AuditService auditService;

    /**
     * Instantiates a new Authorization service.
     *
     * @param userController the user controller
     * @param auditService   the audit service
     */
    public AuthorizationService(UserController userController, AuditService auditService) {
        this.userController = userController;
        this.auditService = auditService;
    }

    /**
     * Метод для авторизации пользователя.
     *
     * @param login    the login
     * @param password the password
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
                auditService.save(MessageAudit.AUTHORIZATION);
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
    public void logout() {
        auditService.save(MessageAudit.LOGOUT);
        CURRENT_USER.remove();
    }
}
