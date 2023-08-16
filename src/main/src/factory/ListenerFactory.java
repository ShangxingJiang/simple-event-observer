package factory;

import observer.IEventListener;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

// todo: consider multi-threading access
public class ListenerFactory <T extends IEventListener>{

    private final Map<Class<T>, T> listenerMap = new HashMap<>();

    public T getEventListener(Class<T> listenerType) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(listenerMap.containsKey(listenerType))
            return listenerMap.get(listenerType);

        var listenerConstructor = listenerType.getConstructor();
        var listenerObj = listenerConstructor.newInstance();
        listenerMap.put(listenerType, listenerObj);
        return listenerObj;
    }

}
