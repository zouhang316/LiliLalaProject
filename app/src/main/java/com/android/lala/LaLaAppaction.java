package com.android.lala;

import android.app.Application;

import com.android.lala.utils.LalaLog;

import java.util.HashMap;

/**
 * @author sxshi on 2016/7/22.
 * @email:emotiona_xiaoshi@126.com
 * @describe:Describe the function  of the current class
 */
public class LaLaAppaction extends Application {
    public static boolean isLoadLocaldata = true;
    public static boolean isUseHTTPS = false;
    public static boolean SAMULATION = false;
    /**
     * 存储验证码倒计时时间
     */
    public static HashMap<String, Long> longHashMap;

    @Override
    public void onCreate() {
        super.onCreate();
        LalaLog.setDebug(true);
        longHashMap = new HashMap<>();
    }
}
