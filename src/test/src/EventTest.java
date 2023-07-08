import event.EventBase;
import event.GenerateFileEvent;
import event.HelloWorldEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void should_get_event_id() {
        EventBase event = new HelloWorldEvent();
        EventBase event2 = new GenerateFileEvent();
        Assertions.assertEquals("HelloWorldEvent", event.getId());
        Assertions.assertEquals("GenerateFileEvent", event2.getId());
    }

}
