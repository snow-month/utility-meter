package ru.homelab.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.homelab.entity.MeterTypeName;
import ru.homelab.entity.MeterValue;
import ru.homelab.exception.NoValueException;
import ru.homelab.exception.ValueAlreadyExistsException;
import ru.homelab.repository.CreateContainerAndRunMigration;
import ru.homelab.repository.MeterValueRepository;
import ru.homelab.service.DBConnectionProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MeterValueRepositoryImplTest extends CreateContainerAndRunMigration {
    private MeterValueRepository meterValueRepository;

    @BeforeEach
    void setUp() {
        DBConnectionProvider dbConnectionProvider = new DBConnectionProvider() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(url, username, password);
            }
        };
        this.meterValueRepository = new MeterValueRepositoryImpl(dbConnectionProvider);
    }

    @Test
    void currentValue() throws NoValueException {
        Integer value = meterValueRepository.currentValue(1, MeterTypeName.HEATING);
        assertEquals(147, value);
    }

    @Test
    void currentValue_ThrowsNoValueException() {
        Throwable exception = assertThrows(NoValueException.class,
                () -> meterValueRepository.currentValue(1112, MeterTypeName.HEATING));
        assertEquals("no value", exception.getMessage());
    }

    @Test
    void valueForMonth() throws NoValueException {
        Integer value = meterValueRepository
                .valueForMonth(2023, 6, MeterTypeName.HEATING, 1);
        assertEquals(67, value);
    }

    @Test
    void valueForMonth_ThrowsNoValueException() {
        Throwable exception = assertThrows(NoValueException.class,
                () -> meterValueRepository
                        .valueForMonth(2024, 11, MeterTypeName.HEATING, 1)
        );
        assertEquals("no value", exception.getMessage());
    }

    @Test
    void addValue() throws ValueAlreadyExistsException, NoValueException {
        int value = 77777;
        int year = 2023;
        int month = 4;
        MeterTypeName meterTypeName = MeterTypeName.HEATING;
        long userId = 12132;
        meterValueRepository.addValue(value, year, month, meterTypeName, userId);
        Integer currentValue = meterValueRepository.currentValue(userId, meterTypeName);
        assertEquals(value, currentValue);
    }

    @Test
    void addValue_ThrowsValueAlreadyExistsException() {
        int value = 147;
        int year = 2024;
        int month = 1;
        MeterTypeName meterTypeName = MeterTypeName.HEATING;
        long userId = 2;
        Throwable exception = assertThrows(ValueAlreadyExistsException.class,
                () -> meterValueRepository.addValue(value, year, month, meterTypeName, userId));
        assertEquals("Value already exists", exception.getMessage());
    }

    @Test
    void allValuesUser() {
        List<MeterValue> meterValues = meterValueRepository.allValuesUser(1L, MeterTypeName.HEATING);
        assertEquals(5, meterValues.size());
    }

    @Test
    void allValue() {
        List<MeterValue> meterValues = meterValueRepository.allValue();
        assertEquals(9, meterValues.size());
    }
}