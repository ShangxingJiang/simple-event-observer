package observer;


import event.EventBase;

public interface IEventListener <T extends EventBase> {
    void execute(T event);
}
