import logic.RequestHandlerEcho;
import logic.RequestHandlerCoin;
import platforms.console.BotConsole;
import platforms.console.InputReaderConsole;
import platforms.console.OutputWriterConsole;
import platforms.telegram.BotTelegram;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        // Создание объектов для работы
        InputReaderConsole inputReader = new InputReaderConsole();
        OutputWriterConsole outputWriter = new OutputWriterConsole();
        //RequestHandlerEcho requestHandler = new RequestHandlerEcho();
        RequestHandlerCoin requestHandler = new RequestHandlerCoin();

        // Пытаемся загрузить конфигурацию и запустить Telegram-бота
        try {
            Properties properties = loadConfig();
            String name = properties.getProperty("bot.username");
            String token = properties.getProperty("bot.token");

            BotTelegram botTelegram = new BotTelegram(token, name, requestHandler);
            Thread telegramBotThread = new Thread(botTelegram::startBot, "TelegramBotThread");
            telegramBotThread.start();
        } catch (IOException ex) {
            System.err.println("Telegram bot failed to start: " + ex.getMessage());
            System.err.println("Console bot continues to run.");
        }

        // Создание и запуск консольного бота
        BotConsole consoleBot = new BotConsole(inputReader, requestHandler, outputWriter);
        Thread consoleBotThread = new Thread(consoleBot::startBot, "ConsoleBotThread");
        consoleBotThread.start();
    }

    private static Properties loadConfig() throws IOException {
        Properties properties = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("Configuration file 'config.properties' not found");
            }
            properties.load(input);
        }
        return properties;
    }
}