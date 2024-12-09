package logic.command;

import logic.Request;
import logic.Response;
import logic.services.TapperService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandBalance implements Command {
    private static final Pattern pattern = Pattern.compile("/balance");
    private final TapperService tapperService;

    public CommandBalance(TapperService tapperService) {
        this.tapperService = tapperService;
    }

    @Override
    public Pattern getCommandPattern() {
        return pattern;
    }

    @Override
    public Response executeCommand(Request request, Matcher matcher) {
        int balance = tapperService.getBalance(request.getChatId());
        return new Response("Ваш текущий баланс: " + balance + " монет.");
    }
}
