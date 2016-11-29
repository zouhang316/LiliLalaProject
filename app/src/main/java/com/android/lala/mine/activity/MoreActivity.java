package com.android.lala.mine.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.base.BaseActivity;
import com.android.lala.utils.PreferenceManager;

/**
 * Created by Administrator on 2016/9/14.
 */
public class MoreActivity extends BaseActivity implements View.OnClickListener{
    private LinearLayout pesoninfo,updatepwd,agreement,currentversion,contactus,signout;
    private String versionName;
    private TextView mVersionName;
    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_more);
        pesoninfo=findView(R.id.personinfo);
        updatepwd=findView(R.id.updatepwd);
        agreement=findView(R.id.agreement);
        currentversion=findView(R.id.currentversion);
        contactus=findView(R.id.contactus);
        signout=findView(R.id.signout);
        mVersionName=findView(R.id.visonname);
        mVersionName.setText(versionName);
        setTitle("更多");
    }

    @Override
    protected void initData() {
        try {
             versionName = MoreActivity.this.getPackageManager().getPackageInfo(MoreActivity.this.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initListener() {
        pesoninfo.setOnClickListener(this);
        updatepwd.setOnClickListener(this);
        agreement.setOnClickListener(this);
        contactus.setOnClickListener(this);
        currentversion.setOnClickListener(this);
        signout.setOnClickListener(this);
    }

    @Override
    protected boolean isShowToolBar() {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.personinfo :
                Intent personinfo=new Intent(this, PersonInfoActivity.class);
                startActivity(personinfo);
                break;
            case R.id.updatepwd :
                Intent updatepwd=new Intent(this, UpdatePwdActivity.class);
                startActivity(updatepwd);
                break;
            case R.id.agreement :
                break;
            case R.id.contactus :
                break;
            case R.id.currentversion :
                break;
            case R.id.signout :
                PreferenceManager.getInstance(this).putBoolean("islogin",false);
                finish();
                break;
        }

    }
}
