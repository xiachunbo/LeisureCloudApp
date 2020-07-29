package com.drops.observer;

import java.util.Observable;

public class WatchManager extends Observable {
    public void notifyObservers(Object arg) {

        /**
         * 为了避免并发冲突，设置了 changed 标志位 changed=true，则当前线程可以通知所有观察者，内部同步块会设置为false；
         * 通知过程中，正在新注册的和撤销的无法通知到
         */
        super.setChanged();

        /**
         * 事件触发，通知所有感兴趣的观察者
         */
        super.notifyObservers(arg);
    }
}
