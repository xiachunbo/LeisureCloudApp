package com.drops.tools;

import java.util.HashMap;
import java.util.Map;

public class MyChannelMap {
    private Map<String, Object> map = new HashMap<>();
    private Object lock = new Object();

    private static MyChannelMap instance = new MyChannelMap();

    public static MyChannelMap getInstance() {
        if (instance == null) {
            instance = new MyChannelMap();
        }
        return instance;
    }
    public void put(String taskId, String name) {
        synchronized (this.lock) {
            this.map.put(taskId, name);
        }
    }


    public Map<String, Object> getMap() { return this.map; }
}
