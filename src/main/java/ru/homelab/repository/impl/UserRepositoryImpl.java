package ru.homelab.repository.impl;

import ru.homelab.entity.Role;
import ru.homelab.entity.User;
import ru.homelab.exception.NoUserException;
import ru.homelab.repository.UserRepository;
import ru.homelab.service.DBConnectionProvider;

import java.sql.*;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final DBConnectionProvider dbConnectionProvider;

    public UserRepositoryImpl(DBConnectionProvider dbConnectionProvider) {
        this.dbConnectionProvider = dbConnectionProvider;
    }

    @Override
    public User getUserByLogin(String login) throws NoUserException {
        User user = null;
        try (Connection connection = dbConnectionProvider.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM user_liquibase WHERE login = ?");
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        Role.valueOf(resultSet.getString("role")));
            } else {
                throw new NoUserException("нет пользователя с логином - " + login);
            }
        } catch (SQLException e) {
            System.out.println("sql exception, getConnection: " + e.getMessage());
        }
        return user;
    }

    @Override
    public User save(User user) throws SQLException {
        try (Connection connection = dbConnectionProvider.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO user_liquibase (login, password, role)" +
                            " VALUES (? ,? ,?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().name());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            Long id = Long.valueOf(generatedKeys.getString("id"));
            user.setId(id);

            return user;
        }
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) throws SQLException {
        try (Connection connection = dbConnectionProvider.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(
                            "SELECT * FROM user_liquibase WHERE login = ? AND password = ?");
            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User(resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        Role.valueOf(resultSet.getString("role")));
            }
            return Optional.ofNullable(user);
        }
    }
}
