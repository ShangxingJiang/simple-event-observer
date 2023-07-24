package observer;

import event.GenerateFileEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateFileEventListener implements IEventListener<GenerateFileEvent> {
    @Override
    public void execute(GenerateFileEvent event) {
        System.out.println("start generate file");
        try {
            Thread.sleep(3000);
            var filePath = String.format("../../out/%s", event.getFileName());
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(event.getContent());
            writer.close();
        }
        catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("finish generate file");
    }
}
