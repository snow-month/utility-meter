package ru.homelab.repository.impl;

import ru.homelab.entity.Audit;
import ru.homelab.repository.AuditRepository;
import ru.homelab.utils.PropertiesApp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Audit repository.
 */
public class AuditRepositoryImpl implements AuditRepository {
    private final PropertiesApp property;

    /**
     * Instantiates a new Audit repository.
     *
     * @param property the property
     */
    public AuditRepositoryImpl(PropertiesApp property) {
        this.property = property;
    }

    @Override
    public void save(Audit audit) {
        try (Connection connection = DriverManager.getConnection(property.getUrl(),
                property.getUsername(), property.getPassword())) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO company.audit_liquibase" +
                    " (date, message_audit, user_id) VALUES (?,?,?);");
            statement.setString(1, audit.getDate());
            statement.setString(2, audit.getMessageAudit());
            statement.setLong(3, audit.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Sql exception: " + e.getMessage());
        }
    }

    @Override
    public List<Audit> getAll() {
        List<Audit> audits = null;
        try (Connection connection = DriverManager.getConnection(property.getUrl(),
                property.getUsername(), property.getPassword())) {
            PreparedStatement statement = connection
                    .prepareStatement("SELECT * FROM company.audit_liquibase;");
            ResultSet resultSet = statement.executeQuery();

            audits = new ArrayList<>();
            while (resultSet.next()) {
                String date = resultSet.getString("date");
                String messageAudit = resultSet.getString("message_audit");
                Long userId = resultSet.getLong("user_id");
                Audit audit = new Audit(date, messageAudit, userId);
                audits.add(audit);
            }
        } catch (SQLException e) {
            System.out.println("Sql exception: " + e.getMessage());

        }
        return audits;
    }
}
