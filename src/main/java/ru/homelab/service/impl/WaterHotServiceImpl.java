package ru.homelab.service.impl;

import ru.homelab.entity.MessageAudit;
import ru.homelab.entity.WaterHot;
import ru.homelab.exception.NoValueException;
import ru.homelab.exception.ValueAlreadyExistsException;
import ru.homelab.repository.WaterHotRepository;
import ru.homelab.service.AuditService;
import ru.homelab.service.WaterHotService;

import java.util.Calendar;
import java.util.List;

public class WaterHotServiceImpl implements WaterHotService {
    private final WaterHotRepository waterHotRepository;
    private final AuditService auditService;

    public WaterHotServiceImpl(WaterHotRepository waterHotRepository, AuditService auditService) {
        this.waterHotRepository = waterHotRepository;
        this.auditService = auditService;
    }

    @Override
    public Integer currentValue() throws NoValueException {
        auditService.save(MessageAudit.VIEWING_READINGS_FOR_THE_MONTH);
        long userId = currentUserId();
        return waterHotRepository.currentValue(userId);
    }

    @Override
    public boolean addValue(int value) throws ValueAlreadyExistsException {
        auditService.save(MessageAudit.ADD_VALUE);
        return waterHotRepository
                .addValue(value, currentYear(), currentMonth(), currentUserId());
    }

    @Override
    public Integer valueForMonth(int year, int month, long userId) throws NoValueException {
        auditService.save(MessageAudit.VIEWING_READINGS_FOR_THE_MONTH);
        return waterHotRepository.valueForMonth(year, month, userId);
    }

    @Override
    public List<WaterHot> allValuesUser() {
        auditService.save(MessageAudit.GETTING_HISTORY_OF_GIVING_TESTIMONY);
        return waterHotRepository.allValuesUser(currentUserId());
    }

    @Override
    public List<WaterHot> allValue() {
        auditService.save(MessageAudit.GETTING_HISTORY_OF_GIVING_TESTIMONY);
        return waterHotRepository.allValue();
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
