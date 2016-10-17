package com.android.lala.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.base.BaseActivity;
import com.android.lala.utils.PreferenceManager;
import com.android.lala.view.CircleImageView;
import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2016/9/14.
 */
public class PersonInfoActivity extends BaseActivity implements View.OnClickListener{
    private RelativeLayout headview,nameview,phoneview;
    private CircleImageView head;
    private TextView name,phone;
    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_personinfo);
        headview=findView(R.id.personinfo_headview);
        nameview=findView(R.id.personinfo_nameview);
        phoneview=findView(R.id.personinfo_phoneview);
        head=findView(R.id.personinfo_head);
        name=findView(R.id.personinfo_name);
        phone=findView(R.id.personinfo_phone);
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
                Intent updatehead=new Intent(this,UpdateHeadActivity.class);
                startActivity(updatehead);
                break;
            case R.id.personinfo_nameview:
                Intent updatename=new Intent(this,UpdateNameActivity.class);
                startActivity(updatename);
                break;
            case R.id.personinfo_phoneview:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        PreferenceManager preferenceManager=PreferenceManager.getInstance(this);
        name.setText(preferenceManager.getString("name",""));
        phone.setText(preferenceManager.getString("phone",""));
        Picasso.with(this).load(preferenceManager.getString("photo","")).into(head);
    }
}
