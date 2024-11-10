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

        // Создаем ответ, адаптированный для Telegram
        Response response = new Response(text);

        // Используем OutputWriter для вывода ответа
        outputWriter.write(response);
    }
}
