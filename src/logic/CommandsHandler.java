package logic;

import logic.balance.BalanceDAO;
import logic.command.Command;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandsHandler {
    private final TapperService tapperService;
    private final List<Command> commands;

    // Конструктор принимает List<Command> и BalanceDAO
    public CommandsHandler(List<Command> commands, BalanceDAO balanceDAO) {
        this.commands = commands;
        this.tapperService = new TapperService(balanceDAO); // Передаем BalanceDAO в TapperService
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
    }
}
