package logic;

public class RequestHandlerConsole implements RequestHandler {
    @Override
    public void handle(Request request, OutputWriter writer) {
        String message = request.getMessage();
        Response response = new Response("Echo: " + message);
        writer.write(response);
    }
}