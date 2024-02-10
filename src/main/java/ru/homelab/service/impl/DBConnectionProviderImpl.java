package ru.homelab.service.impl;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import ru.homelab.service.DBConnectionProvider;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionProviderImpl implements DBConnectionProvider {
    private final ComboPooledDataSource cpds;

    public DBConnectionProviderImpl() {
        this.cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass(PropertiesService.get("db.driver"));
            cpds.setJdbcUrl(PropertiesService.get("db.url"));
            cpds.setUser(PropertiesService.get("db.username"));
            cpds.setPassword(PropertiesService.get("db.password"));
        } catch (PropertyVetoException e) {
            System.out.println("sql exception, load driver Postgresql: " + e.getMessage());
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return cpds.getConnection();
    }

    public void closePool() {
        cpds.close();
    }
}