package ru.homelab.repository.impl;

import ru.homelab.entity.MeterTypeName;
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
    private DBConnectionProvider dbConnectionProvider;

    public MeterValueRepositoryImpl() {
    }

    public MeterValueRepositoryImpl(DBConnectionProvider dbConnectionProvider) {
        this.dbConnectionProvider = dbConnectionProvider;
    }

    @Override
    public Integer currentValue(long userId, MeterTypeName meterTypeName) throws NoValueException {
        Integer currentValue = null;
        try (Connection connection = dbConnectionProvider.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(
                            "SELECT value\n" +
                                    "FROM meter_value_liquibase as mvl\n" +
                                    "         LEFT JOIN meter_type_liquibase AS mtl\n" +
                                    "                   ON mvl.meter_type_id = mtl.id\n" +
                                    "WHERE mvl.user_id = ?\n" +
                                    "  AND mtl.type = ?\n" +
                                    "ORDER BY year DESC, month DESC\n" +
                                    "LIMIT 1;"
                    );
            statement.setLong(1, userId);
            statement.setString(2, meterTypeName.name());

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
    public Integer valueForMonth(int year, int month, MeterTypeName meterTypeName,
                                 long userId) throws NoValueException {
        Integer valueForMonth = null;
        try (Connection connection = dbConnectionProvider.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(
                            "SELECT value\n" +
                                    "FROM meter_value_liquibase as mvl\n" +
                                    "         LEFT JOIN meter_type_liquibase AS mtl\n" +
                                    "                   ON mvl.meter_type_id = mtl.id\n" +
                                    "WHERE mvl.user_id = ?\n" +
                                    "  AND mtl.type = ?\n" +
                                    "  AND mvl.year = ?\n" +
                                    "  AND mvl.month = ?;");
            statement.setLong(1, userId);
            statement.setString(2, meterTypeName.name());
            statement.setInt(3, year);
            statement.setInt(4, month);

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
    public boolean addValue(int value, int year, int month, MeterTypeName meterTypeName, long userId) throws ValueAlreadyExistsException {
        try (Connection connection = dbConnectionProvider.getConnection()) {
            try {
                valueForMonth(year, month, meterTypeName, userId);
                throw new ValueAlreadyExistsException("Value already exists");
            } catch (NoValueException e) {
            }

            PreparedStatement statement = connection
                    .prepareStatement(
                            "INSERT INTO meter_value_liquibase " +
                                    "(value, year, month, meter_type_id, user_id)\n" +
                                    "VALUES (?,?,?,\n" +
                                    "        (SELECT id\n" +
                                    "         FROM meter_type_liquibase\n" +
                                    "         WHERE type = ?),\n" +
                                    "        ?);");
            statement.setInt(1, value);
            statement.setInt(2, year);
            statement.setInt(3, month);
            statement.setString(4, meterTypeName.name());
            statement.setLong(5, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Sql exception: " + e.getMessage());
        }
        return true;
    }

    @Override
    public List<MeterValue> allValuesUser(Long userId, MeterTypeName meterTypeName) {
        List<MeterValue> meterValues = new ArrayList<>();

        try (Connection connection = dbConnectionProvider.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement(
                            "SELECT *\n" +
                                    "FROM meter_value_liquibase as mvl\n" +
                                    "         LEFT JOIN meter_type_liquibase AS mtl\n" +
                                    "                   ON mvl.meter_type_id = mtl.id\n" +
                                    "WHERE mvl.user_id = ?\n" +
                                    "  AND mtl.type = ?;");
            statement.setLong(1, userId);
            statement.setString(2, meterTypeName.name());
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
