package com.android.lala.article.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.article.HolderView.LocalImageHolderView;
import com.android.lala.article.adapter.InformationArticleAdapter;
import com.android.lala.article.adapter.InformationZCAdapter;
import com.android.lala.article.adapter.NewInformationAdater;
import com.android.lala.article.bean.ArticleViewBean;
import com.android.lala.article.bean.ZCBean;
import com.android.lala.base.BaseFragment;
import com.android.lala.base.adapter.DividerItemDecoration;
import com.android.lala.base.adapter.PullRecycler;
import com.android.lala.base.adapter.layoutmanager.CustomLinearLayoutManager;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.utils.LalaLog;
import com.android.lala.view.DemoLoadMoreView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.lhh.ptrrv.library.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.appsdream.nestrefresh.base.AbsRefreshLayout;
import cn.appsdream.nestrefresh.base.OnPullListener;
import cn.appsdream.nestrefresh.normalstyle.NestRefreshLayout;
import cn.appsdream.nestrefresh.util.L;

/**
 * Created by ZH on 2016/11/10.
 * 497239511@qq.com
 */
public class BaseListFragment extends BaseFragment implements OnPullListener{
    private String tag;
    private int page=1;
    private int zcPage=2;
    private NestRefreshLayout nestRefreshLayout;
    private HttpListener<String> httpListener;
    private List<ArticleViewBean> articleViewBeanList;
    private List<ZCBean> zcBeanList;
    private NewInformationAdater adapter;
    private InformationZCAdapter zcAdapter;
    private RecyclerView recyclerView;
    private static final int LOADMORE=1;

    @Override
    public void initData(Bundle savedInstanceState) {
        commDataDao=new CommDataDaoImpl(getActivity(),false,"");
        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                Helper helper = JsonResultUtils.helper(response);
                if (tag.equals("众筹")){
                    //如果是众筹Fragment
                    String value=helper.getContentByKey("crowd_funding");
                    switch (what){
                        case HttpWhatContacts.GETCHANNEL :
                            zcBeanList=FastJsonHelper.getObjects(value,ZCBean.class);
                            zcAdapter=new InformationZCAdapter(zcBeanList,getActivity());
                            recyclerView.setAdapter(zcAdapter);
                            break;
                        case HttpWhatContacts.GETCONTENT :
                            List<ZCBean> morelist=FastJsonHelper.getObjects(value,ZCBean.class);
                            if (morelist.size()!=0){
                                zcBeanList.addAll(morelist);
                                zcPage+=1;
                                zcAdapter.notifyDataSetChanged();
                                nestRefreshLayout.onLoadFinished();
                            }else{
                                nestRefreshLayout.onLoadAll();
                                showToast("没有更多数据了");
                            }
                            break;
                    }
                }else {
                    //其他Fragment
                    String value = helper.getContentByKey("article");
                    switch (what){
                        case HttpWhatContacts.GETNEW :
                            articleViewBeanList = FastJsonHelper.getObjects(value, ArticleViewBean.class);
                            adapter = new NewInformationAdater(articleViewBeanList, getActivity());
                            recyclerView.setAdapter(adapter);
                            break;
                        case HttpWhatContacts.GETOLD :
                            List<ArticleViewBean> morelist = FastJsonHelper.getObjects(value, ArticleViewBean.class);
                            LalaLog.i("more", morelist.size()+"");
                            if (morelist.size() != 0){
                                articleViewBeanList.addAll(morelist);
                                LalaLog.i("size", articleViewBeanList.size()+"");
                                page+=1;
                                adapter.notifyDataSetChanged();
                                nestRefreshLayout.onLoadFinished();
                            }else{
                                nestRefreshLayout.onLoadAll();
                                showToast("没有更多数据了");
                            }
                            break;
                    }
                }

            }

            @Override
            public void onFail(String errMsg) {
                if (tag.equals("黑科技")){
                    showMessageDialog("提示",errMsg);
                }
            }
        };
       if (tag.equals("众筹")){
           getZCDataByVolley();
       }else {
           getDataByVolley();
       }

    }

    @Override
    public void initView(View view) {
        nestRefreshLayout= (NestRefreshLayout) view;
        recyclerView= (RecyclerView) view.findViewById(R.id.content_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        nestRefreshLayout.setOnLoadingListener(this);
        LalaLog.i("page",tag+page);
    }

    private void getDataByVolley(){
            HashMap<String,String> map=new HashMap<>();
            map.put("sort",tag);
            VolleyHelper.getInstance().add(commDataDao,getActivity(), HttpWhatContacts.GETNEW,ApiContacts.INFORMATION_ARTICLE_SELETC,httpListener,map,false);
    }
    private void getZCDataByVolley(){
        LalaLog.i("volleyzc","1");
        HashMap<String,String> paremers=new HashMap<>();
        paremers.put("page",page+"");
        VolleyHelper.getInstance().add(commDataDao,getActivity(),HttpWhatContacts.GETCHANNEL,ApiContacts.GETZC,httpListener,paremers,false);
    }
    private void getMoreZCDataByVolley(){
        HashMap<String,String> paremers=new HashMap<>();
        paremers.put("page",zcPage+"");
        VolleyHelper.getInstance().add(commDataDao,getActivity(),HttpWhatContacts.GETCONTENT,ApiContacts.GETZC,httpListener,paremers,false);
    }
    public void getMoreByVolley(){
        HashMap<String,String> map=new HashMap<>();
        map.put("sort",tag);
        map.put("str",page+"");
        VolleyHelper.getInstance().add(commDataDao,getActivity(),HttpWhatContacts.GETOLD,ApiContacts.ARTICLE_GETMORE,httpListener,map,false);
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==LOADMORE){
                if (tag.equals("众筹")){
                    getMoreZCDataByVolley();
                }else {
                    getMoreByVolley();
                }
            }
        }
    };

    @Override
    public int getFragmentLayoutId() {
        return R.layout.newbaselistfragment;
    }

    @Override
    public void onRefresh(AbsRefreshLayout listLoader) {
        nestRefreshLayout.onLoadFinished();
    }

    @Override
    public void onLoading(AbsRefreshLayout listLoader) {
        handler.sendEmptyMessageDelayed(LOADMORE,500);
    }
    public void setTag(String tag){
        this.tag=tag;
    }

    @Override
    public void onResume() {
        super.onResume();
        LalaLog.i("resume",tag);
        page=1;
        zcPage=2;
    }
}
