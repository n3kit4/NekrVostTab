package logic;

public class RequestHandlerCoin implements RequestHandler {
    private final CommandsHandler commandsHandler;

    public RequestHandlerCoin(CommandsHandler commandsHandler) {
        this.commandsHandler = commandsHandler;
    }

    @Override
    public void handle(Request request, OutputWriter writer) {
        Response response = commandsHandler.processRequest(request);
        writer.write(response);
    }
}
