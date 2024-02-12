package ru.homelab.service;

import ru.homelab.dto.MeterValueDto;
import ru.homelab.entity.MeterTypeName;
import ru.homelab.exception.NoValueException;
import ru.homelab.exception.ValueAlreadyExistsException;

import java.util.List;

public interface MeterValueService {
    Integer currentValue(MeterTypeName meterTypeName) throws NoValueException;

    boolean addValue(int value, MeterTypeName meterTypeName) throws ValueAlreadyExistsException;

    Integer valueForMonth(int year, int month, MeterTypeName meterTypeName) throws NoValueException;

    List<MeterValueDto> allValuesUser(MeterTypeName meterTypeName);

    List<MeterValueDto> allValue();
}
