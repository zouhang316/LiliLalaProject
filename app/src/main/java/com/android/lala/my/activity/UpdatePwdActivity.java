package com.android.lala.my.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.base.BaseActivity;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.utils.CommUtils;
import com.android.lala.utils.LalaLog;
import com.android.lala.utils.PreferenceManager;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZH on 2016/9/28.
 * 497239511@qq.com
 */
public class UpdatePwdActivity extends BaseActivity implements View.OnClickListener{
    private EditText et_oldPwd;
    private EditText et_newPwd;
    private EditText et_confirmPwd;
    private ImageView back;
    private TextView complete;
    private HttpListener<String> httpListener;
    private String oldpwd,newpwd,confirmpwd;

    @Override
    protected void initData() {
        commDataDao=new CommDataDaoImpl(this,false,"");

        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                LalaLog.i("response",response);
                if(response.equals("")){
                    showToastMsg("旧密码输入错误");
                }else{
                    showToastMsg("修改成功！下次登录使用新密码");
                    finish();
                }
            }

            @Override
            public void onFail(String errMsg) {
                LalaLog.i("msg",errMsg);
            }
        };
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_updatepwd);
        et_oldPwd=findView(R.id.updatepwd_old);
        et_newPwd =findView(R.id.updatepwd_new);
        et_confirmPwd =findView(R.id.updatepwd_confirm);
        back=findView(R.id.back);
        complete=findView(R.id.wancheng);
    }

    @Override
    protected void initListener() {
        complete.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    protected boolean isShowToolBar() {
        return false;

    }

    @Override
    public void onClick(View v) {
        oldpwd=et_oldPwd.getText().toString().trim();
        newpwd=et_newPwd.getText().toString().trim();
        confirmpwd=et_confirmPwd.getText().toString().trim();

     if (v.getId()==R.id.wancheng){
         if (!CommUtils.isEnabalePwd(newpwd) || !CommUtils.isEnabalePwd(confirmpwd)) {
             showMessageDialog("", getString(R.string.hint_true_pwd));
             return;
         } else if (!newpwd.equals(confirmpwd)) {
             showMessageDialog("", "两次输入密码不一致");
             return;
         }
         postDataByVolley();
     }else if(v.getId()==R.id.back){
         finish();
     }

    }
    public void postDataByVolley(){
        String userPhone=PreferenceManager.getInstance(this).getString("phone","");
        HashMap<String,String> paramers =new HashMap<>();
        paramers.put("username", userPhone);
        paramers.put("password",et_oldPwd.getText().toString().trim());
        paramers.put("newpassword", et_newPwd.getText().toString().trim());
        VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.REGISTER, ApiContacts.UPDATE_PWD,httpListener,paramers,false);
    }

}
