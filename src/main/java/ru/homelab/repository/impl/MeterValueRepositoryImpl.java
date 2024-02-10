package ru.homelab.repository.impl;

import ru.homelab.entity.MeterValue;
import ru.homelab.exception.NoValueException;
import ru.homelab.exception.ValueAlreadyExistsException;
import ru.homelab.repository.MeterValueRepository;
import ru.homelab.service.DBConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MeterValueRepositoryImpl implements MeterValueRepository {
    private final DBConnectionProvider dbConnectionProvider;

    public MeterValueRepositoryImpl(DBConnectionProvider dbConnectionProvider) {
        this.dbConnectionProvider = dbConnectionProvider;
    }

    public Integer currentValue(long userId) throws NoValueException {
        Integer currentValue = null;
        try (Connection connection = dbConnectionProvider.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT value FROM meter_value_liquibase WHERE user_id = ?" +
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
        try (Connection connection = dbConnectionProvider.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT value FROM meter_value_liquibase WHERE " +
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
    public boolean addValue(int value, int year, int month, long meterTypeId, long userId) throws ValueAlreadyExistsException {
        try (Connection connection = dbConnectionProvider.getConnection()) {
            try {
                valueForMonth(year, month, userId);
                throw new ValueAlreadyExistsException("Value already exists");
            } catch (NoValueException e) {
            }

            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO meter_value_liquibase " +
                            "(value, year, month, meter_type_id, user_id) VALUES (?,?,?,?,?);");
            statement.setInt(1, value);
            statement.setInt(2, year);
            statement.setInt(3, month);
            statement.setLong(4, meterTypeId);
            statement.setLong(5, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Sql exception: " + e.getMessage());
        }
        return true;
    }

    @Override
    public List<MeterValue> allValuesUser(Long userId) {
        List<MeterValue> meterValues = new ArrayList<>();

        try (Connection connection = dbConnectionProvider.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM meter_value_liquibase WHERE user_id = ? " +
                            "order by year DESC, month DESC");
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                MeterValue meterValue = createMeterValue(resultSet);
                meterValues.add(meterValue);
            }
        } catch (SQLException e) {
            System.out.println("Sql exception: " + e.getMessage());
        }
        return meterValues;
    }

    @Override
    public List<MeterValue> allValue() {
        List<MeterValue> meterValues = new ArrayList<>();

        try (Connection connection = dbConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM meter_value_liquibase;");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                MeterValue meterValue = createMeterValue(resultSet);
                meterValues.add(meterValue);
            }
        } catch (SQLException e) {
            System.out.println("Sql exception: " + e.getMessage());
        }
        return meterValues;
    }

    private MeterValue createMeterValue(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Integer value = resultSet.getInt("value");
        Integer year = resultSet.getInt("year");
        Integer month = resultSet.getInt("month");
        Long meterTypeId = resultSet.getLong("meter_type_id");
        Long userId = resultSet.getLong("user_id");
        return new MeterValue(id, value, year, month, meterTypeId, userId);
    }
}
