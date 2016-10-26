package com.android.lala.home.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.article.HolderView.LocalImageHolderView;
import com.android.lala.base.BaseFragment;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.market.adapter.MarketOldAdapter;
import com.android.lala.market.bean.MarketBean;
import com.android.lala.utils.LalaLog;
import com.android.lala.view.DemoLoadMoreView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.lhh.ptrrv.library.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author sxshi on 2016/8/23.
 * @email:emotiona_xiaoshi@126.com
 * @describe:市集
 */
public class MarketFragment_temp1 extends BaseFragment {
    private HttpListener<String> httpListener;
    private List<MarketBean> oldBeanList,tempList;
    private PullToRefreshRecyclerView oldRecycleview;
    private MarketBean marketBean;
    private MarketOldAdapter adapter;
    private ConvenientBanner banner;
    private ArrayList<Integer> pageimagelist;
    public final int LOADMORE=1;
    private int pagenum=20;
    private int currentnum=0;
    private int targetnum=20;

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
                        oldBeanList= FastJsonHelper.getObjects(data,MarketBean.class);
                        tempList=new ArrayList<>();
                        if (targetnum >=oldBeanList.size()){
                            targetnum =oldBeanList.size();
                        }
                        for (int i = currentnum; i < targetnum; i++) {
                            tempList.add(oldBeanList.get(i));
                        }
                        adapter=new MarketOldAdapter(tempList,getContext());
                        oldRecycleview.setAdapter(adapter);
                        oldRecycleview.addHeaderView(initHeadview());
                        oldRecycleview.setEmptyView(View.inflate(getActivity(),R.layout.empty_view,null));
                        oldRecycleview.onFinishLoading(true,false);
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
        oldRecycleview= (PullToRefreshRecyclerView) view.findViewById(R.id.oldrecycleview);
        DemoLoadMoreView loadMoreView=new DemoLoadMoreView(getActivity(),oldRecycleview.getRecyclerView());
        oldRecycleview.setSwipeEnable(true);
        loadMoreView.setLoadMorePadding(100);
        loadMoreView.setLoadmoreString("正在加载中.....");
        oldRecycleview.setLoadMoreFooter(loadMoreView);
        oldRecycleview.setLayoutManager(new GridLayoutManager(getContext(),2));
        setPulltoRefrenshListener();
        getDataByVolley();

    }
    public void setPulltoRefrenshListener(){

        oldRecycleview.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                oldRecycleview.setOnRefreshComplete();
                oldRecycleview.onFinishLoading(true,false);
            }
        });
        oldRecycleview.setPagingableListener(new PullToRefreshRecyclerView.PagingableListener() {
            @Override
            public void onLoadMoreItems() {
                LalaLog.i("state","more");
                mHandler.sendEmptyMessageDelayed(LOADMORE,1000);
                for (int i = 0; i <adapter.getItemCount() ; i++) {
                    LalaLog.i("ppp",tempList.get(i).getCom_name());
                }
            }
        });
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_leaderboard;
    }

    public void getDataByVolley(){
        VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.GETOLD, ApiContacts.MARKET_GETOLD,httpListener,new HashMap<String, String>(),false);
    }


    public void getSumulationData(){
        tempList=new ArrayList<>();
        oldBeanList=new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            marketBean=new MarketBean();
            marketBean.setId(i+"");
            marketBean.setCom_name("奇趣床头灯"+i);
            marketBean.setIntroduction("很实用的奇趣床头灯"+i);
            marketBean.setPrice("111");
            marketBean.setShowcase_img1("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1474353147&di=7e9cffeaac0741e30c00bb87c2f2e392&src=http://www.ixiupet.com/uploads/allimg/150723/8-150H3115611.png");
            oldBeanList.add(marketBean);
    }
        for (int i = currentnum; i <pagenum ; i++) {
            tempList.add(oldBeanList.get(i));
        }

    }
    public void loadMore(){
        int nextpage=targetnum+pagenum;
        if (nextpage>=oldBeanList.size()){
            nextpage=oldBeanList.size();
        }
        for (int i = targetnum; i <nextpage; i++) {
            tempList.add(oldBeanList.get(i));
        }
        targetnum+=pagenum;
    }
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==LOADMORE){
                if (adapter.getItemCount()==oldBeanList.size()){
                    oldRecycleview.onFinishLoading(false,false);
                    showToast("没有更多数据了");
                    LalaLog.i("if","if");
                }else {
                loadMore();
                    LalaLog.i("if","else");
                adapter.notifyDataSetChanged();
                oldRecycleview.onFinishLoading(true,false);
                }
            }

        }
    };
        public View initHeadview(){
           final View view= LayoutInflater.from(getActivity()).inflate(R.layout.head_market,null);
//            banner= (ConvenientBanner) view.findViewById(R.id.market_viewpage);
//            banner.setPages(
//                new CBViewHolderCreator< LocalImageHolderView>() {
//                    @Override
//                    public LocalImageHolderView createHolder() {
//                        return new LocalImageHolderView();
//                    }
//                },pageimagelist
//            ).setPageIndicator(new int[]{R.drawable.ic_page_indicator,R.drawable.ic_page_indicator_selected});
//            banner.startTurning(3000);
            return view;
    }
    public void getPageImages(){
        pageimagelist=new ArrayList<>();
        pageimagelist.add(R.drawable.view_dot1);
        pageimagelist.add(R.drawable.view_dot2);
    }
    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        banner.stopTurning();
    }


}
