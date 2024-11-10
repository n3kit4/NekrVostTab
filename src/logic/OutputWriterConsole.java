package logic;

public class OutputWriterConsole implements OutputWriter {
    @Override
    public void write(Response response) {
        System.out.println(response.getMessage());
    }
}