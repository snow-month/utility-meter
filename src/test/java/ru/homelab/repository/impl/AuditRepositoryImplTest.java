package ru.homelab.repository.impl;

import org.junit.jupiter.api.Test;
import ru.homelab.entity.Audit;
import ru.homelab.entity.MessageAudit;
import ru.homelab.repository.AuditRepository;
import ru.homelab.repository.CreateContainerAndRunMigration;
import ru.homelab.utils.PropertiesApp;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuditRepositoryImplTest extends CreateContainerAndRunMigration {
    private final PropertiesApp propertiesApp = new PropertiesApp(
            CreateContainerAndRunMigration.url,
            CreateContainerAndRunMigration.username,
            CreateContainerAndRunMigration.password
    );
    private final AuditRepository auditRepository = new AuditRepositoryImpl(propertiesApp);

    @Test
    void getAll() {
        auditRepository.save(new Audit(new Date().toString(),
                MessageAudit.CREATE_NEW_USER.toString(), 1L));
        List<Audit> audits = auditRepository.getAll();
        assertEquals(1, audits.size());
    }
}