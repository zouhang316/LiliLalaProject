package com.android.lala.market.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import com.android.lala.R;
import com.android.lala.base.BaseActivity;
import com.android.lala.market.adapter.MarketOldAdapter;
import com.android.lala.market.bean.MarketBean;
import com.android.lala.utils.LalaLog;
import com.android.lala.view.DemoLoadMoreView;
import com.lhh.ptrrv.library.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZH on 2016/9/30.
 * 497239511@qq.com
 */
public class MoreCommodityActivity extends BaseActivity {

    private PullToRefreshRecyclerView mRecycleview;
    private List<MarketBean> beanList;
    private List<MarketBean> pageList;
    private MarketOldAdapter adapter;
    public static final int LOADMORE=0;
    int curentnum=0;
    int targetnum=10;
    int pagenum=10;
    @Override
    protected void initData() {
        LalaLog.i("data",getIntent().getParcelableArrayListExtra("data").toString());
        beanList=getIntent().getParcelableArrayListExtra("data");
        pageList=new ArrayList<>();
        pageList=new ArrayList<>();
        if (targetnum>=beanList.size()){
            targetnum=beanList.size();
        }
        for (int i = curentnum; i <targetnum ; i++) {
            pageList.add(beanList.get(i));
        }
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_morecommodity);
        setTitle("更多好货");
        mRecycleview= findView(R.id.morecommodity_view);
        initPullRecycleView();
        initRecycleviewListener();
        adapter=new MarketOldAdapter(pageList,this);
        mRecycleview.setAdapter(adapter);
        mRecycleview.onFinishLoading(true,true);
    }

    private void initRecycleviewListener() {
        mRecycleview.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRecycleview.setOnRefreshComplete();
                mRecycleview.onFinishLoading(true,true);
            }
        });
        mRecycleview.setPagingableListener(new PullToRefreshRecyclerView.PagingableListener() {
            @Override
            public void onLoadMoreItems() {
                handler.sendEmptyMessageDelayed(0,1000);
                LalaLog.i("gogo","more");
            }
        });
    }

    private void initPullRecycleView() {
        mRecycleview.setSwipeEnable(true);
        DemoLoadMoreView loadMoreView = new DemoLoadMoreView(this, mRecycleview.getRecyclerView());
        loadMoreView.setLoadmoreString("加载中");
        loadMoreView.setLoadMorePadding(100);
        mRecycleview.setLoadMoreFooter(loadMoreView);
        mRecycleview.setLoadmoreString("正在加载中.....");
        mRecycleview.setLayoutManager(new GridLayoutManager(this,2));
    }

    @Override
    protected void initListener() {

    }


    @Override
    protected boolean isShowToolBar() {
        return true;
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==LOADMORE){
                if (adapter.getItemCount()==beanList.size()){
                    //当显示的数量和总数量相等时
                    showToastMsg("没有更多数据了");
                    mRecycleview.onFinishLoading(false,false);
                }else {
                    loadMore();
                    mRecycleview.onFinishLoading(true,false);
                }
            }
        }
    };

    public void loadMore(){
        int nextpage=targetnum+pagenum;
        if (nextpage>=beanList.size()){
            nextpage=beanList.size();
        }
        for (int i = targetnum; i <nextpage; i++) {
            pageList.add(beanList.get(i));
        }
        targetnum+=pagenum;
    }
}
