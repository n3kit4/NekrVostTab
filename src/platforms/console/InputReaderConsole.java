package platforms.console;

import logic.Request;

import java.util.Scanner;

public class InputReaderConsole implements InputReader {
    private final Scanner scanner;
    private final long chatId; // Фиксированный chatId для консольного бота

    public InputReaderConsole(long chatId) {
        this.scanner = new Scanner(System.in);
        this.chatId = chatId; // Присваиваем chatId
    }

    @Override
    public Request read() {
        String input = scanner.nextLine();
        return new Request(input, chatId); // Отправляем в Request вместе с chatId
    }
}
