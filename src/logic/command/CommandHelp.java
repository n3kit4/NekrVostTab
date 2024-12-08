package logic.command;

import logic.Request;
import logic.Response;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandHelp implements Command {
    private static final Pattern pattern = Pattern.compile("/help");

    @Override
    public Pattern getCommandPattern() {
        return pattern;
    }

    @Override
    public Response executeCommand(Request request, Matcher matcher) {
        return new Response("Доступные команды: /start, /balance, /tap, /help");
    }
}
