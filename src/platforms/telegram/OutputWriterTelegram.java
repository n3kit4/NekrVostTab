package platforms.telegram;

import logic.OutputWriter;
import logic.Response;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OutputWriterTelegram implements OutputWriter {
    private static final Logger logger = LoggerFactory.getLogger(OutputWriterTelegram.class);

    private final BotTelegram bot;
    private final Long chatId;

    public OutputWriterTelegram(BotTelegram bot, Long chatId) {
        this.bot = bot;
        this.chatId = chatId;
    }

    public void write(Response response) {
        String text = response.getMessage();
        try {
            bot.execute(SendMessage.builder().chatId(chatId.toString()).text(text).build());
        } catch (TelegramApiException ex) {
            logger.error("Failed to send message to chat ID: " + chatId, ex);
        }
    }

}