package register;

import event.EventBase;
import event.HelloWorldEvent;
import observer.HelloWorldEventListener;
import observer.IEventListener;

import java.util.*;

public class EventListenerRegister <T extends EventBase, E extends IEventListener>{

    private final Map<Class<T>, Set<Class<E>>> registerMap = new HashMap<>();

    public EventListenerRegister() {
        register((Class<T>) HelloWorldEvent.class, (Class<E>) HelloWorldEventListener.class);
    }

    private void register(Class<T> eventType, Class<E> listenerType) {
        registerMap.putIfAbsent(eventType, new HashSet<>());
        registerMap.get(eventType).add(listenerType);
    }

    public Set<Class<E>> getListenerTypes(EventBase event) {
        return registerMap.get(event.getClass());
    }
}
