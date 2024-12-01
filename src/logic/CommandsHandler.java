package logic;

public class CommandsHandler {
    private final Tapper tapper = new Tapper(); // Создаем объект для управления балансом

    public Response processRequest(Request request) {
        String message = request.getMessage();
        String valueS;

        switch (message) {
            case "/start":
                return new Response("Привет! Вас приветствует UrFUCoin бот. Я пока ещё маленький, но скоро стану большим и крутым!");

            case "/balance":
                valueS = "Ваш текущий баланс: " + tapper.getBalance() + " монет.";
                return new Response(valueS);

            case "/tap":
                tapper.incrementBalance(); // Увеличиваем баланс на 1
                valueS = "Ваш баланс пополнился на 1 монету!";
                return new Response(valueS);

            case "/help":
                return new Response("Доступные команды: /start, /balance, /tap, /help");

            default:
                return new Response("Неизвестная команда, введите /help для получения списка доступных команд.");
        }
    }
}
