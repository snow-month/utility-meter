package ru.homelab.service;

import ru.homelab.entity.Audit;
import ru.homelab.entity.MessageAudit;

import java.util.List;

public interface AuditService {
    void save(MessageAudit messageAudit);

    List<Audit> getAll();
}
