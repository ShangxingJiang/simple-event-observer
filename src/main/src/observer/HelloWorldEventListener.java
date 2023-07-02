package observer;

import event.HelloWorldEvent;

public class HelloWorldEventListener implements IEventListener<HelloWorldEvent> {

    @Override
    public void execute(HelloWorldEvent event) {
        System.out.println("hello, world");
    }


}
