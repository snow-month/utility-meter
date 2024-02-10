package ru.homelab.service.impl;

import ru.homelab.entity.MessageAudit;
import ru.homelab.entity.MeterValue;
import ru.homelab.exception.NoValueException;
import ru.homelab.exception.ValueAlreadyExistsException;
import ru.homelab.repository.MeterValueRepository;
import ru.homelab.service.AuditService;
import ru.homelab.service.MeterValueService;

import java.util.Calendar;
import java.util.List;

public class MeterValueServiceImpl implements MeterValueService {
    private final MeterValueRepository meterValueRepository;
    private final AuditService auditService;

    public MeterValueServiceImpl(MeterValueRepository meterValueRepository, AuditService auditService) {
        this.meterValueRepository = meterValueRepository;
        this.auditService = auditService;
    }

    @Override
    public Integer currentValue() throws NoValueException {
        auditService.save(MessageAudit.VIEW_CURRENT_READINGS);
        long userId = currentUserId();
        return meterValueRepository.currentValue(userId);
    }

    @Override
    public boolean addValue(int value, long meterTypeId) throws ValueAlreadyExistsException {
        auditService.save(MessageAudit.ADD_VALUE);
        return meterValueRepository
                .addValue(value, currentYear(), currentMonth(), meterTypeId, currentUserId());
    }

    @Override
    public Integer valueForMonth(int year, int month, long userId) throws NoValueException {
        auditService.save(MessageAudit.VIEWING_READINGS_FOR_THE_MONTH);
        return meterValueRepository.valueForMonth(year, month, userId);
    }

    @Override
    public List<MeterValue> allValuesUser() {
        auditService.save(MessageAudit.GETTING_HISTORY_OF_GIVING_TESTIMONY);
        return meterValueRepository.allValuesUser(currentUserId());
    }

    @Override
    public List<MeterValue> allValue() {
        auditService.save(MessageAudit.GETTING_HISTORY_OF_GIVING_TESTIMONY);
        return meterValueRepository.allValue();
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
