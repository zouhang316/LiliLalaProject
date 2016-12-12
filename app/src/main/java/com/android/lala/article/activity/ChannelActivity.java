package com.android.lala.article.activity;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.article.adapter.ChannelAdapter;
import com.android.lala.article.adapter.InformationArticleAdapter;
import com.android.lala.article.bean.ArticleViewBean;
import com.android.lala.article.bean.ChannelViewBean;
import com.android.lala.base.BaseActivity;
import com.android.lala.base.adapter.DividerItemDecoration;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.utils.LalaLog;
import com.android.lala.view.DemoLoadMoreView;
import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChannelActivity extends BaseActivity {
       private PullToRefreshRecyclerView recyclerView;
       private InformationArticleAdapter adapter;
       private HttpListener httpListener;
       private String id,toppic;
       private ImageView backgrond;
       private List<ArticleViewBean> channelList;
       private ChannelAdapter channelAdapter;
       private List<ArticleViewBean> pageList;
       public static final int LOADMORE=0;
       int curentnum=0;
       int targetnum=10;
       int pagenum=10;

    @Override
    protected void initData() {
        commDataDao=new CommDataDaoImpl(this,false,"");
        getIntentData();
        this.httpListener=new HttpListener() {
            @Override
            public void onSuccess(int what, Object response) {
                LalaLog.i("channel",response+"");
                Helper helper= JsonResultUtils.helper(response.toString());
                String channel=helper.getContentByKey("article");
                channelList = FastJsonHelper.getObjects(channel,ArticleViewBean.class);
                    if (null !=channelList){
                        pageList=new ArrayList<>();
                        if (targetnum>=channelList.size()){
                            targetnum=channelList.size();
                        }
                        for (int i = curentnum; i <targetnum ; i++) {
                            pageList.add(channelList.get(i));
                        }
                        channelAdapter=new ChannelAdapter(pageList,ChannelActivity.this);
                        recyclerView.setAdapter(channelAdapter);
                        recyclerView.onFinishLoading(true,true);
                        setHeadview();
                    }
            }

            @Override
            public void onFail(String errMsg) {}
        };
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("id",id);
        VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.GETARTICLE, ApiContacts.CHANNEL,this.httpListener,paramers,false);
    }

    private void getIntentData() {
        id=getIntent().getStringExtra("id");
        toppic=getIntent().getStringExtra("background");
        setTitle(getIntent().getStringExtra("channelName"));
    }

    @Override
    protected void initListener() {

    }


    @Override
    protected boolean isShowToolBar() {
        return true;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_channel);
        recyclerView=findView(R.id.channalactivity_listview);
        recyclerView.setSwipeEnable(true);
        DemoLoadMoreView loadMoreView = new DemoLoadMoreView(getApplicationContext(), recyclerView.getRecyclerView());
        loadMoreView.setLoadmoreString("加载中");
        loadMoreView.setLoadMorePadding(100);
        recyclerView.setLoadMoreFooter(loadMoreView);
        recyclerView.setLoadmoreString("正在加载中.....");
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //recyclerView.getRecyclerView().addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.setOnRefreshComplete();
                recyclerView.onFinishLoading(true,true);
            }
        });
        recyclerView.setPagingableListener(new PullToRefreshRecyclerView.PagingableListener() {
            @Override
            public void onLoadMoreItems() {
                handler.sendEmptyMessageDelayed(0,1000);
                LalaLog.i("gogo","more");
            }
        });




    }

    private void setHeadview() {
        View view=View.inflate(this, R.layout.head_channel,null);
        backgrond= (ImageView) view.findViewById(R.id.channel_headimg);
        Picasso.with(this).load(toppic).into(backgrond);
        recyclerView.addHeaderView(view);
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==LOADMORE){
                if (channelAdapter.getItemCount()==channelList.size()){
                    //当显示的数量和总数量相等时
                    showToastMsg("没有更多数据了");
                    recyclerView.onFinishLoading(false,false);
                }else {
                loadMore();
                channelAdapter.notifyDataSetChanged();
                recyclerView.onFinishLoading(true,false);
                }
            }
        }
    };

    public void loadMore(){
        int nextpage=targetnum+pagenum;
        if (nextpage>=channelList.size()){
            nextpage=channelList.size();
        }
        for (int i = targetnum; i <nextpage; i++) {
            pageList.add(channelList.get(i));
        }
        targetnum+=pagenum;
    }

}
