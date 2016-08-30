package com.android.lala.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.lala.home.MainActivity;
import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.base.BaseActivity;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.utils.CommUtils;

import java.util.HashMap;

public class SetingPwdActivity extends BaseActivity {

    private ImageView cross;
    private EditText et_password;
    private EditText et_aglign_password;
    private Button btn_finish;

    private HttpListener<String> httpListener;

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_seting_pwd);
        cross = findView(R.id.cross);
        et_aglign_password = findView(R.id.et_aglign_password);
        et_password = findView(R.id.et_password);
        btn_finish = findView(R.id.btn_finish);


        VolleyHelper.getInstance().init(this);
        httpListener = new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                // TODO: 逻辑不清楚 先模拟跳转
                if (!TextUtils.isEmpty(response)) {
                    Intent intent = new Intent();
                    intent.setClass(SetingPwdActivity.this, MainActivity.class);
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
                if (!CommUtils.isEnabalePwd(pwd) || !CommUtils.isEnabalePwd(align_pwd)) {
                    showMessageDialog("", getString(R.string.hint_true_pwd));
                    return;
                } else if (!pwd.equals(align_pwd)) {
                    showMessageDialog("", "两次输入密码不一致");
                    return;
                }
                HashMap<String, String> paramers = new HashMap<String, String>();
                String mPhoneNumber = getIntent().getStringExtra("mPhoneNumber");
                paramers.put("name", mPhoneNumber);
                paramers.put("pw", pwd);
                VolleyHelper.getInstance().add(SetingPwdActivity.this, "register", HttpWhatContacts.REGISTER, ApiContacts.USER_REGISTER, httpListener, paramers, true);
            }
        });

    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }

    @Override
    public boolean isSamulation() {
        return true;
    }

    @Override
    public String getJsonStrName() {
        return "register.json";
    }
}
