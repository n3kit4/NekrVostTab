package logic.balance;

public interface BalanceDAO {
    int getBalance(long chatId); // Получить баланс
    void setBalance(long chatId, int balance); // Установить баланс
    boolean exists(long chatId); // Проверить, существует ли баланс для пользователя
}