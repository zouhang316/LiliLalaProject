package com.android.lala.utils;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/***
 * @author xiaoshi email:emotiona_xiaoshi@126.com
 * 日志工具类
 * @2015年8月26日
 */
public class LalaLog {

    private LalaLog() {
        /**cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static final String SSX_TAG = "ssx";
    private static boolean DEBUG = false;//打印日志
    private static final String TAG = "TAG";
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final int JSON_INDENT = 4;

    public static void setDebug(boolean debug) {
        LalaLog.DEBUG = debug;
    }

    public static void i(String msg) {
        if (DEBUG)
            Log.i(TAG, getClassInfo() + msg);
    }

    public static void d(String msg) {
        if (DEBUG)
            Log.d(TAG, getClassInfo() + msg);
    }

    public static void w(String msg) {
        if (DEBUG)
            Log.w(TAG, getClassInfo() + msg);
    }

    public static void e(String msg) {
        if (DEBUG)
            Log.e(TAG, getClassInfo() + msg);
    }

    public static void v(String msg) {
        if (DEBUG)
            Log.v(TAG, getClassInfo() + msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (DEBUG)
            Log.i(tag, getClassInfo() + msg);
    }

    public static void d(String tag, String msg) {
        if (DEBUG)
            Log.d(tag, getClassInfo() + msg);
    }

    public static void w(String tag, String msg) {
        if (DEBUG)
            Log.w(tag, getClassInfo() + msg);
    }

    public static void e(String tag, String msg) {
        if (DEBUG)
            Log.e(tag, getClassInfo() + msg);
    }

    public static void v(String tag, String msg) {
        if (DEBUG)
            Log.v(tag, getClassInfo() + msg);
    }

    /***
     * @param tagStr
     * @param json
     */
    public static void json(String tagStr, String json) {
        String msg;
        if (!DEBUG) {
            return;
        } else {
            msg = json;
            String logStr = getClassInfo();

            if (TextUtils.isEmpty(msg)) {
                i(tagStr, "Empty or Null json content");
                return;
            }

            String message = null;

            try {
                if (msg.startsWith("{")) {
                    JSONObject jsonObject = new JSONObject(msg);
                    message = jsonObject.toString(JSON_INDENT);
                } else if (msg.startsWith("[")) {
                    JSONArray jsonArray = new JSONArray(msg);
                    message = jsonArray.toString(JSON_INDENT);
                }
            } catch (JSONException e) {
                e(tagStr, e.getCause().getMessage() + "\n" + msg);
                return;
            }
            printLine(tagStr, true);
            message = logStr + LINE_SEPARATOR + message;
            String[] lines = message.split(LINE_SEPARATOR);
            StringBuilder jsonContent = new StringBuilder();
            for (String line : lines) {
                jsonContent.append("║ ").append(line).append(LINE_SEPARATOR);
            }
            i(tagStr, jsonContent.toString());
            printLine(tagStr, false);

        }
    }

    private static void printLine(String tag, boolean isTop) {
        if (isTop) {
            i(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            i(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

    /***
     * @return获取相关类、方法信息
     */
    private static String getClassInfo() {
        int index = 4;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String className = stackTrace[index].getFileName();
        String methodName = stackTrace[index].getMethodName();
        int lineNumber = stackTrace[index].getLineNumber();
        methodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(").append(className).append(":").append(lineNumber).append(")").append("→");
        return stringBuilder.toString();
    }
}