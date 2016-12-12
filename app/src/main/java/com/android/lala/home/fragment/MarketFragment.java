package com.android.lala.home.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
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
import com.android.lala.market.activity.ClassificationActivity;
import com.android.lala.market.activity.MoreCommodityActivity;
import com.android.lala.market.adapter.MarketNewAdapter;
import com.android.lala.market.adapter.MarketOldAdapter;
import com.android.lala.market.bean.MarketBean;
import com.android.lala.utils.LalaLog;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ZH on 2016/9/22.
 * 497239511@qq.com
 */
public class MarketFragment extends BaseFragment implements View.OnClickListener{
    private List<MarketBean>newBeanList;
    private ArrayList<MarketBean> oldBeanList;
    private HttpListener<String> httpListener;
    private MarketOldAdapter oldAdapter;
    private MarketNewAdapter newAdapter;
    private RecyclerView oldRecycleview;
    private RecyclerView newRecycleview;
    private ConvenientBanner banner;
    private ImageView tiyanguan,zhongchou,haodianyouli,qiandao;
    private TextView morecommodity;
    private ArrayList<Integer> pageimagelist;
    private ScrollView scrollView;
    private ImageView transition;
    @Override
    public void initData(Bundle savedInstanceState) {
        getPageImages();
        commDataDao=new CommDataDaoImpl(getActivity(),false,"");
        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                switch (what){
                    //下部分数据
                    case HttpWhatContacts.GETOLD :
                        Helper helper= JsonResultUtils.helper(response);
                        String data=helper.getContentByKey("old");
                        oldBeanList= (ArrayList<MarketBean>) FastJsonHelper.getObjects(data,MarketBean.class);
                        if(oldBeanList.size()!=0){
                            List<MarketBean> pageList=new ArrayList<>();
                            for (int i = 0; i < 8; i++) {
                                pageList.add(oldBeanList.get(i));
                            }
                            oldAdapter=new MarketOldAdapter(pageList,getContext());
                            oldRecycleview.setAdapter(oldAdapter);
                        }
                        break;
                    //上部分数据
                    case HttpWhatContacts.GETNEW:
                        Helper helper2= JsonResultUtils.helper(response);
                        String data2=helper2.getContentByKey("new");
                        newBeanList= FastJsonHelper.getObjects(data2,MarketBean.class);
                        if (newBeanList!=null){
                            newAdapter=new MarketNewAdapter(newBeanList,getContext());
                            transition.setVisibility(View.GONE);
                            newRecycleview.setAdapter(newAdapter);
                        }
                        break;
                    case HttpWhatContacts.GETUP:

                        break;
                    case HttpWhatContacts.GETDOWN :

                        break;
                }
            }

            @Override
            public void onFail(String errMsg) {
                showMessageDialog("提示",errMsg);
            }
        };
    }

    @Override
    public void initView(View view) {
        transition= (ImageView) view.findViewById(R.id.market_transition);
        banner= (ConvenientBanner) view.findViewById(R.id.market_viewpage);
        tiyanguan= (ImageView) view.findViewById(R.id.tiyanguan);
        zhongchou= (ImageView) view.findViewById(R.id.zhongchou);
        haodianyouli= (ImageView) view.findViewById(R.id.haodianyouli);
        qiandao= (ImageView) view.findViewById(R.id.qiandao);
        scrollView= (ScrollView) view.findViewById(R.id.market_scrollview);
        scrollView.smoothScrollTo(0,0);
        morecommodity= (TextView) view.findViewById(R.id.morecommodity);
        oldRecycleview= (RecyclerView) view.findViewById(R.id.oldrecycleview);
        //屏蔽recycleview焦点
        oldRecycleview.setLayoutManager(new GridLayoutManager(getActivity(),2){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        newRecycleview= (RecyclerView) view.findViewById(R.id.newrecycle);
        newRecycleview.setFocusable(false);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
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
                    if (oldBeanList==null){
                        return;
                    }
                    startActivity(intent);
                }
            }
        });
        tiyanguan.setOnClickListener(this);
        zhongchou.setOnClickListener(this);
        haodianyouli.setOnClickListener(this);
        qiandao.setOnClickListener(this);
    }

    public void getDataByVolley(){
        //默认加载数据
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

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.tiyanguan||v.getId()==R.id.zhongchou||v.getId()==R.id.qiandao){
            showToast("该功能马上上线，敬请期待");
        }if (v.getId()==R.id.haodianyouli){
            //分类跳转
            Intent intent=new Intent(getActivity(), ClassificationActivity.class);
            startActivityForResult(intent,101);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101){
            if (resultCode==Activity.RESULT_OK){
                String tag=data.getStringExtra("tag");
                getClassficationDataB(tag);
            }else if (resultCode==Activity.RESULT_FIRST_USER){
                String tag=data.getStringExtra("tag");
                getClassficationDataA(tag);
            }

        }
    }
    private void getClassficationDataA(String tag){
        if (tag.equals("全部商品")){
            VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.GETOLD, ApiContacts.MARKET_GETOLD,httpListener,new HashMap<String, String>(),false);
            return;
        }
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("screen",tag);
        VolleyHelper.getInstance().add(commDataDao,getActivity(),HttpWhatContacts.GETUP,ApiContacts.CLASSFICATION_ONE,httpListener,paramers,false);
    }

    private void getClassficationDataB(String tag){
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("screen",tag);
        VolleyHelper.getInstance().add(commDataDao,getActivity(),HttpWhatContacts.GETDOWN,ApiContacts.CLASSFICATION_TWO,httpListener,paramers,false);
    }
}
