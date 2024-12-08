import logic.*;
import logic.command.*;
import platforms.console.*;
import platforms.telegram.*;
import logic.balance.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        // Создаем DAO
        BalanceDAO balanceDAO = new BalanceDAOImpl();

        // Создание сервисов
        TapperService tapperService = new TapperService(balanceDAO);

        // Создание списка с объектами команд
        List<Command> commands = Arrays.asList(
                new CommandStart(),
                new CommandBalance(tapperService),
                new CommandTap(tapperService),
                new CommandHelp()
        );

        // Создание и запуск ботов
        long consoleChatId = 12345;
        InputReaderConsole inputReader = new InputReaderConsole(consoleChatId);
        OutputWriterConsole outputWriter = new OutputWriterConsole();
        RequestHandlerCoin requestHandler = new RequestHandlerCoin(new CommandsHandler(commands, balanceDAO));

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
