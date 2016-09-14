package com.android.lala.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.base.BaseActivity;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.login.LoginActivity;
import com.android.lala.utils.CommUtils;
import java.util.HashMap;

public class SetingPwdActivity extends BaseActivity {

    private ImageView cross;
    private EditText et_password;
    private EditText et_aglign_password,et_name;
    private Button btn_finish;
    private HttpListener<String> httpListener;

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_seting_pwd);
        cross = findView(R.id.cross);
        et_aglign_password = findView(R.id.et_aglign_password);
        et_password = findView(R.id.et_password);
        et_name=findView(R.id.reg_name_edit);
        btn_finish = findView(R.id.btn_finish);
        btn_finish.setText("完成设置");
    }

    @Override
    protected void initData() {
        commDataDao=new CommDataDaoImpl(this,false,"register.json");
        httpListener = new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                if (!TextUtils.isEmpty(response)) {
                    Intent intent = new Intent();
                    intent.setClass(SetingPwdActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFail(String errMsg) {
                showMessageDialog("", errMsg);
                return;
            }
        };
    }

    @Override
    protected void initListener() {
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = et_password.getText().toString().trim();
                String align_pwd = et_aglign_password.getText().toString().trim();
                String name=et_name.getText().toString().trim();
                if (!CommUtils.isEnabalePwd(pwd) || !CommUtils.isEnabalePwd(align_pwd)) {
                    showMessageDialog("", getString(R.string.hint_true_pwd));
                    return;
                } else if (!pwd.equals(align_pwd)) {
                    showMessageDialog("", "两次输入密码不一致");
                    return;
                }
                HashMap<String, String> paramers = new HashMap<String, String>();
                paramers.put("username",getIntent().getStringExtra("name"));
                paramers.put("name", name);
                paramers.put("pw", pwd);
                VolleyHelper.getInstance().add(commDataDao, "register", HttpWhatContacts.REGISTER, ApiContacts.USER_REGISTER, httpListener, paramers, true);
            }
        });
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    @Override
    protected boolean isShowToolBar() {
        return false;
    }
}
