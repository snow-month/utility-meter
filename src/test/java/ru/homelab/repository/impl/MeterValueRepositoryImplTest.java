package ru.homelab.repository.impl;

import org.junit.jupiter.api.Test;
import ru.homelab.entity.MeterValue;
import ru.homelab.exception.NoValueException;
import ru.homelab.exception.ValueAlreadyExistsException;
import ru.homelab.repository.CreateContainerAndRunMigration;
import ru.homelab.repository.MeterValueRepository;
import ru.homelab.utils.PropertiesApp;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MeterValueRepositoryImplTest extends CreateContainerAndRunMigration {
    private final PropertiesApp propertiesApp = new PropertiesApp(
            CreateContainerAndRunMigration.url,
            CreateContainerAndRunMigration.username,
            CreateContainerAndRunMigration.password
    );
    private final MeterValueRepository meterValueRepository = new MeterValueRepositoryImpl(propertiesApp);

    @Test
    void currentValue() throws NoValueException {
        Integer value = meterValueRepository.currentValue(1);
        assertEquals(122, value);
    }

    @Test
    void currentValue_ThrowsNoValueException() {
        Throwable exception = assertThrows(NoValueException.class,
                () -> meterValueRepository.currentValue(1112));
        assertEquals("no value", exception.getMessage());
    }

    @Test
    void valueForMonth() throws NoValueException {
        Integer value = meterValueRepository.valueForMonth(2023, 6, 1);
        assertEquals(67, value);
    }

    @Test
    void valueForMonth_ThrowsNoValueException() {
        Throwable exception = assertThrows(NoValueException.class,
                () -> meterValueRepository.valueForMonth(2024, 11, 1)
        );
        assertEquals("no value", exception.getMessage());
    }

    @Test
    void addValue() throws ValueAlreadyExistsException, NoValueException {
        int value = 77777;
        int year = 2023;
        int month = 4;
        long meterTypeId = 2;
        long userId = 12132;
        meterValueRepository.addValue(value, year, month, meterTypeId, userId);
        Integer currentValue = meterValueRepository.currentValue(userId);
        assertEquals(value, currentValue);
    }

    @Test
    void addValue_ThrowsValueAlreadyExistsException() {
        int value = 77777;
        int year = 2023;
        int month = 6;
        long meterTypeId = 3;
        long userId = 1;
        Throwable exception = assertThrows(ValueAlreadyExistsException.class,
                () -> meterValueRepository.addValue(value, year, month, meterTypeId, userId));
        assertEquals("Value already exists", exception.getMessage());
    }

    @Test
    void allValuesUser() {
        List<MeterValue> meterValues = meterValueRepository.allValuesUser(1L);
        assertEquals(4, meterValues.size());
    }

    @Test
    void allValue() {
        List<MeterValue> meterValues = meterValueRepository.allValue();
        assertEquals(5, meterValues.size());
    }
}