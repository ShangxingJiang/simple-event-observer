package publisher;

import event.EventBase;
import observer.IEventListener;
import register.EventListenerRegister;

import java.lang.reflect.InvocationTargetException;

public class EventPublisher implements IEventPublisher{

    private final String Execute_Method_Name = "execute";
    private final EventListenerRegister<EventBase, IEventListener> register;

    public EventPublisher() {
        register = new EventListenerRegister<>();
    }

    @Override
    public void publish(EventBase event) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var listenerTypes = register.getListenerTypes(event);
        for (var listenerType : listenerTypes) {
            var listenerConstructor = listenerType.getConstructor();
            var listenerObj = listenerConstructor.newInstance();
            var method = listenerType.getMethod(Execute_Method_Name, event.getClass());
            method.invoke(listenerObj, event);
        }

    }
}
