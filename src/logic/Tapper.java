package logic;

public class Tapper {
    private int bal = 0; // Переменная для хранения баланса

    // Метод для увеличения баланса
    public void incrementBalance() {
        bal += 1;
    }

    // Метод для получения текущего баланса
    public int getBalance() {
        return bal;
    }
}
