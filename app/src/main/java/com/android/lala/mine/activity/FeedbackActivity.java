package com.android.lala.mine.activity;

import android.os.Bundle;

import com.android.lala.R;
import com.android.lala.base.BaseActivity;

/**
 * Created by ZH on 2016/9/30.
 * 497239511@qq.com
 */
public class FeedbackActivity extends BaseActivity{
    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean isShowToolBar() {
        return true;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
            setContentView(R.layout.activity_feedback);
            setTitle("意见反馈");
    }
}
