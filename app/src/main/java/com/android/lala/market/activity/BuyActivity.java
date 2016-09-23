package com.android.lala.market.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.article.HolderView.NetworkImageHolderView;
import com.android.lala.base.BaseActivity;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.market.bean.CommodityBean;
import com.android.lala.utils.LalaLog;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ZH on 2016/9/23.
 * 497239511@qq.com
 */
public class BuyActivity extends BaseActivity{
    private HttpListener<String> httpListener;
    private ConvenientBanner banner;
    private List<String> networkImages;
    private ArrayList<Integer> pageimagelist;
    private TextView mTitle;
    private TextView price;
    private TextView introduction;

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        //TODO : 评论功能和显示用户清单功能待实现
        setContentView(R.layout.activity_buy);
        banner=findView(R.id.buy_viewpage);
        mTitle=findView(R.id.market_buy_title);
        price=findView(R.id.market_buy_price);
        introduction=findView(R.id.market_buy_jianjie);
        getDataByVolley();


    }

    @Override
    protected void initData() {

        getPageImages();
        commDataDao=new CommDataDaoImpl(this,false,"");
        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                switch (what){
                    case HttpWhatContacts.GETCONTENT:
                        Helper helper= JsonResultUtils.helper(response);
                        String data=helper.getContentByKey("product");
                        LalaLog.i("paramers",data);
                        CommodityBean bean= FastJsonHelper.getObject(data,CommodityBean.class);
                        mTitle.setText(bean.getCom_name());
                        price.setText("¥"+bean.getPrice());
                        introduction.setText(bean.getIntroduction());
                        networkImages=new ArrayList<>();
                        networkImages.add(bean.getShowcase_img1());
                        networkImages.add(bean.getShowcase_img2());
                        networkImages.add(bean.getShowcase_img3());
                        initViewpage();
                        break;
                }

            }

            @Override
            public void onFail(String errMsg) {

            }
        };

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }
    public void initViewpage(){
        LalaLog.i("networkimages",networkImages.toString());
        banner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        },networkImages).setPageIndicator(new int[]{R.drawable.ic_page_indicator,R.drawable.ic_page_indicator_selected});
    }

    //
    public void getPageImages(){
        pageimagelist=new ArrayList<>();
        pageimagelist.add(R.drawable.view_dot1);
        pageimagelist.add(R.drawable.view_dot2);
    }
    public void getDataByVolley(){
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("id",getIntent().getStringExtra("id"));
        VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.GETCONTENT, ApiContacts.BUY_GETCONTENT,httpListener,paramers,false);
    }
}
