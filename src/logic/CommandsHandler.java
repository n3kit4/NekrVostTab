package logic;

import logic.command.Command;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandsHandler {
    private final TapperService tapperService = new TapperService(); // Создаем объект для управления балансом
    private final List<Command> commands;

    public CommandsHandler(List<Command> commands) {
        this.commands = commands;
    }

    public Response processRequest(Request request) {
        String message = request.getMessage();

        for (Command command : commands) {
            Pattern commandPattern = command.getCommandPattern();
            Matcher matcher = commandPattern.matcher(message);
            if (matcher.matches()) {
                return command.executeCommand(request, matcher);
            }
        }

        return new Response("Неизвестная команда, введите /help для получения списка доступных команд.");

        /*switch (message) {
            case "/start":
                return new Response("Привет! Вас приветствует UrFUCoin бот. Я пока ещё маленький, но скоро стану большим и крутым!");

            case "/balance":
                valueS = "Ваш текущий баланс: " + tapper.getBalance(request.getChatId()) + " монет.";
                return new Response(valueS);

            case "/tap":
                tapper.incrementBalance(request.getChatId()); // Увеличиваем баланс на 1
                valueS = "Ваш баланс пополнился на 1 монету!";
                return new Response(valueS);

            case "/help":
                return new Response("Доступные команды: /start, /balance, /tap, /help");

            default:
                return new Response("Неизвестная команда, введите /help для получения списка доступных команд.");
        }*/
    }

    // можно новый свитч
}
