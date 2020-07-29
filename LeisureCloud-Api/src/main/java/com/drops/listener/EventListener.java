package com.drops.listener;


public interface EventListener extends java.util.EventListener {

    /**
     * 事件处理
     */
    void handleEvent(EventObject eventObject);

}
