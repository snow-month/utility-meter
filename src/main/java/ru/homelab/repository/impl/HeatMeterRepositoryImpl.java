package ru.homelab.repository.impl;

import ru.homelab.entity.HeatMeter;
import ru.homelab.exception.NoValueException;
import ru.homelab.exception.ValueAlreadyExistsException;
import ru.homelab.repository.HeatMeterRepository;
import ru.homelab.utils.PropertiesApp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Heat meter repository.
 */
public class HeatMeterRepositoryImpl implements HeatMeterRepository {
    private final PropertiesApp property;

    /**
     * Instantiates a new Heat meter repository.
     *
     * @param property the property
     */
    public HeatMeterRepositoryImpl(PropertiesApp property) {
        this.property = property;
    }

    public Integer currentValue(long userId) throws NoValueException {
        Integer currentValue = null;
        try (Connection connection = DriverManager.getConnection(property.getUrl(),
                property.getUsername(), property.getPassword())) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT value FROM heat_liquibase WHERE user_id = ?" +
                            " order by year DESC, month DESC limit 1");
            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                currentValue = resultSet.getInt("value");
            } else {
                throw new NoValueException("no value");
            }
        } catch (SQLException e) {
            System.out.println("Sql exception: " + e.getMessage());
        }
        return currentValue;
    }

    @Override
    public Integer valueForMonth(int year, int month, long userId) throws NoValueException {
        Integer valueForMonth = null;
        try (Connection connection = DriverManager.getConnection(property.getUrl(),
                property.getUsername(), property.getPassword())) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT value FROM heat_liquibase WHERE " +
                            "year = ? and month = ? and user_id = ?");
            statement.setInt(1, year);
            statement.setInt(2, month);
            statement.setLong(3, userId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                valueForMonth = resultSet.getInt("value");
            } else {
                throw new NoValueException("no value");
            }
        } catch (SQLException e) {
            System.out.println("Sql exception: " + e.getMessage());
        }
        return valueForMonth;
    }

    @Override
    public boolean addValue(int value, int year, int month, long user_id) throws ValueAlreadyExistsException {
        try (Connection connection = DriverManager.getConnection(property.getUrl(),
                property.getUsername(), property.getPassword())) {
            try {
                valueForMonth(year, month, user_id);
                throw new ValueAlreadyExistsException("Value already exists");
            } catch (NoValueException e) {
            }

            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO heat_liquibase " +
                            "(value, year, month, user_id) VALUES (? ,? ,? , ?)");
            statement.setInt(1, value);
            statement.setInt(2, year);
            statement.setInt(3, month);
            statement.setLong(4, user_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Sql exception: " + e.getMessage());
        }
        return true;
    }

    @Override
    public List<HeatMeter> allValuesUser(Long userId) {
        List<HeatMeter> heatMeters = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(property.getUrl(),
                property.getUsername(), property.getPassword())) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM heat_liquibase WHERE user_id = ? " +
                            "order by year DESC, month DESC");
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                HeatMeter heatMeter = createHeatMeter(resultSet);
                heatMeters.add(heatMeter);
            }
        } catch (SQLException e) {
            System.out.println("Sql exception: " + e.getMessage());
        }
        return heatMeters;
    }

    @Override
    public List<HeatMeter> allValue() {
        List<HeatMeter> heatMeters = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(property.getUrl(),
                property.getUsername(), property.getPassword())) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM heat_liquibase;");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                HeatMeter heatMeter = createHeatMeter(resultSet);
                heatMeters.add(heatMeter);
            }
        } catch (SQLException e) {
            System.out.println("Sql exception: " + e.getMessage());
        }
        return heatMeters;
    }

    private HeatMeter createHeatMeter(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Integer value = resultSet.getInt("value");
        Integer year = resultSet.getInt("year");
        Integer month = resultSet.getInt("month");
        Long userId = resultSet.getLong("user_id");
        return new HeatMeter(id, value, year, month, userId);
    }
}
