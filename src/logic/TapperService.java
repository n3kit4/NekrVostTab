package logic;

import logic.balance.BalanceDAO;

public class TapperService {
    private final BalanceDAO balanceDAO; // Зависимость от BalanceDAO

    // Конструктор для инициализации
    public TapperService(BalanceDAO balanceDAO) {
        this.balanceDAO = balanceDAO;
    }

    // Метод для получения баланса пользователя
    public int getBalance(long chatId) {
        return balanceDAO.getBalance(chatId);
    }

    // Метод для увеличения баланса пользователя
    public void incrementBalance(long chatId) {
        int currentBalance = balanceDAO.getBalance(chatId);
        balanceDAO.setBalance(chatId, currentBalance + 1);
    }

    // Метод для проверки существования пользователя
    public boolean exists(long chatId) {
        return balanceDAO.exists(chatId);
    }
}