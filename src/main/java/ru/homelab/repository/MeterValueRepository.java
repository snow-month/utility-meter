package ru.homelab.repository;

import ru.homelab.entity.MeterTypeName;
import ru.homelab.entity.MeterValue;
import ru.homelab.exception.NoValueException;
import ru.homelab.exception.ValueAlreadyExistsException;

import java.util.List;

public interface MeterValueRepository {
    Integer currentValue(long userId, MeterTypeName name) throws NoValueException;

    boolean addValue(int value, int year, int month, MeterTypeName meterTypeName, long userId) throws ValueAlreadyExistsException;

    Integer valueForMonth(int year, int month, MeterTypeName meterTypeName, long userId) throws NoValueException;

    List<MeterValue> allValuesUser(Long userId, MeterTypeName meterTypeName);

    // todo только admin, проверяем роль в контроллере?
    List<MeterValue> allValue();
}
