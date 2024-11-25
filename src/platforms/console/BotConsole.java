package platforms.console;

import logic.*;
import platforms.Bot;

public class BotConsole implements Bot {
    private final InputReader inputReader;
    private final OutputWriter outputWriter;
    private final RequestHandler requestHandler;

    public BotConsole(InputReader inputReader, RequestHandler requestHandler, OutputWriter outputWriter) {
        this.inputReader = inputReader;
        this.requestHandler = requestHandler;
        this.outputWriter = outputWriter;
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