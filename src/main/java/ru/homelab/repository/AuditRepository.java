package ru.homelab.repository;

import ru.homelab.entity.Audit;

import java.util.List;

/**
 * The interface Audit repository.
 */
public interface AuditRepository {
    /**
     * Save.
     *
     * @param audit the audit
     */
    void save(Audit audit);

    /**
     * Gets all.
     *
     * @return the all
     */
    List<Audit> getAll();
}
