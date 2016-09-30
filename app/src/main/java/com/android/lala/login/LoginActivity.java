package com.android.lala.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.article.activity.ArticleActivity;
import com.android.lala.base.BaseActivity;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.forgetpassword.ForgetPassword;
import com.android.lala.home.MainActivity;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.login.bean.UserBean;
import com.android.lala.register.RegisterActivity;
import com.android.lala.utils.CommUtils;
import com.android.lala.utils.LalaLog;
import com.android.lala.utils.PreferenceManager;

import java.util.HashMap;
import java.util.List;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText mUserName;
    private EditText mPassWord;
    private TextView forgotPwd;
    private Button btn_login;
    private Button btn_reg;
    private ImageView iv_cross;
    private String pwd;
    private HttpListener<String> httpListener;
    private String username;

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        VolleyHelper.getInstance().init(this);
        mUserName = findView(R.id.lg_username);
        mPassWord = findView(R.id.lg_password);
        btn_login = findView(R.id.btn_login);
        btn_reg = findView(R.id.btn_reg);
        iv_cross = findView(R.id.cross);
        forgotPwd=findView(R.id.forgotpasswordtext);
    }

    @Override
    protected void initData() {
        commDataDao=new CommDataDaoImpl(this,false,"login.json");

        httpListener = new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                if (response.equals("")){
                    showMessageDialog("登录失败", "账号或密码错误！");
                    return;
                }
                Helper helper = JsonResultUtils.helper(response);
                String user = helper.getContentByKey("user");
                List<UserBean> userBeanList = FastJsonHelper.getObjects(user, UserBean.class);
                UserBean userBean = userBeanList.get(0);
                if (null != userBean) {
                    LalaLog.i("userBean:", userBean.toString());
                    PreferenceManager preferenceManager=PreferenceManager.getInstance(getApplicationContext());
                    preferenceManager.putString("id",userBean.getUserId());
                    preferenceManager.putString("phone",userBean.getUsername());
                    preferenceManager.putString("name",userBean.getName());
                    preferenceManager.putString("photo",userBean.getPhoto());
                    preferenceManager.putBoolean("islogin",true);
//                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
//                    startActivity(intent);
                    finish();
                } else {
                    showMessageDialog("登录失败", "解析数据错误！");
                }
            }
            @Override
            public void onFail(String errMsg) {

                showMessageDialog("提示", errMsg);
            }
        };
    }

        @Override
    protected void initListener() {
        btn_login.setOnClickListener(this);
        btn_reg.setOnClickListener(this);
        iv_cross.setOnClickListener(this);
        forgotPwd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        Intent intent = new Intent();
        if (viewId == R.id.btn_login) {
            doLogin();
        } else if (viewId == R.id.btn_reg) {
            intent.setClass(this, RegisterActivity.class);
            startActivity(intent);
        } else if (viewId == R.id.cross) {
            finish();
        }else if (viewId==R.id.forgotpasswordtext){
            Intent intent1=new Intent(this, ForgetPassword.class);
            startActivity(intent1);
            finish();
        }
    }

    private void doLogin() {
        username = mUserName.getText().toString().trim();
        pwd = mPassWord.getText().toString().trim();
        if (!CommUtils.isMobile(username)) {
            showMessageDialog("提示", "请输入正确的手机号码！");
            return;
        }
        HashMap<String, String> paramers = new HashMap<>();
        paramers.put("name", username);
        paramers.put("pw", pwd);
        VolleyHelper.getInstance().add(commDataDao, this, HttpWhatContacts.LOGIN, ApiContacts.USER_LOGIN, httpListener, paramers, false);
    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }

}
