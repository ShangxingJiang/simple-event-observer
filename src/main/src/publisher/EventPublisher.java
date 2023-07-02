package publisher;

import event.HelloWorldEvent;
import observer.HelloWorldEventListener;
import observer.IEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventPublisher implements IEventPublisher{

    List<IEventListener> listeners;

    public EventPublisher() {
        this.listeners = new ArrayList<>();
        listeners.add(new HelloWorldEventListener());
    }

    @Override
    public void publish() {
        var event = new HelloWorldEvent();
        for(var listener : listeners) {
            listener.execute(event);
        }
    }
}
