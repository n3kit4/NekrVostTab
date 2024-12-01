package logic;

public class CommandsHandler {

    public Response processRequest(Request request){
        String message = request.getMessage();
        int value = 0;
        String valueS = "";
        switch (message){
            case "/start":
                return new Response("Привет! Вас приветствует UrFUCoin бот. Я пока ещё маленький, но скоро стану большим и крутым!");

            case "/balance":
                valueS = "Баланс: " + value;
                return new Response(valueS);

            case "/tap":
                value ++;
                valueS = "Баланс: " + value;
                return new Response("Ваш баланс успешно увеличился на 1 монетку");

            case "/help":
                return new Response("3 команды: /start, /balance, /tap");

            default:
                return new Response("Неизвестная команда, введи /help");
        }
    }
}
