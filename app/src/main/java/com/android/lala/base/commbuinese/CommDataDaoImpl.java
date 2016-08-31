package com.android.lala.base.commbuinese;

import android.content.Context;
import android.text.TextUtils;

import com.android.lala.utils.CommUtils;
import com.android.lala.utils.LalaLog;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/8/31.
 */
public class CommDataDaoImpl implements CommDataDao{
    private final String TAG =this.getClass().getSimpleName();
    private Context mContext;
    private boolean isSamulation=false;
    private String jsonName;

    public CommDataDaoImpl(Context context,boolean isSamulation,String jsonName) {
        this.mContext=context;
        this.isSamulation=isSamulation;
        this.jsonName=jsonName;
    }

    public String getJsonName() {
        return jsonName;
    }

    public void setJsonName(String jsonName) {
        this.jsonName = jsonName;
    }

    public boolean isSamulation() {
        return isSamulation;
    }

    public void setSamulation(boolean samulation) {
        isSamulation = samulation;
    }

    @Override
    public String getAssData() {
        if (TextUtils.isEmpty(this.jsonName)) {
            LalaLog.e(TAG, "you should override getJsonStrName");
            return null;
        } else {
            String result;
            try {
                //这里使用mContext.getApplicationContext()，获取数据，避免Activity被会收的时候，出现内存泄漏
                InputStream inputStream = mContext.getApplicationContext().getAssets().open(jsonName);
                result = CommUtils.InputStreamToString(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return result;
        }
    }
}
