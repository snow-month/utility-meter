package ru.homelab.service.impl;

import ru.homelab.entity.Audit;
import ru.homelab.entity.MessageAudit;
import ru.homelab.repository.AuditRepository;
import ru.homelab.service.AuditService;

import java.util.Date;
import java.util.List;

public class AuditServiceImpl implements AuditService {
    private final AuditRepository auditRepository;

    public AuditServiceImpl(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    @Override
    public void save(MessageAudit messageAudit) {
        String date = new Date().toString();
        // todo fix
        //        long userId = Authorization.CURRENT_USER.get().getId();
        long userId = 1;
        Audit audit = new Audit(date, messageAudit.name(), userId);
        auditRepository.save(audit);
    }

    @Override
    public List<Audit> getAll() {
        return auditRepository.getAll();
    }
}
