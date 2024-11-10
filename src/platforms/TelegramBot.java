package platforms;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import logic.Request;
import logic.OutputWriterTelegram;
import logic.RequestHandlerTelegram;

import java.io.InputStream;
import java.util.Properties;

public class TelegramBot extends TelegramLongPollingBot {

    private final Properties config;
    private final RequestHandlerTelegram requestHandler;

    public TelegramBot() {
        config = new Properties();
        requestHandler = new RequestHandlerTelegram();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Не удается найти файл config.properties");
            }
            config.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при загрузке конфигурации", e);
        }
    }

    @Override
    public String getBotUsername() {
        return config.getProperty("BOT_USERNAME");
    }

    @Override
    public String getBotToken() {
        return config.getProperty("BOT_TOKEN");
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            // Создаем объект запроса и обработчик
            Request request = new Request(messageText);
            OutputWriterTelegram outputWriter = new OutputWriterTelegram(this, chatId);

            // Передаем запрос в RequestHandler для обработки
            requestHandler.handle(request, outputWriter);
        }
    }
}
