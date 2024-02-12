package ru.homelab.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.homelab.entity.MeterType;
import ru.homelab.repository.CreateContainerAndRunMigration;
import ru.homelab.repository.MeterTypeRepository;
import ru.homelab.service.DBConnectionProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MeterTypeRepositoryImplTest extends CreateContainerAndRunMigration {
    private MeterTypeRepository meterTypeRepository;

    @BeforeEach
    void setUp() {
        DBConnectionProvider dbConnectionProvider = new DBConnectionProvider() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(url, username, password);
            }
        };
        this.meterTypeRepository = new MeterTypeRepositoryImpl(dbConnectionProvider);
    }

    @Test
    void save() {
        List<MeterType> meterTypes = meterTypeRepository.all();
        assertEquals(3, meterTypes.size());
    }
}