package com.android.lala.config;

/**
 * @author sxshi on 2016/7/22.
 * @email:emotiona_xiaoshi@126.com
 * @describe:load jni
 */
public class LalaConfig {
    public native static String[] stringsFromJNI();
    public static String httpServer;
    static {
        System.loadLibrary("lala_native");
        String[] strings = stringsFromJNI();
        httpServer=strings[0];
    }
}
