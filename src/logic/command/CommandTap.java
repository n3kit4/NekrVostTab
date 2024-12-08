package logic.command;

import logic.Request;
import logic.Response;
import logic.TapperService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandTap implements Command {
    private static final Pattern pattern = Pattern.compile("/tap");
    private final TapperService tapperService;

    public CommandTap(TapperService tapperService) {
        this.tapperService = tapperService;
    }

    @Override
    public Pattern getCommandPattern() {
        return pattern;
    }

    @Override
    public Response executeCommand(Request request, Matcher matcher) {
        tapperService.incrementBalance(request.getChatId());
        return new Response("Ваш баланс пополнился на 1 монету!");
    }
}
