package ru.homelab.repository.impl;

import org.junit.jupiter.api.Test;
import ru.homelab.entity.WaterHot;
import ru.homelab.exception.NoValueException;
import ru.homelab.exception.ValueAlreadyExistsException;
import ru.homelab.repository.CreateContainerAndRunMigration;
import ru.homelab.repository.WaterHotRepository;
import ru.homelab.utils.PropertiesApp;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WaterHotRepositoryImplTest extends CreateContainerAndRunMigration {
    private final PropertiesApp propertiesApp = new PropertiesApp(
            CreateContainerAndRunMigration.url,
            CreateContainerAndRunMigration.username,
            CreateContainerAndRunMigration.password
    );
    private final WaterHotRepository waterHotRepository = new WaterHotRepositoryImpl(propertiesApp);

    @Test
    void currentValue() throws NoValueException {
        Integer value = waterHotRepository.currentValue(1);
        assertEquals(122, value);
    }

    @Test
    void currentValue_ThrowsNoValueException() {
        Throwable exception = assertThrows(NoValueException.class,
                () -> waterHotRepository.currentValue(1112));
        assertEquals("no value", exception.getMessage());
    }

    @Test
    void valueForMonth() throws NoValueException {
        Integer value = waterHotRepository.valueForMonth(2023, 6, 1);
        assertEquals(67, value);
    }

    @Test
    void valueForMonth_ThrowsNoValueException() {
        Throwable exception = assertThrows(NoValueException.class,
                () -> waterHotRepository.valueForMonth(2024, 11, 1)
        );
        assertEquals("no value", exception.getMessage());
    }

    @Test
    void addValue() throws ValueAlreadyExistsException, NoValueException {
        int value = 77777;
        int year = 2023;
        int month = 4;
        long userId = 12132;
        waterHotRepository.addValue(value, year, month, userId);
        Integer currentValue = waterHotRepository.currentValue(userId);
        assertEquals(value, currentValue);
    }

    @Test
    void addValue_ThrowsValueAlreadyExistsException() {
        int value = 77777;
        int year = 2023;
        int month = 6;
        long userId = 1;
        Throwable exception = assertThrows(ValueAlreadyExistsException.class,
                () -> waterHotRepository.addValue(value, year, month, userId));
        assertEquals("Value already exists", exception.getMessage());
    }

    @Test
    void allValuesUser() {
        List<WaterHot> waterHots = waterHotRepository.allValuesUser(1L);
        assertEquals(4, waterHots.size());
    }

    @Test
    void allValue() {
        List<WaterHot> waterHots = waterHotRepository.allValue();
        assertEquals(6, waterHots.size());
    }
}