package logic;

public class RequestHandlerTelegram implements RequestHandler {

    @Override
    public void handle(Request request, OutputWriter outputWriter) {
        String text = request.getMessage();

        Response response = new Response(text);

        outputWriter.write(response);
    }
}
