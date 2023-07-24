import event.GenerateFileEvent;
import event.HelloWorldEvent;
import org.junit.jupiter.api.Test;
import publisher.EventPublisher;
import publisher.IEventPublisher;

import java.util.concurrent.ExecutionException;

public class PublisherTest {

    private final IEventPublisher publisher = new EventPublisher();

    @Test
    void publish_events_sync() {
        var event1 = new HelloWorldEvent();
        var event2 = new GenerateFileEvent();
        event2.setFileName("test.txt");
        event2.setContent("some content");
        publisher.publish(event1);
        publisher.publish(event2);
    }

    @Test
    void publish_events_async() throws ExecutionException, InterruptedException {
        var event1 = new HelloWorldEvent();
        var event2 = new GenerateFileEvent();
        event2.setFileName("test.txt");
        event2.setContent("some content");
        var res1 = publisher.publishAsync(event2);
        var res2 = publisher.publishAsync(event1);
        // wait for all threads are done
        res1.addAll(res2);
        for(var res : res1) {
            res.get();
        }
    }
}
