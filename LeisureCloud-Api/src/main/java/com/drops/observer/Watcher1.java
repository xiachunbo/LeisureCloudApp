package com.drops.observer;

import java.util.Observable;
import java.util.Observer;

public class Watcher1 implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        if (arg.toString().equals("openWindow")) {
            System.out.println("打开窗口1");
        }
    }
}
