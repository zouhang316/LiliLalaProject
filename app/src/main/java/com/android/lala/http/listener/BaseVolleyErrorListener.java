package com.android.lala.http.listener;

import com.android.volley.NetworkError;
import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Author emotiona
 * Email emotiona_xiaoshi@icloud.com
 * Date 2016.07.25
 **/
public class BaseVolleyErrorListener implements Response.ErrorListener {
    @Override
    public void onErrorResponse(VolleyError volleyError) {
        Class volleyErrorClass = volleyError.getClass();
        String message = null;
        if (volleyErrorClass == com.android.volley.ServerError.class) {
            message = "服务不可用，请稍后重试!";
        } else if (volleyErrorClass == com.android.volley.AuthFailureError.class) {
            message = "身份验证未通过，请稍后重试!";
        } else if (volleyErrorClass == com.android.volley.ParseError.class) {
            message = "解析数据错误，请稍后重试!";
        } else if (volleyErrorClass == com.android.volley.TimeoutError.class) {
            message = "网络连接超时，请稍后重试！";
        } else if (volleyError instanceof NetworkError) {
            if (volleyErrorClass == com.android.volley.NoConnectionError.class) {
                message = "无法连接服务器,请检查网络地址是否正确!";
            } else {
                message = "网络连接异常，请检查网络!";
            }
        } else {
            message = "重定向错误，请稍后重试!";
        }
    }
}
