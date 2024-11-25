package logic;

public class RequestHandlerEcho implements RequestHandler{

    @Override
    public void handle(Request request, OutputWriter writer) {
        String message = request.getMessage();
        Response response = new Response(message);
        writer.write(response);
    }
}
