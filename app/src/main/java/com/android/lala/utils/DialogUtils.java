package com.android.lala.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.lala.R;
import com.android.lala.base.BaseActivity;

/**
 * @author sxshi on 2016/6/15.
 * @email:emotiona_xiaoshi@126.com
 * @describe:dialog and progress and Toast utils
 */
public class DialogUtils {
    // 进度条
    private static ProgressDialog progressDialog;
    private static AlertDialog alertDialog = null;
    /***
     * @param title 标题
     * @param msg  内容
     * @param canceledOnTouchOutside 点击其他区域是否可以关闭
     * @param okListener 点击事件
     */
    public static void showAlertDialog(BaseActivity activity, CharSequence title, CharSequence msg, boolean canceledOnTouchOutside , String buttonMsg, DialogInterface.OnClickListener okListener){
        closeAlertDialog();
        alertDialog=new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(buttonMsg,okListener)//需要用getApplicationContext 如果使用Activity的Context 有可能程序获取资源文件的时候Activity还没创建，就会报错
                .create();
        alertDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    /***
     * 版本更新提示框 三个按钮
     * @param activity
     * @param title
     * @param msg
     * @param leftMsg
     * @param leftListener
     * @param middleMsg
     * @param middleListener
     * @param rightMsg
     * @param rightListener
     */
    public static void showAlertDialog(BaseActivity activity,CharSequence title, CharSequence msg, String leftMsg, DialogInterface.OnClickListener leftListener ,String middleMsg,DialogInterface.OnClickListener middleListener,String rightMsg,DialogInterface.OnClickListener rightListener){
        closeAlertDialog();
        alertDialog =new AlertDialog.Builder(activity)
        .setTitle(title)
        .setMessage(msg)
        .setPositiveButton(leftMsg,leftListener)
        .setNeutralButton(middleMsg,middleListener)
        .setNegativeButton(rightMsg,rightListener)
        .create();
        alertDialog.setCancelable(false);
        alertDialog.show();

    }
    /***
     *
     * @param activity
     * @param title
     * @param msg
     * @param canceledOnTouchOutside
     */
    public static void showAlertDialog(BaseActivity activity,CharSequence title, CharSequence msg, boolean canceledOnTouchOutside){
        closeAlertDialog();
        alertDialog=new AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton(activity.getApplicationContext().getString(R.string.dialog_ok),null)//需要用getApplicationContext 如果使用Activity的Context 有可能程序获取资源文件的时候Activity还没创建，就会报错
                .create();
        alertDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        alertDialog.setCancelable(false);
        alertDialog.show();
    }
    /***
     * 两个按钮 确认和取消
     * @param activity
     * @param title
     * @param msg
     * @param leftMsg
     * @param leftListener
     * @param rightMsg
     * @param rightListener
     */
    public static void showAlertDialog(BaseActivity activity,CharSequence title, CharSequence msg, String leftMsg, DialogInterface.OnClickListener leftListener,String rightMsg,DialogInterface.OnClickListener rightListener){
        closeAlertDialog();
        alertDialog=new AlertDialog.Builder(activity)
        .setTitle(title)
        .setMessage(msg)
        .setPositiveButton(leftMsg,leftListener)
        .setNegativeButton(rightMsg,rightListener)
        .create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    /***
     *
     * @param title
     * @param msg
     * @param cancelable
     */
    public static void showProgressDialog(BaseActivity activity,CharSequence title,CharSequence msg,boolean cancelable){
        progressDialog = new ProgressDialog(activity);
        progressDialog.setTitle(title);
        progressDialog.setMessage(msg);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//ProgressDialog.STYLE_SPINNER|ProgressDialog.STYLE_HORIZONTAL
        progressDialog.setCancelable(cancelable);
        progressDialog.setMax(100);
        progressDialog.show();
    }

    public static void closeProgressDialog(){
        if (progressDialog!=null){
            progressDialog.dismiss();
        }
    }

    public static void closeAlertDialog(){
        if (alertDialog!=null){
            alertDialog.dismiss();
        }
    }

    /***
     * Toast
     * @param msg
     */
    public static void showToast( Context context,String msg){
        if (!TextUtils.isEmpty(msg)){
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }
}
