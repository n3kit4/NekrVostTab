package logic;

import platforms.TelegramBot;

public class OutputWriterTelegram implements OutputWriter {
    private final TelegramBot bot;
    private final Long chatId;

    public OutputWriterTelegram(TelegramBot bot, Long chatId) {
        this.bot = bot;
        this.chatId = chatId;
    }

    @Override
    public void write(Response response) {
        String text = response.getMessage();
        bot.sendMessage(chatId, text);
    }
}