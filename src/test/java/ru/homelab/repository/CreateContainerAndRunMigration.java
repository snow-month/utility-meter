package ru.homelab.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.homelab.service.impl.MigrationService;

public class CreateContainerAndRunMigration {
    protected static String url;
    protected static String username;
    protected static String password;
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
        url = postgres.getJdbcUrl();
        username = postgres.getUsername();
        password = postgres.getPassword();
        new MigrationService().init(
                url,
                username,
                password,
                "dbtest/changelog/changelog.xml",
                "public");
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }
}
