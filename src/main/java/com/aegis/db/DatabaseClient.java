package com.aegis.db;

import com.aegis.config.ConfigReader;
import java.sql.*;
import java.util.*;

public final class DatabaseClient {
    private DatabaseClient() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            ConfigReader.get("db.url"),
            ConfigReader.get("db.username"),
            ConfigReader.get("db.password")
        );
    }

    /** Executes a parameterized SELECT and returns rows as maps keyed by column label. */
    public static List<Map<String, Object>> query(String sql, Object... parameters) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                ResultSetMetaData metadata = resultSet.getMetaData();
                int columns = metadata.getColumnCount();
                List<Map<String, Object>> rows = new ArrayList<>();
                while (resultSet.next()) {
                    Map<String, Object> row = new LinkedHashMap<>();
                    for (int column = 1; column <= columns; column++) {
                        row.put(metadata.getColumnLabel(column), resultSet.getObject(column));
                    }
                    rows.add(row);
                }
                return rows;
            }
        }
    }
}
