package com.ad.api.jdbc;

import java.sql.*;

public enum JDBCProvider {
    POSTGRESQL_ADDB(
            "org.postgresql.Driver",
            "jdbc:postgresql://localhost/dbname",
            "userName",
            "password"
    );

    private final String driverName;
    private final String connectionURI;
    private final String userName;
    private final String password;

    private final Connection connection;

    private JDBCProvider(String driverName, String connectionURI, String userName, String password) {
        this.driverName = driverName;
        this.connectionURI = connectionURI;
        this.userName = userName;
        this.password = password;

        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(connectionURI, userName, password);
            connection.setAutoCommit(true);
            statement();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Statement statement() {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeUpdate(String sql){
        try {
            Statement statement = statement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
