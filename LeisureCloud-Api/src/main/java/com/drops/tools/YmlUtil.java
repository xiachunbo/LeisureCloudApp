package com.drops.tools;

import com.drops.tools.YmlUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;


public class YmlUtil {
  private static Map<String, LinkedHashMap> ymls = new HashMap<>();


     private static ThreadLocal<String> nowFileName = new ThreadLocal<>();


    public static void loadYml(String fileName) {

        nowFileName.set(fileName);

        if (!ymls.containsKey(fileName)) {

            ymls.put(fileName, (new Yaml()).loadAs(YmlUtil.class.getResourceAsStream("/" + fileName), LinkedHashMap.class));
        }
    }


    public static Object getValue(String key) throws Exception {
        String[] keys = key.split("[.]");
        Map ymlInfo = (Map) ((LinkedHashMap) ymls.get(nowFileName.get())).clone();
        for (int i = 0; i < keys.length; i++) {
            Object value = ymlInfo.get(keys[i]);
            if (i < keys.length - 1)
            {
                ymlInfo = (Map) value;
            }
            else {
                if (value == null) {
                    throw new Exception("key不存在");
                }
                return value;
            }

        }
        throw new RuntimeException("不可能到这里的...");
    }


    public static Object getValue(String fileName, String key) throws Exception {
        loadYml(fileName);
        return getValue(key);
    }
}