package ru.homelab.repository.impl;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import ru.homelab.entity.Role;
import ru.homelab.entity.User;
import ru.homelab.exception.NoUserException;
import ru.homelab.repository.CreateContainerAndRunMigration;
import ru.homelab.repository.UserRepository;
import ru.homelab.service.DBConnectionProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserRepositoryImplTest extends CreateContainerAndRunMigration {
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        DBConnectionProvider dbConnectionProvider = new DBConnectionProvider() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(url, username, password);
            }
        };
        this.userRepository = new UserRepositoryImpl(dbConnectionProvider);
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    void getUserByLogin() throws NoUserException {
        User user = userRepository.getUserByLogin("user");
        assertEquals("user", user.getLogin());
    }

    @Test
    void getUserByLogin_ThrowsNoUserException() {
        String login = "thwwkwww";
        Throwable exception = assertThrows(NoUserException.class,
                () -> userRepository.getUserByLogin(login));
        assertEquals("нет пользователя с логином - " + login, exception.getMessage());
    }

    @Test
    void save() throws NoUserException {
        User newuser = new User("newuser", "1123", Role.USER.toString());
        userRepository.save(newuser);
        User user = userRepository.getUserByLogin(newuser.getLogin());
        assertEquals(newuser.getLogin(), user.getLogin());
    }
}