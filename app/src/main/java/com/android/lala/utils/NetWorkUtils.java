package com.android.lala.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author sxshi on 2016/6/15.
 * @email:emotiona_xiaoshi@126.com
 * @describe:网络监测
 */
public class NetWorkUtils {
    /**
     * 获取网络信息
     *
     * @return
     */
    public static boolean checkNetworkInfo(Context context) {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null) {
            // mobile 3G Data Network
            NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
            if (mobile == NetworkInfo.State.CONNECTED/* || mobile == State.CONNECTING */) {
                return true;
            }
        }
        if (conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null) {
            // wifi
            NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
            if (wifi == NetworkInfo.State.CONNECTED/* || wifi == State.CONNECTING */) {
                return true;
            }
        }
        return false;
    }
}
