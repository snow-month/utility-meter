package ru.homelab.repository.impl;

import ru.homelab.entity.MeterType;
import ru.homelab.entity.MeterTypeName;
import ru.homelab.repository.MeterTypeRepository;
import ru.homelab.service.DBConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MeterTypeRepositoryImpl implements MeterTypeRepository {
    private final DBConnectionProvider dbConnectionProvider;

    public MeterTypeRepositoryImpl(DBConnectionProvider dbConnectionProvider) {
        this.dbConnectionProvider = dbConnectionProvider;
    }

    @Override
    public void save(MeterTypeName meterTypeName) {
        try (Connection connection = dbConnectionProvider.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO meter_type_liquibase (type) VALUES (?);");
            statement.setString(1, meterTypeName.name());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Sql exception: " + e.getMessage());
        }
    }

    @Override
    public List<MeterType> all() {
        ArrayList<MeterType> meterTypes = new ArrayList<>();
        try (Connection connection = dbConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM meter_type_liquibase");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                meterTypes.add(new MeterType(
                        resultSet.getLong("id"),
                        MeterTypeName.valueOf(resultSet.getString("type"))
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return meterTypes;
    }
}