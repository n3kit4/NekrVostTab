package logic;

public interface Command {
    String getCommandPattern();
    void executeCommand(Request request);
}

// создаём объекты команд в main, затем в requestHandler новый, при обработке реквеста перебираем команды