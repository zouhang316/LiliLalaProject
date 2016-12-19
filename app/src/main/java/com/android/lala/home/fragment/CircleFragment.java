package com.android.lala.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.article.HolderView.LocalImageHolderView;
import com.android.lala.article.HolderView.NetworkImageHolderView;
import com.android.lala.article.bean.BannerImageBean;
import com.android.lala.base.BaseFragment;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.circle.activity.CircleArticleActivity;
import com.android.lala.circle.adapter.CircleAdapter;
import com.android.lala.circle.adapter.DarenListAdapter;
import com.android.lala.circle.bean.ActionBean;
import com.android.lala.circle.bean.DarenBean;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.utils.LalaLog;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.appsdream.nestrefresh.base.AbsRefreshLayout;
import cn.appsdream.nestrefresh.base.OnPullListener;
import cn.appsdream.nestrefresh.normalstyle.NestRefreshLayout;

/**
 * @author sxshi on 2016/8/23.
 * @email:emotiona_xiaoshi@126.com
 * @describe:圈子
 */
public class CircleFragment extends BaseFragment implements OnPullListener{
    private ConvenientBanner banner;
    private RecyclerView mActionRecycleView;
    private RecyclerView mCircleRecycleview;
    private ScrollView scrollView;
    private HttpListener<String> httpListener;
    private ArrayList<Integer> pageimagelist;
    private List<String> stringList;
    private List<ActionBean> actionBeanList,mydata;
    private ImageView transition;
    private ListView darenList;
    private NestRefreshLayout nestRefreshLayout;
    private List<DarenBean> beanList;
    private DarenListAdapter adapter;
    private ArrayList<String> imageList;
    private int page=1;
    private static final int LOADMORE=1;
    @Override
    public void initData(Bundle savedInstanceState) {
        commDataDao=new CommDataDaoImpl(getActivity(),false,"register.json");
        httpListener = new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                Helper helper= JsonResultUtils.helper(response);
                switch (what){
                    case HttpWhatContacts.GETNEW :
                        //TODO 活动功能暂时不需要
//                        Helper helper= JsonResultUtils.helper(response);
//                        String data=helper.getContentByKey("activity");
//                        actionBeanList= FastJsonHelper.getObjects(data,ActionBean.class);
//                        ActionAdapter adapter=new ActionAdapter(getActivity(),mydata);
//                        mActionRecycleView.setAdapter(adapter);
                        break;
                    case HttpWhatContacts.GETOLD :
                        //圈子分类
                        String data2=helper.getContentByKey("circle");
                        List<Map<String,String>> mapList=FastJsonHelper.getKeyMapsList(data2);
                        CircleAdapter circleadapter=new CircleAdapter(getActivity(),mapList);
                        transition.setVisibility(View.GONE);
                        mCircleRecycleview.setAdapter(circleadapter);
                        break;
                    case HttpWhatContacts.GETUP:
                        String banner=helper.getContentByKey("img_data");
                        List<BannerImageBean>  bannerList= FastJsonHelper.getObjects(banner,BannerImageBean.class);
                        imageList=new ArrayList<>();
                        for (int i = 0; i < bannerList.size(); i++) {
                            imageList.add(bannerList.get(i).getImg());
                        }
                        initBanner();
                        break;
                    case HttpWhatContacts.GETCONTENT:
                        String date=helper.getContentByKey("date");
                        beanList= FastJsonHelper.getObjects(date,DarenBean.class);
                        adapter=new DarenListAdapter(getActivity(),beanList);
                        darenList.setAdapter(adapter);
                        break;
                    case HttpWhatContacts.GETMORE:
                        String moreDate=helper.getContentByKey("date");
                        List<DarenBean> moreList=FastJsonHelper.getObjects(moreDate,DarenBean.class);
                        if (moreList.size()!=0){
                            beanList.addAll(moreList);
                            adapter.notifyDataSetChanged();
                            page+=1;
                            nestRefreshLayout.onLoadFinished();
                        }else {
                            showToast("没有更多数据了");
                            nestRefreshLayout.onLoadAll();
                        }
                        break;
                }
            }

            @Override
            public void onFail(String errMsg) {
                showMessageDialog("提示",errMsg);
            }
        };
        getImageDataByVolley();
    }

    @Override
    public void initView(View view) {
        nestRefreshLayout= (NestRefreshLayout) view;
        nestRefreshLayout.setOnLoadingListener(this);
        darenList= (ListView) view.findViewById(R.id.darenlist);
        transition= (ImageView) view.findViewById(R.id.circle_transition);
        banner= (ConvenientBanner) view.findViewById(R.id.circle_viewpage);
        scrollView= (ScrollView) view.findViewById(R.id.circle_scrollview1);
        //mActionRecycleView= (RecyclerView) view.findViewById(R.id.action_recycleview);
        LinearLayoutManager manager=new GridLayoutManager(getActivity(),2){
            @Override
            //禁止Recyclerview 滑动 与Scrollview嵌套时会造成滑动卡顿
            public boolean canScrollVertically() {
                return false;
            }
        };
        mCircleRecycleview= (RecyclerView) view.findViewById(R.id.circle_recycleview);
        mCircleRecycleview.setFocusable(false);
        mCircleRecycleview.setLayoutManager(manager);
        darenList.setFocusable(false);
        getDarenDataByVolley();
        initListener();
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_circle;
    }

    public void initBanner(){

        banner.setPages(
                new CBViewHolderCreator<NetworkImageHolderView>() {
                    @Override
                    public NetworkImageHolderView createHolder() {
                        return new NetworkImageHolderView();
                    }
                },imageList
        ).setPageIndicator(new int[]{R.drawable.ic_page_indicator,R.drawable.ic_page_indicator_selected});
    }


    public void getDataByVolley(){
        //VolleyHelper.getInstance().add(commDataDao,this,HttpWhatContacts.GETNEW,ApiContacts.CIRCLE_ACTION,httpListener,new HashMap<String, String>(),false);
        VolleyHelper.getInstance().add(commDataDao,this,HttpWhatContacts.GETOLD,ApiContacts.CIRCLE_CIRCLE,httpListener,new HashMap<String, String>(),false);
    }
    private void getDarenDataByVolley(){
        VolleyHelper.getInstance().add(commDataDao,getActivity(), HttpWhatContacts.GETCONTENT, ApiContacts.CIRCLE_GETDAREN,httpListener,new HashMap<String,String>(),false);
    }
    private void getImageDataByVolley(){
        VolleyHelper.getInstance().add(commDataDao,getActivity(), HttpWhatContacts.GETUP, ApiContacts.GETBANNERIMG,httpListener,new HashMap<String, String>(),false);
    }
    private void loadMore(){
        HashMap<String,String > paremers=new HashMap<>();
        LalaLog.i("page",page+"");
        paremers.put("str",page+"");
        VolleyHelper.getInstance().add(commDataDao,getActivity(), HttpWhatContacts.GETMORE, ApiContacts.CIRCLE_GETMORE,httpListener,paremers,false);

    }
    private void initListener(){
        darenList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(getActivity(), CircleArticleActivity.class);
                intent.putExtra("id",beanList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh(AbsRefreshLayout listLoader) {
        nestRefreshLayout.onLoadFinished();
    }

    @Override
    public void onLoading(AbsRefreshLayout listLoader) {
        handler.sendEmptyMessageDelayed(LOADMORE,1000);
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
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                loadMore();
            }
        }
    };
}
