package platforms.console;

import logic.OutputWriter;
import logic.Response;

public class OutputWriterConsole implements OutputWriter {
    @Override
    public void write(Response response) {
        System.out.println(response.getMessage());
    }
}