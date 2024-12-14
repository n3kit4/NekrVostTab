package logic.dao;

import java.util.Map;
import java.util.HashMap;

public class BalanceDAOHashMap implements BalanceDAO {
    private final Map<Long, Integer> balances = new HashMap<>();

    @Override
    public int getBalance(long chatId) {
        return balances.computeIfAbsent(chatId, k -> 0); // Возвращаем 0, если баланса нет
    }

    @Override
    public void setBalance(long chatId, int balance) {
        balances.put(chatId, balance); // Сохраняем новый баланс
    }

    @Override
    public boolean exists(long chatId) {
        return balances.containsKey(chatId); // Проверяем, существует ли баланс
    }
}