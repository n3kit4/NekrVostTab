package logic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        try {
            // Устанавливаем соединение с SQLite базой данных
            String url = "jdbc:sqlite:balances.db";
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new SQLException("Connection to database failed", e);
        }
    }
}