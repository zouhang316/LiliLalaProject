package com.android.lala.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.article.HolderView.LocalImageHolderView;
import com.android.lala.base.BaseFragment;
import com.android.lala.base.adapter.DividerItemDecoration;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.market.activity.MoreCommodityActivity;
import com.android.lala.market.adapter.MarketNewAdapter;
import com.android.lala.market.adapter.MarketOldAdapter;
import com.android.lala.market.bean.MarketBean;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ZH on 2016/9/22.
 * 497239511@qq.com
 */
public class MarketFragment extends BaseFragment {
    private List<MarketBean>newBeanList;
    private ArrayList<MarketBean> oldBeanList;
    private HttpListener<String> httpListener;
    private MarketOldAdapter oldAdapter;
    private MarketNewAdapter newAdapter;
    private RecyclerView oldRecycleview;
    private RecyclerView newRecycleview;
    private ConvenientBanner banner;
    private TextView morecommodity;
    private ArrayList<Integer> pageimagelist;
    @Override
    public void initData(Bundle savedInstanceState) {
        getPageImages();
        commDataDao=new CommDataDaoImpl(getActivity(),false,"");
        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                switch (what){
                    case HttpWhatContacts.GETOLD :
                        Helper helper= JsonResultUtils.helper(response);
                        String data=helper.getContentByKey("old");
                        oldBeanList= (ArrayList<MarketBean>) FastJsonHelper.getObjects(data,MarketBean.class);
                        List<MarketBean> pageList=new ArrayList<>();
                        for (int i = 0; i < 8; i++) {
                            pageList.add(oldBeanList.get(i));
                        }
                        oldAdapter=new MarketOldAdapter(pageList,getContext());
                        oldRecycleview.setAdapter(oldAdapter);
                        break;
                    case HttpWhatContacts.GETNEW:
                        Helper helper2= JsonResultUtils.helper(response);
                        String data2=helper2.getContentByKey("new");
                        newBeanList= FastJsonHelper.getObjects(data2,MarketBean.class);
                        newAdapter=new MarketNewAdapter(newBeanList,getContext());
                        newRecycleview.setAdapter(newAdapter);
                        break;
                }

            }

            @Override
            public void onFail(String errMsg) {

            }
        };


    }

    @Override
    public void initView(View view) {
        banner= (ConvenientBanner) view.findViewById(R.id.market_viewpage);
        morecommodity= (TextView) view.findViewById(R.id.morecommodity);
        oldRecycleview= (RecyclerView) view.findViewById(R.id.oldrecycleview);
        oldRecycleview.setLayoutManager(new GridLayoutManager(getActivity(),2));
        newRecycleview= (RecyclerView) view.findViewById(R.id.newrecycle);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        newRecycleview.setLayoutManager(manager);
        getDataByVolley();
        initViewpage();
        initListener();
    }

    @Override
    public int getFragmentLayoutId() {return R.layout.fragment_leaderboard;}

    public void initListener(){
        morecommodity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId()==R.id.morecommodity){
                    Intent intent=new Intent(getActivity(), MoreCommodityActivity.class);
                    intent.putParcelableArrayListExtra("data",oldBeanList);
                    startActivity(intent);
                }
            }
        });
    }

    public void getDataByVolley(){
        VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.GETOLD, ApiContacts.MARKET_GETOLD,httpListener,new HashMap<String, String>(),false);
        VolleyHelper.getInstance().add(commDataDao,this,HttpWhatContacts.GETNEW,ApiContacts.MARKET_GETNEW,httpListener,new HashMap<String, String>(),false);
    }
    public void initViewpage(){
            banner.setPages(
                new CBViewHolderCreator< LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                },pageimagelist
            ).setPageIndicator(new int[]{R.drawable.ic_page_indicator,R.drawable.ic_page_indicator_selected});
    }
    public void getPageImages(){
        pageimagelist=new ArrayList<>();
        pageimagelist.add(R.drawable.view_dot1);
        pageimagelist.add(R.drawable.view_dot2);
    }

    @Override
    public void onResume() {
        super.onResume();
        banner.startTurning(3000);
    }

    @Override
    public void onPause() {
        super.onPause();
        banner.stopTurning();
    }
}
