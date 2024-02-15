package ru.homelab.service.impl;

import ru.homelab.dto.MeterValueDto;
import ru.homelab.entity.MeterTypeName;
import ru.homelab.exception.NoValueException;
import ru.homelab.exception.ValueAlreadyExistsException;
import ru.homelab.mapper.MeterValueMapper;
import ru.homelab.repository.MeterValueRepository;
import ru.homelab.service.MeterValueService;

import java.util.Calendar;
import java.util.List;

public class MeterValueServiceImpl implements MeterValueService {
    private MeterValueRepository meterValueRepository;

    public MeterValueServiceImpl() {
    }

    public MeterValueServiceImpl(MeterValueRepository meterValueRepository) {
        this.meterValueRepository = meterValueRepository;
    }

    @Override
    public Integer currentValue(MeterTypeName name) throws NoValueException {
        long userId = currentUserId();
        return meterValueRepository.currentValue(userId, name);
    }

    @Override
    public boolean addValue(int value, MeterTypeName meterTypeName) throws ValueAlreadyExistsException {
        return meterValueRepository
                .addValue(value, currentYear(), currentMonth(), meterTypeName, currentUserId());
    }

    @Override
    public Integer valueForMonth(int year, int month, MeterTypeName meterTypeName) throws NoValueException {
        return meterValueRepository.valueForMonth(year, month, meterTypeName, currentUserId());
    }

    @Override
    public List<MeterValueDto> allValuesUser(MeterTypeName meterTypeName) {
        return meterValueRepository.allValuesUser(currentUserId(), meterTypeName).stream()
                .map(MeterValueMapper.INSTANCE::materValueToMeterValueDto)
                .toList();
    }

    @Override
    public List<MeterValueDto> allValue() {
//        return meterValueRepository.allValue();
        return null;
    }

    private int currentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    private int currentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    private static Long currentUserId() {
//        return AuthorizationService.CURRENT_USER.get().getId();
        return 20L;
    }
}
