package ru.homelab.repository;

import ru.homelab.entity.Audit;

import java.util.List;

public interface AuditRepository {
    void save(Audit audit);

    List<Audit> getAll();
}
