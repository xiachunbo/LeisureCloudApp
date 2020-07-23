package com.drops.tools;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;


public class JSONUtil {
    public static <T> Object JSONToObj(String jsonStr, Class<T> obj) {
        T t = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            t = (T) objectMapper.readValue(jsonStr, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }


    public static <T> String objectToJson(T obj) throws JSONException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        String jsonStr = "";
        try {
            jsonStr = mapper.writeValueAsString(obj);
        } catch (IOException e) {
            throw e;
        }
        return (new JSONObject(jsonStr)).toString();
    }
}
