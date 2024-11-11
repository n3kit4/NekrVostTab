package logic;

public class RequestHandlerTelegram implements RequestHandler {

    @Override
    public void handle(Request request, OutputWriter outputWriter) {
        // Получаем текст сообщения
        String text = request.getMessage();

        // Проверяем, является ли сообщение командой /start
        if (text.equals("/start")) {
            text = "Бот успешно перезапущен. Отправь любое сообщение";
        }

        Response response = new Response(text);

        outputWriter.write(response);
    }
}
