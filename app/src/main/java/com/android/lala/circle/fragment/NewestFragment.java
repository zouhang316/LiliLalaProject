package com.android.lala.circle.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.base.BaseFragment;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.circle.adapter.NewAdapter;
import com.android.lala.circle.bean.CircleBean;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.utils.LalaLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZH on 2016/9/26.
 * 497239511@qq.com
 */
public class NewestFragment extends BaseFragment {
    private HttpListener<String> httpListener;
    private RecyclerView recyclerView;
    private List<CircleBean> circleBeanList;
    private String sort;
    private ImageView transition;
    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        sort=bundle.getString("sort");
        commDataDao=new CommDataDaoImpl(getActivity(),false,"");
        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                LalaLog.i("response",response);
                List<String> list=new ArrayList<>();
                for (int i = 0; i <8 ; i++) {
                    list.add(i+"");
                }
                Helper helper= JsonResultUtils.helper(response);
                String data=helper.getContentByKey("date");
                circleBeanList= FastJsonHelper.getObjects(data,CircleBean.class);
                LalaLog.i("size",circleBeanList.size()+"");
                NewAdapter adapter=new NewAdapter(getActivity(),circleBeanList);
                recyclerView.setAdapter(adapter);
                transition.setVisibility(View.GONE);
            }

            @Override
            public void onFail(String errMsg) {
                showMessageDialog("提示",errMsg);
            }
        };

    }

    @Override
    public void initView(View view) {
        recyclerView= (RecyclerView) view.findViewById(R.id.newest_recycleview);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        transition= (ImageView) view.findViewById(R.id.circle_transition);
        getDataByVolley();
    }
    public void getDataByVolley(){
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("sort",sort);
        VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.GETCONTENT, ApiContacts.CIRCLE_NEW,httpListener,paramers,false);
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_newest;
    }
}
