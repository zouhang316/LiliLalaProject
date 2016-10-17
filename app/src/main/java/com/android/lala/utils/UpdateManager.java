package com.android.lala.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;

import com.android.lala.api.ApiContacts;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ZH on 2016/10/9.
 * 497239511@qq.com
 */
public class UpdateManager  {
    private Context mContext;
    private int mVersionCode;
    private int versionCode;
    private String updatemsg;
    private File file;
    public static final int UPDATE=1;

    /***
     *
     * @param mContext
     * @param versionCode
     * @param updatemsg
     */

    public UpdateManager(Context mContext, int versionCode, String updatemsg) {
        this.mContext = mContext;
        this.versionCode = versionCode;
        this.updatemsg = updatemsg;
    }

    public void getUpdateInfo(){
        try {

            mVersionCode=mContext.getPackageManager().getPackageInfo(mContext.getPackageName(),0).versionCode;
            if (mVersionCode<versionCode){
                LalaLog.i("update","需要更新");
                    handler.sendEmptyMessage(UPDATE);
            }else{
                    LalaLog.i("update","无需更新");
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATE:
                    showUpdateDialog();
                    break;
            }
        }
    };
    public void showUpdateDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
        builder.setCancelable(false);
        builder.setTitle("版本升级");
        builder.setMessage(updatemsg);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                downLoadApk();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void downLoadApk(){
        final ProgressDialog pd;
        pd=new ProgressDialog(mContext);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMessage("正在下载更新中");
        pd.show();
        new Thread(){
            @Override
            public void run() {
                getFileFromServer(pd);
                installApk(file);
                pd.dismiss();
            }
        }.start();
    }
    public void getFileFromServer(ProgressDialog pd){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            try {
                URL url=new URL(ApiContacts.UPDATE_APK);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(5000);
                pd.setMax(connection.getContentLength());
                InputStream inputStream = connection.getInputStream();
                file=new File(Environment.getExternalStorageDirectory(), "mayixinkong.apk");
                FileOutputStream fos=new FileOutputStream(file);
                BufferedInputStream bis=new BufferedInputStream(inputStream);
                byte [] buffer=new byte[1024];
                int len;
                int total=0;
                while ((len=bis.read(buffer))!=-1){
                    fos.write(buffer,0,len);
                    total+=len;
                    pd.setProgress(total);
                }
                inputStream.close();
                fos.close();
                bis.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            file=null;
        }
    }
    public void installApk(File file){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }

}
