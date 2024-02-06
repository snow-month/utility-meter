package ru.homelab.repository.impl;

import org.junit.jupiter.api.Test;
import ru.homelab.entity.WaterCold;
import ru.homelab.exception.NoValueException;
import ru.homelab.exception.ValueAlreadyExistsException;
import ru.homelab.repository.CreateContainerAndRunMigration;
import ru.homelab.repository.WaterColdRepository;
import ru.homelab.utils.PropertiesApp;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WaterColdRepositoryImplTest extends CreateContainerAndRunMigration {
    private final PropertiesApp propertiesApp = new PropertiesApp(
            CreateContainerAndRunMigration.url,
            CreateContainerAndRunMigration.username,
            CreateContainerAndRunMigration.password
    );
    private final WaterColdRepository waterColdRepository = new WaterColdRepositoryImpl(propertiesApp);

    @Test
    void currentValue() throws NoValueException {
        Integer value = waterColdRepository.currentValue(1);
        assertEquals(122, value);
    }

    @Test
    void currentValue_ThrowsNoValueException() {
        Throwable exception = assertThrows(NoValueException.class,
                () -> waterColdRepository.currentValue(1112));
        assertEquals("no value", exception.getMessage());
    }

    @Test
    void valueForMonth() throws NoValueException {
        Integer value = waterColdRepository.valueForMonth(2023, 6, 1);
        assertEquals(67, value);
    }

    @Test
    void valueForMonth_ThrowsNoValueException() {
        Throwable exception = assertThrows(NoValueException.class,
                () -> waterColdRepository.valueForMonth(2024, 11, 1)
        );
        assertEquals("no value", exception.getMessage());
    }

    @Test
    void addValue() throws ValueAlreadyExistsException, NoValueException {
        int value = 77777;
        int year = 2023;
        int month = 4;
        long userId = 12132;
        waterColdRepository.addValue(value, year, month, userId);
        Integer currentValue = waterColdRepository.currentValue(userId);
        assertEquals(value, currentValue);
    }

    @Test
    void addValue_ThrowsValueAlreadyExistsException() {
        int value = 77777;
        int year = 2023;
        int month = 6;
        long userId = 1;
        Throwable exception = assertThrows(ValueAlreadyExistsException.class,
                () -> waterColdRepository.addValue(value, year, month, userId));
        assertEquals("Value already exists", exception.getMessage());
    }

    @Test
    void allValuesUser() {
        List<WaterCold> waterColds = waterColdRepository.allValuesUser(1L);
        assertEquals(4, waterColds.size());
    }

    @Test
    void allValue() {
        List<WaterCold> waterColds = waterColdRepository.allValue();
        assertEquals(6, waterColds.size());
    }
}