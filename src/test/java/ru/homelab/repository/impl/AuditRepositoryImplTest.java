package ru.homelab.repository.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.homelab.entity.Audit;
import ru.homelab.repository.AuditRepository;
import ru.homelab.repository.CreateContainerAndRunMigration;
import ru.homelab.service.DBConnectionProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuditRepositoryImplTest extends CreateContainerAndRunMigration {
    private AuditRepository auditRepository;

    @BeforeEach
    void setUp() {
        DBConnectionProvider dbConnectionProvider = new DBConnectionProvider() {
            @Override
            public Connection getConnection() throws SQLException {
                return DriverManager.getConnection(url, username, password);
            }
        };
        this.auditRepository = new AuditRepositoryImpl(dbConnectionProvider);
    }

    @Test
    void getAll() {
        auditRepository.save(new Audit(new Date().toString(),
                "получение всех значений отопления", 1L));
        List<Audit> audits = auditRepository.getAll();
        assertEquals(1, audits.size());
    }
}