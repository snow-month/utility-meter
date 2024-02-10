package ru.homelab.repository.impl;

import ru.homelab.entity.MeterType;
import ru.homelab.repository.MeterTypeRepository;
import ru.homelab.service.DBConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MeterTypeRepositoryImpl implements MeterTypeRepository {
    private final DBConnectionProvider dbConnectionProvider;

    public MeterTypeRepositoryImpl(DBConnectionProvider dbConnectionProvider) {
        this.dbConnectionProvider = dbConnectionProvider;
    }

    @Override
    public void save(MeterType meterType) {
        try (Connection connection = dbConnectionProvider.getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO meter_type_liquibase (type) VALUES (?);");
            statement.setString(1, meterType.getType());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Sql exception: " + e.getMessage());
        }
    }
}
