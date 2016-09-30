package com.android.lala.forgetpassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.base.BaseActivity;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.login.LoginActivity;
import com.android.lala.utils.CommUtils;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/9/13.
 */
public class ForgetSetPwd extends BaseActivity implements View.OnClickListener{
    private HttpListener<String> httpListener;
    private EditText et_password,et_aglign_password;
    private ImageView cross;
    private Button btn_finish;
    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_seting_pwd);
        findView(R.id.reg_name_icon).setVisibility(View.GONE);
        findView(R.id.reg_name_edit).setVisibility(View.GONE);
        findView(R.id.mmm).setVisibility(View.GONE);
        et_aglign_password = findView(R.id.et_aglign_password);
        et_password = findView(R.id.et_password);
        cross = findView(R.id.cross);
        btn_finish = findView(R.id.btn_finish);
        btn_finish.setText("完成修改");
    }

    @Override
    protected void initData() {
        commDataDao=new CommDataDaoImpl(this,false,"");
        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                showToastMsg("修改成功，下次使用新密码登录");
                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFail(String errMsg) {

            }
        };


    }

    @Override
    protected void initListener() {
        btn_finish.setOnClickListener(this);
        cross.setOnClickListener(this);

    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_finish:
                String pwd = et_password.getText().toString().trim();
                String align_pwd = et_aglign_password.getText().toString().trim();
                if (!CommUtils.isEnabalePwd(pwd) || !CommUtils.isEnabalePwd(align_pwd)) {
                    showMessageDialog("", getString(R.string.hint_true_pwd));
                    return;
                } else if (!pwd.equals(align_pwd)) {
                    showMessageDialog("", "两次输入密码不一致");
                    return;
                }
                HashMap<String,String> paramers =new HashMap<>();
                paramers.put("username",getIntent().getStringExtra("phone"));
                paramers.put("password",et_password.getText().toString().trim());
                VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.REGISTER, ApiContacts.FORGETPASSWPRD_SETPWD,httpListener,paramers,false);
                break;

            case R.id.cross:
                finish();
                break;
        }

    }
}
