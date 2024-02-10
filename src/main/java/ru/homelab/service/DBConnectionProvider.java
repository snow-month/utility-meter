package ru.homelab.service;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnectionProvider {
     Connection getConnection() throws SQLException;
}
