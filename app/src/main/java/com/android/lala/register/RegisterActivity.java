package com.android.lala.register;

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
import com.android.lala.base.BaseActivity;
import com.android.lala.customview.TimeButton;
import com.android.lala.utils.CommUtils;

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



    }


    @Override
    protected void initData() {

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
        } else if (viewId == R.id.btn_next) {
            String mCode = et_code.getText().toString().trim();
            if (TextUtils.isEmpty(mPhoneNumber)) {
                showMessageDialog("", getString(R.string.hint_phone));
                return;
            } else if (TextUtils.isEmpty(mCode)) {
                showMessageDialog("", getString(R.string.hint_input_code));
                return;
            }
        } else if (viewId == R.id.btn_relogin) {
            finish();
        } else if (viewId == R.id.cross) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        btn_getcode.onDestroy();
        super.onDestroy();
    }
}
