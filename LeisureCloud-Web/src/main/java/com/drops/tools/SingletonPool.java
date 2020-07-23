package com.drops.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xiaobobo on 8/11/18.
 */
public class SingletonPool {


    private static HashMap<String,Object> dataPool = new HashMap<String,Object>();
    public static List<String> nodePool = new ArrayList<String>();
    public static List<String> getwayPool = new ArrayList<String>();
    public static HashMap<String,Object> currentNodePool = new HashMap<String,Object>();
    public static HashMap<String,Object> currentGetwayPool = new HashMap<String,Object>();
    public SingletonPool() {
    }
    public HashMap<String,Object> getChannels() {
        return dataPool;
    }
    public static SingletonPool getStance() {

        return SingletonHolder.mSington;

    }

    private static class SingletonHolder {
        private static final SingletonPool mSington = new SingletonPool();
    }

}