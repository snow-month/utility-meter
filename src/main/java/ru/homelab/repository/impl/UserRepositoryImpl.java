package ru.homelab.repository.impl;

import ru.homelab.entity.User;
import ru.homelab.exception.NoUserException;
import ru.homelab.repository.UserRepository;
import ru.homelab.utils.PropertiesApp;

import java.sql.*;

/**
 * The type User repository.
 */
public class UserRepositoryImpl implements UserRepository {
    private final PropertiesApp property;

    /**
     * Instantiates a new User repository.
     *
     * @param property the property
     */
    public UserRepositoryImpl(PropertiesApp property) {
        this.property = property;
    }

    @Override
    public User getUserByLogin(String login) throws NoUserException {
        User user = null;
        try (Connection connection = DriverManager.getConnection(property.getUrl(),
                property.getUsername(), property.getPassword())) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM user_liquibase WHERE login = ?");
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
            } else {
                throw new NoUserException("нет пользователя с логином - " + login);
            }
        } catch (SQLException e) {
            System.out.println("Sql exception: " + e.getMessage());
        }
        return user;
    }

    @Override
    public void save(User user) {
        try (Connection connection = DriverManager.getConnection(property.getUrl(),
                property.getUsername(), property.getPassword())) {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO user_liquibase (login, password, role)" +
                            " VALUES (? ,? ,?)");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Sql exception: " + e.getMessage());
        }
    }
}
