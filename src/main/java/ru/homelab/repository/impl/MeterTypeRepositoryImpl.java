package ru.homelab.repository.impl;

import ru.homelab.entity.MeterType;
import ru.homelab.repository.MeterTypeRepository;
import ru.homelab.utils.PropertiesApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MeterTypeRepositoryImpl implements MeterTypeRepository {
    private final PropertiesApp property;

    public MeterTypeRepositoryImpl(PropertiesApp property) {
        this.property = property;
    }

    @Override
    public void save(MeterType meterType) {
        try (Connection connection = DriverManager.getConnection(property.getUrl(),
                property.getUsername(), property.getPassword())) {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO meter_type_liquibase (type) VALUES (?);");
            statement.setString(1, meterType.getType());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Sql exception: " + e.getMessage());
        }
    }
}
