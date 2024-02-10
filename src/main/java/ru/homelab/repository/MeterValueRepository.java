package ru.homelab.repository;

import ru.homelab.entity.MeterValue;
import ru.homelab.exception.NoValueException;
import ru.homelab.exception.ValueAlreadyExistsException;

import java.util.List;

public interface MeterValueRepository {
    Integer currentValue(long userId) throws NoValueException;

    boolean addValue(int value, int year, int month, long meterTypeId, long userId) throws ValueAlreadyExistsException;

    Integer valueForMonth(int year, int month, long userId) throws NoValueException;

    List<MeterValue> allValuesUser(Long userId);

    // todo только admin, проверяем роль в контроллере?
    List<MeterValue> allValue();
}
