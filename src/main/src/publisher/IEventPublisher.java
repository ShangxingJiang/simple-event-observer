package publisher;

import event.EventBase;

import java.lang.reflect.InvocationTargetException;

public interface IEventPublisher {
    void publish(EventBase event) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
