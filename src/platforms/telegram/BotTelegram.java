package platforms.telegram;

import logic.Request;
import logic.RequestHandler;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import platforms.Bot;

public class BotTelegram extends TelegramLongPollingBot implements Bot {
    private final RequestHandler requestHandler;
    private final String name;
    private final String token;

    public BotTelegram(String token, String name, RequestHandler requestHandler) {
        this.token = token;
        this.name = name;
        this.requestHandler = requestHandler;
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            long chatID = update.getMessage().getChatId();

            Request request = new Request(message); //отдельный класс создать для заполнения request
            OutputWriterTelegram writer = new OutputWriterTelegram(this, chatID);
            requestHandler.handle(request, writer);
        }
    }

    @Override
    public void startBot() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
