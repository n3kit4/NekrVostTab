package logic.command;

import logic.Request;
import logic.Response;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Command {
    // Поле для хранения скомпилированной регулярки
    //Pattern commandPattern = Pattern.compile("/start|/balance|/tap|/help");

    Pattern getCommandPattern();
    Response executeCommand(Request request, Matcher matcher);
}