package ru.homelab.service.impl;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The type Migration service.
 */
public class MigrationService {
    /**
     * Init.
     *
     * @param url      the url
     * @param username the username
     * @param password the password
     */
    public void init(String url, String username, String password, String changelog,
                     String defaultSchemaName) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Database database = DatabaseFactory.getInstance()
                    .findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new Liquibase(changelog,
                    new ClassLoaderResourceAccessor(), database);

            database.setDefaultSchemaName(defaultSchemaName);

            liquibase.update();
            System.out.println("Migration is completed successfully");
        } catch (SQLException | LiquibaseException e) {
            System.out.println("sql Exception in migration: " + e.getMessage());
        }
    }
}
