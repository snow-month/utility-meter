package ru.homelab.service.impl;

import ru.homelab.entity.HeatMeter;
import ru.homelab.entity.MessageAudit;
import ru.homelab.exception.NoValueException;
import ru.homelab.exception.ValueAlreadyExistsException;
import ru.homelab.repository.HeatMeterRepository;
import ru.homelab.security.Authorization;
import ru.homelab.service.AuditService;
import ru.homelab.service.HeatMeterService;

import java.util.Calendar;
import java.util.List;

public class HeatMeterServiceImpl implements HeatMeterService {
    private final HeatMeterRepository heatMeterRepository;
    private final AuditService auditService;

    public HeatMeterServiceImpl(HeatMeterRepository heatMeterRepository, AuditService auditService) {
        this.heatMeterRepository = heatMeterRepository;
        this.auditService = auditService;
    }

    @Override
    public Integer currentValue() throws NoValueException {
        auditService.save(MessageAudit.VIEW_CURRENT_READINGS);
        long userId = currentUserId();
        return heatMeterRepository.currentValue(userId);
    }

    @Override
    public boolean addValue(int value) throws ValueAlreadyExistsException {
        auditService.save(MessageAudit.ADD_VALUE);
        return heatMeterRepository
                .addValue(value, currentYear(), currentMonth(), currentUserId());
    }

    @Override
    public Integer valueForMonth(int year, int month, long userId) throws NoValueException {
        auditService.save(MessageAudit.VIEWING_READINGS_FOR_THE_MONTH);
        return heatMeterRepository.valueForMonth(year, month, userId);
    }

    @Override
    public List<HeatMeter> allValuesUser() {
        auditService.save(MessageAudit.GETTING_HISTORY_OF_GIVING_TESTIMONY);
        return heatMeterRepository.allValuesUser(currentUserId());
    }

    @Override
    public List<HeatMeter> allValue() {
        auditService.save(MessageAudit.GETTING_HISTORY_OF_GIVING_TESTIMONY);
        return heatMeterRepository.allValue();
    }

    private int currentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    private int currentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    private static Long currentUserId() {
        return Authorization.CURRENT_USER.get().getId();
    }
}
