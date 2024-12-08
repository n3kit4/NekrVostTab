package logic.command;

import logic.Request;
import logic.Response;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Command {
    Pattern getCommandPattern(); //return "/start"
    // сделать поле, в котором компиллируем регулярку, передаём в гет... Pattern.compile()
    Response executeCommand(Request request, Matcher matcher);
}