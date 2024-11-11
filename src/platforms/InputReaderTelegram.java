package platforms;

import org.telegram.telegrambots.meta.api.objects.Update;

public class InputReaderTelegram {
    private final TelegramBot bot;

    public InputReaderTelegram(TelegramBot bot) {
        this.bot = bot;
    }

    public void read(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            // Передача сообщения и chatId для обработки
            bot.handleIncomingMessage(messageText, chatId);
        }
    }
}
