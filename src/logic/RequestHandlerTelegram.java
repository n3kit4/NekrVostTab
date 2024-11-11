package logic;

public class RequestHandlerTelegram implements RequestHandler {

    @Override
    public void handle(Request request, OutputWriter outputWriter) {
        String text = request.getMessage();

        // Проверка на команду start
        if (text.equals("/start")) {
            text = "Бот успешно перезапущен. Отправь любое сообщение";
        }

        Response response = new Response(text);

        outputWriter.write(response);
    }
}
