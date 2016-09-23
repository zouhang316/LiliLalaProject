package com.android.lala.my.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.base.BaseActivity;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.utils.PreferenceManager;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/9/14.
 */
public class UpdateNameActivity extends BaseActivity  {
    private HttpListener<String> httpListener;
    private EditText name;
    private Button commit;
    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_updatename);
        name=findView(R.id.updatename);
        name.setHint(PreferenceManager.getInstance(this).getString("name",""));
        commit=findView(R.id.updatename_comit);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateName();
            }
        });
        setTitle("修改昵称");
    }

    @Override
    protected void initData() {
        commDataDao=new CommDataDaoImpl(this,false,"");
        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                if (response.equals("")){
                    showToastMsg("该昵称已存在，请重新修改");
                }else {
                    showToastMsg("修改成功");
                    PreferenceManager.getInstance(getApplicationContext()).putString("name",name.getText().toString());
                    finish();
                }
            }

            @Override
            public void onFail(String errMsg) {

            }
        };
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean isShowToolBar() {
        return true;
    }

    private void updateName(){
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("name",name.getText().toString().trim());
        paramers.put("userId",PreferenceManager.getInstance(getApplicationContext()).getString("id",""));
        VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.UPLOADFILE, ApiContacts.UPDATENAME,httpListener,paramers,false);
    }
}
