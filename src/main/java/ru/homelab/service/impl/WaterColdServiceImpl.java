package ru.homelab.service.impl;

import ru.homelab.entity.MessageAudit;
import ru.homelab.entity.WaterCold;
import ru.homelab.exception.NoValueException;
import ru.homelab.exception.ValueAlreadyExistsException;
import ru.homelab.repository.WaterColdRepository;
import ru.homelab.service.AuditService;
import ru.homelab.service.WaterColdService;

import java.util.Calendar;
import java.util.List;

public class WaterColdServiceImpl implements WaterColdService {
    private final WaterColdRepository waterColdRepository;
    private final AuditService auditService;

    public WaterColdServiceImpl(WaterColdRepository waterColdRepository, AuditService auditService) {
        this.waterColdRepository = waterColdRepository;
        this.auditService = auditService;
    }

    @Override
    public Integer currentValue() throws NoValueException {
        auditService.save(MessageAudit.GIVING_EVIDENCE);
        long userId = currentUserId();
        return waterColdRepository.currentValue(userId);
    }

    @Override
    public boolean addValue(int value) throws ValueAlreadyExistsException {
        auditService.save(MessageAudit.ADD_VALUE);
        return waterColdRepository
                .addValue(value, currentYear(), currentMonth(), currentUserId());
    }

    @Override
    public Integer valueForMonth(int year, int month, long userId) throws NoValueException {
        auditService.save(MessageAudit.VIEWING_READINGS_FOR_THE_MONTH);
        return waterColdRepository.valueForMonth(year, month, userId);
    }

    @Override
    public List<WaterCold> allValuesUser() {
        auditService.save(MessageAudit.GETTING_HISTORY_OF_GIVING_TESTIMONY);
        return waterColdRepository.allValuesUser(currentUserId());
    }

    @Override
    public List<WaterCold> allValue() {
        auditService.save(MessageAudit.GETTING_HISTORY_OF_GIVING_TESTIMONY);
        return waterColdRepository.allValue();
    }

    private int currentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    private int currentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    private static Long currentUserId() {
        return AuthorizationService.CURRENT_USER.get().getId();
    }
}
