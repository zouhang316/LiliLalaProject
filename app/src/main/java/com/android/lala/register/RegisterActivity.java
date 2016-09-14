package com.android.lala.register;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.base.BaseActivity;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.customview.TimeButton;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.utils.CommUtils;
import com.android.lala.utils.LalaLog;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private ImageView cross;
    private EditText et_phone;
    private EditText et_code;
    private TimeButton btn_getcode;
    private Button btn_next;
    private Button btn_relogin;
    private String mPhoneNumber,mCode;
    private HttpListener<String> httpListener;
    private List<Map<String,String >> codelist;

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        cross = findView(R.id.cross);
        et_phone = findView(R.id.et_phone);
        et_code = findView(R.id.et_code);
        btn_getcode = findView(R.id.btn_getcode);
        btn_next = findView(R.id.btn_next);
        btn_relogin = findView(R.id.btn_relogin);

    }


    @Override
    protected void initData() {
        commDataDao=new CommDataDaoImpl(this,false,"register.json");
        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                if (response.equals("该用户已存在,请重新输入！")){
                    showMessageDialog("提示","该手机号码已注册，请直接登录");
                    return;
                }
                Helper helper= JsonResultUtils.helper(response);
                String code=helper.getContentByKey("sms");
                codelist=FastJsonHelper.getKeyMapsList(code);
                LalaLog.i("code",codelist.get(0).get("verifyCode"));
                showToastMsg("验证码已发送");
            }

            @Override
            public void onFail(String errMsg) {
                    showToastMsg(errMsg);
            }
        };


    }

    @Override
    protected void initListener() {        et_phone.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String phoneNumber = et_phone.getText().toString().trim();
            if (btn_getcode.isFinish()) {
                if (!CommUtils.isMobile(phoneNumber)) {
                    btn_getcode.setEnabled(false);
                } else {
                    btn_getcode.setEnabled(true);
                }
            }
        }
    });

        btn_relogin.setOnClickListener(this);
        btn_getcode.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        cross.setOnClickListener(this);

    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        mPhoneNumber = et_phone.getText().toString().trim();
        mCode = et_code.getText().toString().trim();
        if (viewId == R.id.btn_getcode) {
            if (TextUtils.isEmpty(mPhoneNumber)) {
                showMessageDialog("", getString(R.string.hint_phone));
                return;
            } else if (!CommUtils.isMobile(mPhoneNumber)) {
                showMessageDialog("", getString(R.string.hint_true_phone));
                return;
            }
            getCode();
            btn_getcode.startTimeCount();
        } else if (viewId == R.id.btn_next) {
            if (TextUtils.isEmpty(mPhoneNumber)) {
                showMessageDialog("", getString(R.string.hint_phone));
                return;
            } else if (TextUtils.isEmpty(mCode)) {
                showMessageDialog("", getString(R.string.hint_input_code));
                return;
            }
            judgeCode();
        } else if (viewId == R.id.btn_relogin) {
            finish();
        } else if (viewId == R.id.cross) {
            finish();
        }
    }
    //获取验证码
    public void getCode(){
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("mobilephone",et_phone.getText().toString().trim());
        VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.GETCODE, ApiContacts.REGISTER_GETCODE,httpListener,paramers,false);
    }
    //点击下一步并判断验证码和手机是否一致
    public void judgeCode(){
        String mycode=codelist.get(0).get("verifyCode");
        String myphone=codelist.get(0).get("mobilephone");
        if (!mycode.equals(mCode)){
            showToastMsg("验证码错误");
            return;
        }
        if (!myphone.equals(mPhoneNumber)){
            showToastMsg("手机号码错误");
            return;
        }
            Intent intent=new Intent(this,SetingPwdActivity.class);
            intent.putExtra("name",myphone);
            startActivity(intent);


    }

    @Override
    protected void onDestroy() {
        btn_getcode.onDestroy();
        super.onDestroy();
    }
}
