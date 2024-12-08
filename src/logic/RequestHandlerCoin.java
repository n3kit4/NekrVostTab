package logic;

public class RequestHandlerCoin implements RequestHandler {

    CommandsHandler commandsHandler = new CommandsHandler();
    //private final + конструктор

    @Override
    public void handle(Request request, OutputWriter writer) {
        Response response = commandsHandler.processRequest(request);
        writer.write(response);
    }
}