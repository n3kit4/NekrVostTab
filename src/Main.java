import logic.RequestHandlerEcho;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platforms.console.BotConsole;
import platforms.console.InputReaderConsole;
import platforms.console.OutputWriterConsole;
import platforms.telegram.BotTelegram;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        InputReaderConsole inputReader = new InputReaderConsole();
        OutputWriterConsole outputWriter = new OutputWriterConsole();
        RequestHandlerEcho requestHandler = new RequestHandlerEcho();
        String name;
        String token;

        // Загрузка конфигурации из config.properties
        Properties properties = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                logger.error("Sorry, unable to find config.properties");
                throw new RuntimeException("Configuration file not found");
            }
            properties.load(input);
            name = properties.getProperty("bot.username");
            token = properties.getProperty("bot.token");
        } catch (IOException ex) {
            logger.error("Failed to load bot configuration", ex);
            throw new RuntimeException("Failed to load bot configuration", ex);
        }

        // Создание и запуск консольного бота
        BotConsole consoleBot = new BotConsole(inputReader, requestHandler, outputWriter);
        Thread consoleBotThread = new Thread(consoleBot::startBot, "ConsoleBotThread");

        // Создание и запуск Telegram-бота
        BotTelegram botTelegram = new BotTelegram(token, name, requestHandler);
        Thread telegramBotThread = new Thread(botTelegram::startBot, "TelegramBotThread");

        // Запуск обоих потоков
        consoleBotThread.start();
        telegramBotThread.start();

        // Ожидание завершения потоков (если нужно)
        try {
            consoleBotThread.join();
            telegramBotThread.join();
        } catch (InterruptedException e) {
            logger.error("Thread interrupted", e);
            Thread.currentThread().interrupt();
        }
    }
}

//научить вести диалог с пользователем; pattern: state, commands