package ru.homelab.service.impl;

import ru.homelab.entity.Audit;
import ru.homelab.repository.AuditRepository;
import ru.homelab.service.AuditService;

import java.util.Date;
import java.util.List;

/**
 * The type Audit service.
 */
public class AuditServiceImpl implements AuditService {
    private final AuditRepository auditRepository;

    /**
     * Instantiates a new Audit service.
     *
     * @param auditRepository the audit repository
     */
    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    public void save(String message) {
        String date = new Date().toString();
        long userId = currentUserId();
        Audit audit = new Audit(date, message, userId);
        auditRepository.save(audit);
    }

    @Override
    public List<Audit> getAll() {
        return auditRepository.getAll();
    }

    private static Long currentUserId() {
//        return AuthorizationService.CURRENT_USER.get().getId();
        return 20L;
    }
}
