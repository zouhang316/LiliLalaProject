package com.android.lala.fastjson;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * fastJson
 * 根据需求返回所需要的数据格式
 *
 * @author xiaoshi
 */
public class FastJsonHelper {

    public static <T> T getObject(String jsonString, Class<T> cls) {
        return JSON.parseObject(jsonString, cls);
    }

    public static <T> List<T> getObjects(String jsonString, Class<T> cls) {
        return JSON.parseArray(jsonString, cls);
    }

    public static List<Map<String, String>> getKeyMapsList(String jsonString) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        list = JSON.parseObject(jsonString, new TypeReference<List<Map<String, String>>>() {
        });
        return list;
    }
}  
