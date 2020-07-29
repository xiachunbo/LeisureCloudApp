package com.drops.listener;

public class EventObject extends java.util.EventObject {

    public EventObject(Object source) {
        super(source);
    }

    public void doEvent() {
        System.out.println("通知一个事件源 source:" + this.getSource());
    }
}

