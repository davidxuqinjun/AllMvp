package com.david.mvpframework.utils;


import com.google.gson.Gson;

/**
 *
 */
public class JsonUtil {
    /**
     * 从java对象转换为字符串
     */
    public static String dto2String(Object dto) {
        Gson gson = new Gson();
        return gson.toJson(dto);//Gson.toJSONString(dto);
    }

    /**
     * 从字符串转换为java对象
     */
    public static <T> T  string2Obejct(String jsonStr, Class<T> c) {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr,c);
    }

  /*  *//**
     * 将Json生成集合
     *//*
    public static <T> List<T> createList(String json, Class<T> clazz) {
        List<T> list = new ArrayList<T>();
        list = JSON.parseArray(json, clazz);
        return list;
    }

    *//**
     * 将json生成listMap
     *//*
    public static <T> List<Map<String, T>> createListMap(String json, Class<T> clazz) {
        List<Map<String, T>> list = new ArrayList<Map<String, T>>();
        list = JSON.parseObject(json, new TypeReference<List<Map<String, T>>>() {
        });
        return list;
    }*/
}
