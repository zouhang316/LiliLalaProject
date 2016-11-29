package com.android.lala.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.article.HolderView.LocalImageHolderView;
import com.android.lala.base.BaseFragment;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.circle.activity.ShareManActivity;
import com.android.lala.circle.adapter.ActionAdapter;
import com.android.lala.circle.adapter.CircleAdapter;
import com.android.lala.circle.bean.ActionBean;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.utils.LalaLog;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sxshi on 2016/8/23.
 * @email:emotiona_xiaoshi@126.com
 * @describe:圈子
 */
public class CircleFragment extends BaseFragment {
    private ConvenientBanner banner;
    private RecyclerView mActionRecycleView;
    private RecyclerView mCircleRecycleview;
    private ScrollView scrollView;
    private HttpListener<String> httpListener;
    private ArrayList<Integer> pageimagelist;
    private List<String> stringList;
    private List<ActionBean> actionBeanList,mydata;
    private ImageView transition;
    private TextView shareMan;
    @Override
    public void initData(Bundle savedInstanceState) {
        getMyData();
        getPageImages();
        getMyImageData();
        commDataDao=new CommDataDaoImpl(getActivity(),false,"register.json");

        httpListener = new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
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
                        Helper helper2= JsonResultUtils.helper(response);
                        String data2=helper2.getContentByKey("circle");
                        List<Map<String,String>> mapList=FastJsonHelper.getKeyMapsList(data2);
                        CircleAdapter circleadapter=new CircleAdapter(getActivity(),mapList);
                        transition.setVisibility(View.GONE);
                        mCircleRecycleview.setAdapter(circleadapter);
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
        shareMan= (TextView) view.findViewById(R.id.shareman);
        transition= (ImageView) view.findViewById(R.id.circle_transition);
        banner= (ConvenientBanner) view.findViewById(R.id.circle_viewpage);
        scrollView= (ScrollView) view.findViewById(R.id.circle_scrollview1);
        //mActionRecycleView= (RecyclerView) view.findViewById(R.id.action_recycleview);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity()){
            @Override
            //禁止Recyclerview 滑动 与Scrollview嵌套时会造成滑动卡顿
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        //mActionRecycleView.setLayoutManager(manager);
        mCircleRecycleview= (RecyclerView) view.findViewById(R.id.circle_recycleview);
        mCircleRecycleview.setFocusable(false);
        mCircleRecycleview.setLayoutManager(manager);
        //scrollView.smoothScrollTo(10,10);
        initViewpage();
        getDataByVolley();
        shareMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //分享达人功能暂时不需要
//                Intent intent=new Intent(getActivity(), ShareManActivity.class);
//                startActivity(intent);
            }
        });

    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_circle;
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

    public void getDataByVolley(){
        //VolleyHelper.getInstance().add(commDataDao,this,HttpWhatContacts.GETNEW,ApiContacts.CIRCLE_ACTION,httpListener,new HashMap<String, String>(),false);
        VolleyHelper.getInstance().add(commDataDao,this,HttpWhatContacts.GETOLD,ApiContacts.CIRCLE_CIRCLE,httpListener,new HashMap<String, String>(),false);
    }
    public void getMyData(){
        mydata=new ArrayList<>();
        for (int i = 0; i <8 ; i++) {
           ActionBean bean=new ActionBean();
            bean.setBackground("http://www.petmrs.com/uploads/allimg/140624/3-140624113644.jpg");
            bean.setId((i+1)+"");
            mydata.add(bean);
        }
    }
    public void getMyImageData(){
        stringList=new ArrayList<>();
        for (int i = 0; i <8 ; i++) {
            stringList.add(i+"");
        }
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
