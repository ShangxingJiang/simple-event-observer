package observer;

import event.GenerateFileEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateFileEventListener implements IEventListener<GenerateFileEvent> {
    @Override
    public void execute(GenerateFileEvent event) {
        try {
            var filePath = String.format("../../out/%s", event.getFileName());
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(event.getContent());
            writer.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
