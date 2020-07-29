package com.drops.listener;

public class EventDemo {
    public static void main(String[] args) {
        EventSource eventSource = new EventSource(); // 事件源
        eventSource.addListener(new EventListenerImpl());
        //测试监听
        EventObject eventObject = new EventObject("closeWindow"); // 事件对象
        eventSource.notifyListenerEvent(eventObject); // 触发事件
    }
}
