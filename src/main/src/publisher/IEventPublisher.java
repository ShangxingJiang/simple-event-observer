package publisher;

import event.EventBase;

import java.util.List;
import java.util.concurrent.Future;

public interface IEventPublisher {
    void publish(EventBase event);

    List<Future<?>> publishAsync(EventBase event);
}
