package com.drops.observer;

public class WatcherDemo {
    public static void main(String[] args) {
        WatchManager watched = new WatchManager();
        Watcher watcher = new Watcher();
        Watcher1 watcher1 = new Watcher1();
        watched.addObserver(watcher);
        watched.addObserver(watcher1);
        watched.notifyObservers("openWindow");
    }
}

