package platforms.console;

import logic.*;
import platforms.Bot;
import platforms.InputReader;

public class ConsoleBot implements Bot {
    private final InputReader inputReader;
    //private final InputReader inputReader = new InputReaderConsole();
    private final OutputWriter outputWriter;
    private final RequestHandler requestHandler;

    public ConsoleBot() { // конструктор по умолчанию
        this.inputReader = new InputReaderConsole();
        this.outputWriter = new OutputWriterConsole();
        this.requestHandler = new RequestHandlerConsole();
    }

    @Override
    public void startBot() {
        System.out.println("Welcome to Echo Bot!");
        while (true) {
            Request request = inputReader.read();
            requestHandler.handle(request, outputWriter);
        }
    }
}