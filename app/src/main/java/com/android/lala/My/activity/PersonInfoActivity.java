package com.android.lala.My.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.android.lala.R;
import com.android.lala.base.BaseActivity;

/**
 * Created by Administrator on 2016/9/14.
 */
public class PersonInfoActivity extends BaseActivity implements View.OnClickListener{
    private RelativeLayout headview,nameview,phoneview;
    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_personinfo);
        headview=findView(R.id.personinfo_headview);
        nameview=findView(R.id.personinfo_nameview);
        phoneview=findView(R.id.personinfo_phoneview);
        setTitle("个人信息");

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        headview.setOnClickListener(this);
        nameview.setOnClickListener(this);
        phoneview.setOnClickListener(this);

    }

    @Override
    protected boolean isShowToolBar() {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.personinfo_headview:
                break;
            case R.id.personinfo_nameview:
                break;
            case R.id.personinfo_phoneview:
                break;
        }

    }
}
