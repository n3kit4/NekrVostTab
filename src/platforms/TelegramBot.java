package platforms;

import logic.OutputWriterTelegram;
import logic.Request;
import logic.RequestHandlerTelegram;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelegramBot extends TelegramLongPollingBot {
    private static final Logger logger = Logger.getLogger(TelegramBot.class.getName());
    private final RequestHandlerTelegram requestHandler;
    private final String botUsername;
    private final String botToken;

    public TelegramBot() {
        this.requestHandler = new RequestHandlerTelegram();

        // Загрузка конфигурации из config.properties
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                logger.severe("Sorry, unable to find config.properties");
                throw new RuntimeException("Configuration file not found");
            }
            properties.load(input);
            this.botUsername = properties.getProperty("bot.username");
            this.botToken = properties.getProperty("bot.token");
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Failed to load bot configuration", ex);
            throw new RuntimeException("Failed to load bot configuration", ex);
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        InputReaderTelegram inputReader = new InputReaderTelegram(this);
        inputReader.read(update);
    }

    public void handleIncomingMessage(String messageText, Long chatId) {
        Request request = new Request(messageText);
        OutputWriterTelegram outputWriter = new OutputWriterTelegram(this, chatId);
        requestHandler.handle(request, outputWriter);
    }

    public void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            logger.log(Level.SEVERE, "Failed to send message to chat ID: " + chatId, e);
        }
    }
}
