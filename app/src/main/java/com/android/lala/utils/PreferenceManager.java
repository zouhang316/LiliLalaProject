package com.android.lala.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * SharedPreferences的工具类
 * <p>
 * SharedPreferences是Android平台上一个轻量级的存储类，主要是保存一些常用的配置比如窗口状态， 一般在Activity中
 * 重载窗口状态onSaveInstanceState保存一般使用SharedPreferences完成， 它提供了Android平台常规的Long长
 * 整形、Int整形、String字符串型的保存
 * <p>
 * 在Android系统中，这些信息以XML文件的形式保存在 /data/data/PACKAGE_NAME /shared_prefs 目录下
 *
 * @author ssx
 */
public class PreferenceManager {
    private static final String PREFERENCE_NAME = "LalaPreference";
    private static SharedPreferences shareditorPreferences;
    public static Editor editor;
    private static PreferenceManager shareferenceManager;

    private PreferenceManager(Context context) {
        if (shareditorPreferences == null || editor == null) {
            try {
                shareditorPreferences = context.getSharedPreferences(
                        PREFERENCE_NAME, 0);
                editor = shareditorPreferences.edit();
            } catch (Exception e) {
            }
        }
    }

    public static PreferenceManager getInstance(Context context) {
        if (shareferenceManager == null) {
            shareferenceManager = new PreferenceManager(context);
        }
        return shareferenceManager;
    }

    public int getInt(String key, int value) {
        return shareditorPreferences.getInt(key, value);
    }

    public String getString(String key, String value) {
        return shareditorPreferences.getString(key, value);
    }

    public long getLong(String key, long value) {
        return shareditorPreferences.getLong(key, value);
    }

    public boolean getBoolean(String key, boolean flag) {
        return shareditorPreferences.getBoolean(key, flag);
    }

    public void putInt(String key, int value) {
        editor.putInt(key, value).commit();
    }

    public void putString(String key, String value) {
        editor.putString(key, value).commit();
    }

    public void putLong(String key, long value) {
        editor.putLong(key, value).commit();
    }

    public void putBoolean(String key, boolean flag) {
        editor.putBoolean(key, flag).commit();
    }

    public void remove(String key) {
        editor.remove(key).commit();
    }
}
