package com.android.lala.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.article.HolderView.LocalImageHolderView;
import com.android.lala.article.HolderView.NetworkImageHolderView;
import com.android.lala.article.activity.SearchArticleActivity;
import com.android.lala.article.adapter.FragmentViewPagerAdapter;
import com.android.lala.article.bean.BannerImageBean;
import com.android.lala.article.fragment.BaseListFragment;
import com.android.lala.base.BaseFragment;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
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

/**
 * Created by ZH on 2016/11/10.
 * 497239511@qq.com
 */
public class NewInformationFragment extends BaseFragment implements View.OnClickListener{
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<Fragment> flist;
    private ConvenientBanner banner;
    private ArrayList<Integer> bannerlist;
    private ImageView search;
    private ArrayList<String> imageList;
    private HttpListener<String> httpListener;
    private String [] tags={"黑科技","出行","智能硬件","众筹","医疗教育","娱乐","艺术","家居","商业","餐饮","教育"};
    private String [] titles={"黑科技","出行","智能","众筹","医疗","娱乐","艺术","家居","商业","餐饮","教育"};
    @Override
    public void initData(Bundle savedInstanceState) {
        commDataDao=new CommDataDaoImpl(getActivity(),false,"");
        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                Helper helper= JsonResultUtils.helper(response);
                String data=helper.getContentByKey("img_data");
                List<BannerImageBean>  beanList= FastJsonHelper.getObjects(data,BannerImageBean.class);
                imageList=new ArrayList<>();
                for (int i = 0; i < beanList.size(); i++) {
                    imageList.add(beanList.get(i).getImg());
                }
                initBanner();
            }
            @Override
            public void onFail(String errMsg) {
            }
        };
        getImageDataByVolley();
    }

    @Override
    public void initView(View view) {
        viewPager= (ViewPager) view.findViewById(R.id.newinfomation_viewpager);
        viewPager.setOffscreenPageLimit(4);
        tabLayout= (TabLayout) view.findViewById(R.id.tabs);
        search= (ImageView) view.findViewById(R.id.information_articlesearch);
        search.getBackground().setAlpha(170);
        banner= (ConvenientBanner) view.findViewById(R.id.information_banner);
        initAdapter();
        initListener();
        tabLayout.setupWithViewPager(viewPager);
        //initBanner();
    }
    private void initListener(){
        search.setOnClickListener(this);
    }

    private void initAdapter() {
        flist=new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            BaseListFragment fragment=new BaseListFragment();
            fragment.setTag(tags[i]);
            flist.add(fragment);
        }
        FragmentViewPagerAdapter adapter=new FragmentViewPagerAdapter(getChildFragmentManager(),flist,titles);
        viewPager.setAdapter(adapter);
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


    private void getImageDataByVolley(){
        VolleyHelper.getInstance().add(commDataDao,getActivity(), HttpWhatContacts.GETUP, ApiContacts.GETBANNERIMG,httpListener,new HashMap<String, String>(),false);
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.iinformation;
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
        if (v.getId()==R.id.information_articlesearch){
            Intent intent=new Intent(getActivity(), SearchArticleActivity.class);
            startActivity(intent);
        }
    }
}
