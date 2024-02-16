package ru.homelab.service;

import ru.homelab.entity.Audit;

import java.util.List;

/**
 * The interface Audit service.
 */
public interface AuditService {
    /**
     * Save.
     *
     * @param message the message audit
     */
    void save(String message);

    /**
     * Gets all.
     *
     * @return the all
     */
    List<Audit> getAll();
}
