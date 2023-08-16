package publisher;

import event.EventBase;
import factory.ListenerFactory;
import observer.IEventListener;
import register.EventListenerRegister;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EventPublisher implements IEventPublisher{
    private final ExecutorService WORKER_THREAD_POOL = Executors.newFixedThreadPool(4);

    private final EventListenerRegister<EventBase, IEventListener> register;

    private final ListenerFactory<IEventListener> listenerFactory;

    public EventPublisher() {
        register = new EventListenerRegister<>();
        listenerFactory = new ListenerFactory<>();
    }

    @Override
    public void publish(EventBase event) {
        var listenerTypes = register.getListenerTypes(event);
        for (var listenerType : listenerTypes) {
            try {
                publishEventToListener(event, listenerType);
            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Future<?>> publishAsync(EventBase event) {
        List<Future<?>> ret = new ArrayList<>();

        var listenerTypes = register.getListenerTypes(event);
        for (var listenerType : listenerTypes) {
            var runnable = new Runnable(){
                @Override
                public void run() {
                    try {
                        publishEventToListener(event, listenerType);
                    } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
            var future = WORKER_THREAD_POOL.submit(runnable);
            ret.add(future);
        }

        return ret;
    }

    private void publishEventToListener(EventBase event, Class<IEventListener> listenerType) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        var listenerObj = listenerFactory.getEventListener(listenerType);
        listenerObj.execute(event);
        System.out.println(Thread.currentThread().getName());
    }

}
