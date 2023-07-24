import event.HelloWorldEvent;
import publisher.EventPublisher;

public class App {
    public static void main(String[] args) {
        var publisher = new EventPublisher();
        publisher.publish(new HelloWorldEvent());
    }
}
