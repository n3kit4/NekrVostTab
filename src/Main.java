import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platforms.TelegramBot;

public class Main {
    // Инициализация логера
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            // Создаем экземпляр TelegramBotsApi с использованием DefaultBotSession
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBot());

            // Сообщение об успешном запуске
            logger.info("Бот успешно запущен и готов к приему сообщений!");
        } catch (TelegramApiException e) {
            // Логирование ошибки запуска бота
            logger.error("Ошибка при запуске бота: ", e);
        }
    }
}
