package com.android.lala.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.base.BaseFragment;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.home.MainActivity;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;

import java.util.HashMap;

/**
 * @author sxshi on 2016/8/23.
 * @email:emotiona_xiaoshi@126.com
 * @describe:圈子
 */
public class CircleFragment extends BaseFragment {
    private HttpListener<String> httpListener;
    @Override
    public void initData(Bundle savedInstanceState) {
        commDataDao=new CommDataDaoImpl(getActivity(),false,"register.json");

        httpListener = new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                // TODO: 逻辑不清楚 先模拟跳转
            }

            @Override
            public void onFail(String errMsg) {
                return;
            }
        };
        HashMap<String, String> paramers = new HashMap<String, String>();
        paramers.put("name", "");
        paramers.put("pw", "");//这样就实现请求了  有啥不懂得嘛？
        // listenre ne还有不会？ 搜嘎 我的意思不知道在哪里写 initdata里是吧？
        VolleyHelper.getInstance().add(commDataDao, "register", HttpWhatContacts.REGISTER, ApiContacts.USER_REGISTER, httpListener, paramers, true);
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_circle;
    }

}
