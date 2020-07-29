package com.drops.listener;


public class EventListenerImpl implements EventListener {
    @Override
    public void handleEvent(EventObject eventObject) {
        eventObject.doEvent();
        if (eventObject.getSource().equals("closeWindow")) {
            System.out.println("doClose"); // 回调
        }
    }
}
