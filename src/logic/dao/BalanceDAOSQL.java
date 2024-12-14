package logic.dao;

import java.sql.*;

public class BalanceDAOSQL implements BalanceDAO {

    // Метод для получения соединения с базой данных
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }

    @Override
    public int getBalance(long chatId) {
        String query = "SELECT balance FROM balances WHERE chat_id = ?"; // Запрос для получения баланса по chatId
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) { // Создаем подготовленный запрос

            statement.setLong(1, chatId); // Заменяем первый параметр "?" на фактическое значение chatId

            ResultSet resultSet = statement.executeQuery(); // Выполняем запрос и получаем результат
            if (resultSet.next()) {
                return resultSet.getInt("balance"); // Если запись найдена, возвращаем баланс
            } else {
                return 0; // Если запись не найдена, возвращаем 0
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // В случае ошибки возвращаем 0
        }
    }

    @Override
    public void setBalance(long chatId, int balance) {
        String query = "INSERT OR REPLACE INTO balances (chat_id, balance) VALUES (?, ?)"; // Запрос для установки или обновления баланса

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, chatId); // Устанавливаем chatId
            statement.setInt(2, balance); // Устанавливаем новый баланс

            statement.executeUpdate(); // Выполняем запрос

        } catch (SQLException e) {
            e.printStackTrace(); // В случае ошибки печатаем стек
        }
    }

    @Override
    public boolean exists(long chatId) {
        String query = "SELECT 1 FROM balances WHERE chat_id = ?"; // Запрос для проверки существования записи по chatId

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, chatId); // Устанавливаем chatId

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Если результат есть, значит запись существует

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // В случае ошибки возвращаем false
        }
    }
}
