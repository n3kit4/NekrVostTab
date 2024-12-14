package logic.command;

import logic.Request;
import logic.Response;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Command {
    Pattern getCommandPattern();
    Response executeCommand(Request request, Matcher matcher);
}