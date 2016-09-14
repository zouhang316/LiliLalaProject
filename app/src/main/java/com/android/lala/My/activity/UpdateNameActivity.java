package com.android.lala.My.activity;

import android.os.Bundle;
import android.widget.EditText;

import com.android.lala.R;
import com.android.lala.base.BaseActivity;

/**
 * Created by Administrator on 2016/9/14.
 */
public class UpdateNameActivity extends BaseActivity  {
    private EditText name;
    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_updatename);
        name=findView(R.id.updatename);
        setTitle("修改昵称");
    }

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
}
