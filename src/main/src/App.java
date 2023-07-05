import event.HelloWorldEvent;
import publisher.EventPublisher;

import java.lang.reflect.InvocationTargetException;

public class App {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        var publisher = new EventPublisher();
        publisher.publish(new HelloWorldEvent());
    }
}
