package logic;

import platforms.TelegramBot; // Убедитесь, что импорт указывает на правильный пакет
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class OutputWriterTelegram implements OutputWriter {
    private static final Logger logger = LoggerFactory.getLogger(OutputWriterTelegram.class);
    private final TelegramBot bot; // Экземпляр Telegram бота
    private final Long chatId;     // Идентификатор чата, чтобы отправлять сообщения в нужный чат

    public OutputWriterTelegram(TelegramBot bot, Long chatId) {
        this.bot = bot;
        this.chatId = chatId;
    }

    @Override
    public void write(Response response) {
        // Создаем сообщение для отправки в Telegram
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(response.getMessage()); // Используем getMessage для получения текста ответа

        try {
            // Отправляем сообщение через Telegram бот
            bot.execute(message);
        } catch (TelegramApiException e) {
            // Логируем ошибку, используя logger.error
            logger.error("Ошибка при отправке сообщения в Telegram: {}", e.getMessage(), e);
        }
    }
}