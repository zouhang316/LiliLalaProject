package com.android.lala.forgetpassword;

import android.content.Intent;
import android.os.Bundle;
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

/**
 * Created by Administrator on 2016/9/13.
 */
public class ForgetPassword extends BaseActivity implements View.OnClickListener{
    private HttpListener<String> httpListener;
    private EditText et_phone;
    private EditText et_code;
    private TimeButton btn_getcode;
    private ImageView cross;
    private Button btn_next;
    private List<Map<String,String >> codelist;
    private String mCode,mPhoneNumber;

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        findView(R.id.btn_relogin).setVisibility(View.GONE);
        et_phone=findView(R.id.et_phone);
        et_code=findView(R.id.et_code);
        btn_getcode=findView(R.id.btn_getcode);
        cross=findView(R.id.cross);
        btn_next=findView(R.id.btn_next);


    }

    @Override
    protected void initData() {
        commDataDao=new CommDataDaoImpl(this,false,"");
        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                if (response.equals("该用户不存在,请重新输入！")){
                    showMessageDialog("",getString(R.string.str_goreg));
                    return;
                }
                Helper helper= JsonResultUtils.helper(response);
                String code=helper.getContentByKey("sms");
                codelist= FastJsonHelper.getKeyMapsList(code);
                LalaLog.i("code",codelist.get(0).get("verifyCode"));
                showToastMsg("验证码已发送");
                btn_getcode.startTimeCount();

            }

            @Override
            public void onFail(String errMsg) {

            }
        };

    }

    @Override
    protected void initListener() {
        cross.setOnClickListener(this);
        btn_getcode.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        et_phone.addTextChangedListener(new TextWatcher() {
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
    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }

    public void getCode(){
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("mobilephone",et_phone.getText().toString().trim());
        VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.GETCODE, ApiContacts.FORGETPASSWORD_GETCODE,httpListener,paramers,false);
    }

    @Override
    public void onClick(View v) {
        mPhoneNumber=et_phone.getText().toString().trim();
        mCode=et_code.getText().toString().trim();
        switch (v.getId()){
            case R.id.btn_getcode:
                if (TextUtils.isEmpty(mPhoneNumber)) {
                    showMessageDialog("", getString(R.string.hint_phone));
                    return;
                } else if (!CommUtils.isMobile(mPhoneNumber)) {
                    showMessageDialog("", getString(R.string.hint_true_phone));
                    return;
                }
                getCode();

                break;

            case R.id.btn_next:
                if (TextUtils.isEmpty(mPhoneNumber)) {
                    showMessageDialog("", getString(R.string.hint_phone));
                    return;
                } else if (TextUtils.isEmpty(mCode)) {
                    showMessageDialog("", getString(R.string.hint_input_code));
                    return;
                }
                judgeCode();
                break;

            case R.id.cross:
                finish();
                break;
        }

    }
    public void judgeCode(){

        String mycode=codelist.get(0).get("verifyCode");
        String myphone=codelist.get(0).get("mobilephone");
        if (!mycode.equals(mCode)){
            showMessageDialog("",getString(R.string.str_code_erro));
            return;
        }
        if (!myphone.equals(mPhoneNumber)){
            showMessageDialog("",getString(R.string.hint_true_phone));
            return;
        }
        Intent intent=new Intent(getApplicationContext(),ForgetSetPwd.class);
        intent.putExtra("phone",myphone);
        startActivity(intent);
        finish();
    }
}
