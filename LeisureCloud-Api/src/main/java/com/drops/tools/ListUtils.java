package com.drops.tools;

import com.drops.tools.ListUtilsHook;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;


public final class ListUtils {
    public static <T> List<T> filter(List<T> list, ListUtilsHook<T> hook) {
        ArrayList<T> r = new ArrayList<>();
        for (T t : list) {
            if (hook.test(t)) {
                r.add(t);
            }
        }
        r.trimToSize();
        return r;
    }

    public static <T> List<LinkedHashMap<String, Object>> EntityConvertMap(List<T> list) {
        List<LinkedHashMap<String, Object>> l = new LinkedList<>();
        try {
            for (T t : list) {
                LinkedHashMap<String, Object> map = new LinkedHashMap<>();
                Method[] methods = t.getClass().getMethods();
                for (Method method : methods) {
                    if (method.getName().startsWith("get")) {
                        String name = method.getName().substring(3);
                        name = name.substring(0, 1).toLowerCase() + name.substring(1);
                        Object value = method.invoke(t, new Object[0]);
                        map.put(name, value);
                    }
                }
                l.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
}