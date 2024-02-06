package ru.homelab.repository.impl;

import org.junit.jupiter.api.Test;
import ru.homelab.entity.HeatMeter;
import ru.homelab.exception.NoValueException;
import ru.homelab.exception.ValueAlreadyExistsException;
import ru.homelab.repository.CreateContainerAndRunMigration;
import ru.homelab.repository.HeatMeterRepository;
import ru.homelab.utils.PropertiesApp;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HeatMeterRepositoryImplTest extends CreateContainerAndRunMigration {
    private final PropertiesApp propertiesApp = new PropertiesApp(
            CreateContainerAndRunMigration.url,
            CreateContainerAndRunMigration.username,
            CreateContainerAndRunMigration.password
    );
    private final HeatMeterRepository heatMeterRepository = new HeatMeterRepositoryImpl(propertiesApp);

    @Test
    void currentValue() throws NoValueException {
        Integer value = heatMeterRepository.currentValue(1);
        assertEquals(122, value);
    }

    @Test
    void currentValue_ThrowsNoValueException() {
        Throwable exception = assertThrows(NoValueException.class,
                () -> heatMeterRepository.currentValue(1112));
        assertEquals("no value", exception.getMessage());
    }

    @Test
    void valueForMonth() throws NoValueException {
        Integer value = heatMeterRepository.valueForMonth(2023, 6, 1);
        assertEquals(67, value);
    }

    @Test
    void valueForMonth_ThrowsNoValueException() {
        Throwable exception = assertThrows(NoValueException.class,
                () -> heatMeterRepository.valueForMonth(2024, 11, 1)
        );
        assertEquals("no value", exception.getMessage());
    }

    @Test
    void addValue() throws ValueAlreadyExistsException, NoValueException {
        int value = 77777;
        int year = 2023;
        int month = 4;
        long userId = 12132;
        heatMeterRepository.addValue(value, year, month, userId);
        Integer currentValue = heatMeterRepository.currentValue(userId);
        assertEquals(value, currentValue);
    }

    @Test
    void addValue_ThrowsValueAlreadyExistsException() {
        int value = 77777;
        int year = 2023;
        int month = 6;
        long userId = 1;
        Throwable exception = assertThrows(ValueAlreadyExistsException.class,
                () -> heatMeterRepository.addValue(value, year, month, userId));
        assertEquals("Value already exists", exception.getMessage());
    }

    @Test
    void allValuesUser() {
        List<HeatMeter> heatMeters = heatMeterRepository.allValuesUser(1L);
        assertEquals(4, heatMeters.size());
    }

    @Test
    void allValue() {
        List<HeatMeter> heatMeters = heatMeterRepository.allValue();
        assertEquals(6, heatMeters.size());
    }
}