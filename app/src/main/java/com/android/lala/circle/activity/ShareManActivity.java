package com.android.lala.circle.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.android.lala.R;
import com.android.lala.base.BaseActivity;
import com.android.lala.circle.adapter.ShareManAdapter;
import com.android.lala.http.listener.HttpListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZH on 2016/9/28.
 * 497239511@qq.com
 */
public class ShareManActivity extends BaseActivity implements View.OnClickListener{
    private HttpListener<String> httpListener;
    private ListView mListview;
    private ShareManAdapter adapter;
    @Override
    protected void initData() {

    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_shareman);
        mListview=findView(R.id.shareman_listview);
        setTitle("分享达人");
        List<String> data=new ArrayList<>();
        for (int i = 0; i <5 ; i++) {
            data.add(i+"");
        }
        adapter=new ShareManAdapter(data,this);
        mListview.setAdapter(adapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean isShowToolBar() {
        return true;

    }

    @Override
    public void onClick(View v) {

    }
}
