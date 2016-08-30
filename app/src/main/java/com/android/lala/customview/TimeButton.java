package com.android.lala.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android.lala.LaLaAppaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/***
 * @author sxshi
 */
public class TimeButton extends Button implements OnClickListener {
    private long lenght = 30 * 1000;
    private String textafter = "重新获取";
    private String textbefore = "获取验证码";
    private final String TIME = "time";
    private final String CTIME = "ctime";
    private OnClickListener mOnclickListener;
    private Timer t;
    private TimerTask tt;
    private long time;
    Map<String, Long> map = new HashMap<String, Long>();

    public TimeButton(Context context) {
        super(context);
        setOnClickListener(this);

    }

    public TimeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    @SuppressLint("HandlerLeak")
    Handler han = new Handler() {
        public void handleMessage(android.os.Message msg) {
            TimeButton.this.setText(textafter + "("+time / 1000 + ")");
            time -= 1000;
            if (time < 0) {
                TimeButton.this.setEnabled(true);
                TimeButton.this.setText(textbefore);
                clearTimer();
            }
        }

        ;
    };

    private void initTimer() {
        time = lenght;
        t = new Timer();
        tt = new TimerTask() {

            @Override
            public void run() {
                han.sendEmptyMessage(0x01);
            }
        };
    }

    private void clearTimer() {
        if (tt != null) {
            tt.cancel();
            tt = null;
        }
        if (t != null)
            t.cancel();
        t = null;
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        if (l instanceof TimeButton) {
            super.setOnClickListener(l);
        } else
            this.mOnclickListener = l;
    }

    @Override
    public void onClick(View v) {
        if (mOnclickListener != null)
            mOnclickListener.onClick(v);
        initTimer();
        this.setText(textafter + "("+time / 1000 + ")");
        this.setEnabled(false);
        t.schedule(tt, 0, 1000);
    }

    public void onDestroy() {
        if (LaLaAppaction.longHashMap == null)
            LaLaAppaction.longHashMap = new HashMap<String, Long>();
        LaLaAppaction.longHashMap.put(TIME, time);
        LaLaAppaction.longHashMap.put(CTIME, System.currentTimeMillis());
        clearTimer();
    }

    public void onCreate(Bundle bundle) {
        if (LaLaAppaction.longHashMap == null)
            return;
        if (LaLaAppaction.longHashMap.size() <= 0)
            return;
        long time = System.currentTimeMillis() - LaLaAppaction.longHashMap.get(CTIME)
                - LaLaAppaction.longHashMap.get(TIME);
        LaLaAppaction.longHashMap.clear();
        if (time > 0)
            return;
        else {
            initTimer();
            this.time = Math.abs(time);
            t.schedule(tt, 0, 1000);
            this.setText(time + textafter);
            this.setEnabled(false);
        }
    }

    public TimeButton setTextAfter(String text1) {
        this.textafter = text1;
        return this;
    }

    public TimeButton setTextBefore(String text0) {
        this.textbefore = text0;
        this.setText(textbefore);
        return this;
    }

    public TimeButton setLenght(long lenght) {
        this.lenght = lenght;
        return this;
    }
}