package com.android.lala.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonResultHelper implements Helper {

    private JSONObject objects;

    public JsonResultHelper(String jsonResult) {
        objects = JSON.parseObject(jsonResult);
    }

    public JsonResultHelper(JSONObject jsonResult) {
        if (null == jsonResult) {
            objects = JSON.parseObject("{}");
        } else {
            objects = jsonResult;
        }
    }

    public String getContentByKey(String key) {
        try {
            String result = String.valueOf(objects.get(key));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }

    @Override
    public void put(String key, String value) {
        objects.put(key, value);
    }
}
