package ru.homelab.service.impl;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import ru.homelab.service.DBConnectionProvider;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionProviderImpl implements DBConnectionProvider {
    private static final String DRIVER_KEY = "db.driver";
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
    private final ComboPooledDataSource cpds;

    static {
        loadDriver();
    }

    // todo не грузит драйвер
    private static void loadDriver() {
//        try {
//            Class.forName(PropertiesService.get(DRIVER_KEY));
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException("sql exception, load driver Postgresql: " + e.getMessage());
//        }
    }

    public DBConnectionProviderImpl() {
        this.cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass(PropertiesService.get(DRIVER_KEY));
            cpds.setJdbcUrl(PropertiesService.get(URL_KEY));
            cpds.setUser(PropertiesService.get(USERNAME_KEY));
            cpds.setPassword(PropertiesService.get(PASSWORD_KEY));
        } catch (PropertyVetoException e) {
            throw new RuntimeException("sql exception, load driver Postgresql: " + e.getMessage());
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