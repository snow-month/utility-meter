package ru.homelab.service;

import ru.homelab.entity.Audit;
import ru.homelab.entity.MessageAudit;

import java.util.List;

/**
 * The interface Audit service.
 */
public interface AuditService {
    /**
     * Save.
     *
     * @param messageAudit the message audit
     */
    void save(MessageAudit messageAudit);

    /**
     * Gets all.
     *
     * @return the all
     */
    List<Audit> getAll();
}
