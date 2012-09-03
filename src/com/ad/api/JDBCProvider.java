package com.ad.api;

import com.ad.e.JDBCProviderException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public enum JDBCProvider {
    POSTGRESQL_DRIVER("org.postgresql.Driver");

    private final String driverName;
    private Connection connection = null;

    private JDBCProvider(String driverName) {
        this.driverName = driverName;
    }

    public boolean existConnection(){
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            throw new JDBCProviderException(e);
        }
    }

    public void openConnection() {
        if (!existConnection())
        {
            String JDBC_URI = System.getProperty(Constants.JDBC_URL_KEY);
            if (JDBC_URI != null) {
                try {
                    Class.forName(driverName);
                    connection = DriverManager.getConnection(JDBC_URI);
                    connection.setAutoCommit(true);
                } catch (Exception e) {
                    throw new JDBCProviderException(e);
                }
            } else {
                throw new IllegalArgumentException(String.format("Global property %s isn't exist", Constants.JDBC_URL_KEY));
            }
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new JDBCProviderException(e);
        }
    }

    public Statement statement() {
        if (connection == null) {
            throw new IllegalArgumentException("java.sql.Connection object isn't exist");
        }
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            throw new JDBCProviderException(e);
        }
    }

    public void executeUpdate(String sql) {
        try {
            Statement statement = statement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            throw new JDBCProviderException(e);
        }
    }

    public abstract static class DBElement<TRequest>
    {
        protected DBElement(){}
        protected DBElement(JDBCProvider provider, TRequest request) {}
    }
}
