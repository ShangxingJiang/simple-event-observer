import event.GenerateFileEvent;
import event.HelloWorldEvent;
import observer.GenerateFileEventListener;
import observer.HelloWorldEventListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class ObserverTest {

    @Test
    void HelloWordEventListener_can_execute_and_print_hello_world() {
        ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outSpy));

        var event = new HelloWorldEvent();
        var listener = new HelloWorldEventListener();
        listener.execute(event);

        Assertions.assertEquals("hello, world\r\n", outSpy.toString());
    }

    @Test
    void GenerateFileEventListener_can_execute_and_generate_file() throws FileNotFoundException {
        var event = new GenerateFileEvent();
        event.setFileName("test.txt");
        event.setContent("some content");

        var listener = new GenerateFileEventListener();
        listener.execute(event);

        var filePath = String.format("../../out/%s", event.getFileName());
        var inputStream = new FileInputStream(filePath);
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals("some content\n", resultStringBuilder.toString());
    }
}
