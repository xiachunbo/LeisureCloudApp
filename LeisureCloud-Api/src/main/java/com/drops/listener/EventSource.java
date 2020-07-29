package com.drops.listener;

import java.util.ArrayList;
import java.util.List;

public class EventSource {

    // 监听器列表，监听器的注册 加入此列表
    private List<EventListener> listeners = new ArrayList<>();

    public void addListener(EventListener eventListener) {
        listeners.add(eventListener);
    }

    public void removeListener(EventListener eventListener) {
        listeners.remove(eventListener);
    }

    public void notifyListenerEvent(EventObject eventObject) {
        for (EventListener eventListener : listeners) {
            eventListener.handleEvent(eventObject);
        }
    }
}
