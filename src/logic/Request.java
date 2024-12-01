package logic;

public class Request {
    private final String message;
    private final long chatId; // Добавляем поле для chatId

    public Request(String message, long chatId) {
        this.message = message;
        this.chatId = chatId;
    }

    public String getMessage() {
        return message;
    }

    public long getChatId() {
        return chatId;
    }
}
