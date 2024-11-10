package platforms;

import logic.Request;
import logic.RequestHandlerTelegram;
import logic.OutputWriterTelegram;
import org.telegram.telegrambots.meta.api.objects.Update;

public class InputReaderTelegram {

    private final RequestHandlerTelegram requestHandler;
    private final TelegramBot bot;

    public InputReaderTelegram(TelegramBot bot) {
        this.bot = bot;
        this.requestHandler = new RequestHandlerTelegram();
    }

    public void processUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            // Создаем объект Request с текстом сообщения
            Request request = new Request(messageText);

            // Создаем OutputWriter для отправки ответа в чат
            OutputWriterTelegram outputWriter = new OutputWriterTelegram(bot, chatId);

            // Передаем запрос и OutputWriter в RequestHandlerTelegram для обработки
            requestHandler.handle(request, outputWriter);
        }
    }
}
