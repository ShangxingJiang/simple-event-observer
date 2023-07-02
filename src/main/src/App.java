import publisher.EventPublisher;

public class App {
    public static void main(String[] args) {
//        new HelloWorldEventListener().execute(new HelloWorldEvent());

        var publisher = new EventPublisher();
        publisher.publish();

    }
}
