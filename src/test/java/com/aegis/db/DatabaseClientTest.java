package com.aegis.db;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assumptions;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;

class DatabaseClientTest {
    @Test
    void databaseConnectivitySmokeCheckWhenLocalDbIsConfigured() throws SQLException {
        Connection connection;
        try {
            connection = DatabaseClient.getConnection();
        } catch (SQLException unavailable) {
            Assumptions.abort("Local SQL Server is not configured/running yet: " + unavailable.getMessage());
            return;
        }
        try (connection) {
            assertTrue(connection.isValid(3), "JDBC connection should be valid");
            assertEquals(1, DatabaseClient.query("SELECT 1 AS probe").get(0).get("probe"));
        }
    }
}
