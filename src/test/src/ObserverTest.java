import event.HelloWorldEvent;
import observer.HelloWorldEventListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
}
