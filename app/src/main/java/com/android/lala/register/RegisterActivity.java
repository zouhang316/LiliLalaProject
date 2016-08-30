package com.android.lala.register;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.lala.R;
import com.android.lala.base.BaseActivity;
import com.android.lala.customview.TimeButton;
import com.android.lala.utils.CommUtils;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private ImageView cross;
    private EditText et_phone;
    private EditText et_code;
    private TimeButton btn_getcode;
    private Button btn_next;
    private Button btn_relogin;
    private Handler mHandler;

    private String mPhoneNumber;

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
        cross = findView(R.id.cross);
        et_phone = findView(R.id.et_phone);
        et_code = findView(R.id.et_code);
        btn_getcode = findView(R.id.btn_getcode);
        btn_next = findView(R.id.btn_next);
        btn_relogin = findView(R.id.btn_relogin);
        initSMSSDK();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                Log.e("event", "event=" + event);
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 短信注册成功后，返回MainActivity,然后提示
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
                        showToastMsg("提交验证码成功");
                        Intent intent = new Intent(RegisterActivity.this,
                                SetingPwdActivity.class);
                        intent.putExtra("mPhoneNumber", mPhoneNumber);
                        startActivity(intent);
                        finish();
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        showToastMsg("验证码已经发送");
                    } else {
                        ((Throwable) data).printStackTrace();
                    }
                } else {
                    showToastMsg("验证码错误");
                }
            }
        };
    }

    private void initSMSSDK() {
        SMSSDK.initSDK(this, getString(R.string.smssdk_key), getString(R.string.smssdk_value));
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = Message.obtain();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                mHandler.sendMessage(msg);
            }
        };
        //注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    protected void initListener() {
        btn_relogin.setOnClickListener(this);
        btn_getcode.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        cross.setOnClickListener(this);
        et_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(et_phone.getText().toString().trim())) {
                    btn_getcode.setEnabled(false);
                } else {
                    btn_getcode.setEnabled(true);
                }
            }
        });
    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        mPhoneNumber = et_phone.getText().toString().trim();
        if (viewId == R.id.btn_getcode) {
            if (TextUtils.isEmpty(mPhoneNumber)) {
                showMessageDialog("", getString(R.string.hint_phone));
                return;
            } else if (!CommUtils.isMobile(mPhoneNumber)) {
                showMessageDialog("", getString(R.string.hint_true_phone));
                return;
            }
            SMSSDK.getVerificationCode("86", mPhoneNumber);
        } else if (viewId == R.id.btn_next) {
            String mCode = et_code.getText().toString().trim();
            if (TextUtils.isEmpty(mPhoneNumber)) {
                showMessageDialog("", getString(R.string.hint_phone));
                return;
            } else if (TextUtils.isEmpty(mCode)) {
                showMessageDialog("", getString(R.string.hint_input_code));
                return;
            }
            SMSSDK.submitVerificationCode("86", mPhoneNumber, mCode);
        } else if (viewId == R.id.btn_relogin) {
            finish();
        } else if (viewId == R.id.cross) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        SMSSDK.unregisterAllEventHandler();
        btn_getcode.onDestroy();
        super.onDestroy();
    }
}
