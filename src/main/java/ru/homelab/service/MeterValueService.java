package ru.homelab.service;

import ru.homelab.entity.MeterValue;
import ru.homelab.exception.NoValueException;
import ru.homelab.exception.ValueAlreadyExistsException;

import java.util.List;

public interface MeterValueService {
    Integer currentValue() throws NoValueException;

    boolean addValue(int value, long meterTypeId) throws ValueAlreadyExistsException;

    Integer valueForMonth(int year, int month, long userId) throws NoValueException;

    List<MeterValue> allValuesUser();

    List<MeterValue> allValue();
}
