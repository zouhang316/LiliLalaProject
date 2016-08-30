package com.android.lala.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.base.BaseFragment;

/**
 * @author sxshi on 2016/8/23.
 * @email:emotiona_xiaoshi@126.com
 * @describe:资讯
 */
public class InformationFragment extends BaseFragment {
    TextView tst;
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView(View view) {
       tst= (TextView) view.findViewById(R.id.testtx);
        tst.setText("Hollo");

    }



    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_information;
    }

}
