package com.android.lala.market.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.android.lala.login.LoginActivity;
import com.android.lala.login.bean.UserBean;
import com.android.lala.market.adapter.BuyCommentAdaper;
import com.android.lala.market.adapter.BuyHeadAdapter;
import com.android.lala.market.adapter.MarketNewAdapter;
import com.android.lala.market.bean.CommentBean;
import com.android.lala.market.bean.CommodityBean;
import com.android.lala.market.bean.MarketBean;
import com.android.lala.utils.LalaLog;
import com.android.lala.utils.PreferenceManager;
import com.android.lala.view.MyListView;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZH on 2016/9/23.
 * 497239511@qq.com
 */
public class BuyActivity extends BaseActivity implements View.OnClickListener{
    private HttpListener<String> httpListener;
    private ConvenientBanner banner;
    private List<String> networkImages;
    private ArrayList<Integer> pageimagelist;
    private TextView mTitle;
    private TextView price;
    private TextView listnum;
    private TextView introduction;
    private TextView lunxinnum;
    private ImageView star1,star2,star3,star4,star5;
    private RecyclerView recmmendRecycleview;
    private RecyclerView headRecycleview;
    private MyListView commentListview;
    private ImageView transition;
    private Button buy;
    private String links;

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_buy);
        banner=findView(R.id.buy_viewpage);
        mTitle=findView(R.id.market_buy_title);
        price=findView(R.id.market_buy_price);
        transition=findView(R.id.transition_buyactivity);
        introduction=findView(R.id.market_buy_jianjie);
        commentListview=findView(R.id.market_commentview);
        commentListview.setFocusable(false);
        lunxinnum=findView(R.id.market_buy_lunxinnum);
        listnum=findView(R.id.buy_listnum);
        star1=findView(R.id.xindu1);
        star2=findView(R.id.xindu2);
        star3=findView(R.id.xindu3);
        star4=findView(R.id.xindu4);
        star5=findView(R.id.xindu5);
        buy=findView(R.id.buyactivity_buy);
        recmmendRecycleview=findView(R.id.market_buy_recommend);
        recmmendRecycleview.setFocusable(false);
        recmmendRecycleview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        headRecycleview=findView(R.id.market_userhead_recycle);
        headRecycleview.setFocusable(false);
        headRecycleview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
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
                        Helper contentHelper= JsonResultUtils.helper(response);
                        String contentData=contentHelper.getContentByKey("product");
                        LalaLog.i("paramers",contentData);
                        CommodityBean bean= FastJsonHelper.getObject(contentData,CommodityBean.class);
                        mTitle.setText(bean.getCom_name());
                        price.setText("¥"+bean.getPrice());
                        introduction.setText(bean.getIntroduction());
                        showStars(Integer.parseInt(bean.getLevel()));
                        networkImages=new ArrayList<>();
                        networkImages.add(bean.getShowcase_img1());
                        networkImages.add(bean.getShowcase_img2());
                        networkImages.add(bean.getShowcase_img3());
                        links=bean.getLinks();
                        initViewpage();
                        transition.setVisibility(View.GONE);
                        break;
                    case HttpWhatContacts.GETARTICLERECOMMEND :
                        Helper recommendHelper= JsonResultUtils.helper(response);
                        String recommendData=recommendHelper.getContentByKey("recommend");
                        List<MarketBean> newBeanList= FastJsonHelper.getObjects(recommendData,MarketBean.class);
                        MarketNewAdapter adapter=new MarketNewAdapter(newBeanList,BuyActivity.this);
                        recmmendRecycleview.setAdapter(adapter);
                        break;
                    case HttpWhatContacts.GETHEAD :
                        Helper headHelper=JsonResultUtils.helper(response);
                        String headData=headHelper.getContentByKey("inventory");
                        List<Map<String,String>> maps=FastJsonHelper.getKeyMapsList(headData);
                        listnum.setText(maps.size()+"人加入了购买清单");
                        LalaLog.i("qingdan",maps.toString());
                        BuyHeadAdapter headAdapter=new BuyHeadAdapter(BuyActivity.this,maps);
                        headRecycleview.setAdapter(headAdapter);
                        break;
                    case HttpWhatContacts.GETCOMMENT :
                        Helper commentHelper=JsonResultUtils.helper(response);
                        String commentData=commentHelper.getContentByKey("comment");
                        List<CommentBean> commentBeanList=FastJsonHelper.getObjects(commentData,CommentBean.class);
                        lunxinnum.setText(commentBeanList.size()+"");
                        BuyCommentAdaper commentAdaper=new BuyCommentAdaper(BuyActivity.this,commentBeanList);
                        commentListview.setAdapter(commentAdaper);
                }

            }

            @Override
            public void onFail(String errMsg) {

            }
        };

    }

    @Override
    protected void initListener() {
        buy.setOnClickListener(this);

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
        VolleyHelper.getInstance().add(commDataDao,this,HttpWhatContacts.GETHEAD,ApiContacts.BUY_GETHEAD,httpListener,paramers,false);
        VolleyHelper.getInstance().add(commDataDao,this,HttpWhatContacts.GETARTICLERECOMMEND,ApiContacts.BUY_GETRECOMMEND,httpListener,paramers,false);
        VolleyHelper.getInstance().add(commDataDao,this,HttpWhatContacts.GETCOMMENT,ApiContacts.BUY_GETCOMMENT,httpListener,paramers,false);
    }
    public void showStars(int level){
        switch (level) {
            case 0:
                break;
            case 1:
                star1.setImageResource(R.drawable.ic_star_full);
                break;
            case 2:
                star1.setImageResource(R.drawable.ic_star_full);
                star2.setImageResource(R.drawable.ic_star_full);
                break;
            case 3:
                star1.setImageResource(R.drawable.ic_star_full);
                star2.setImageResource(R.drawable.ic_star_full);
                star3.setImageResource(R.drawable.ic_star_full);
                break;
            case 4:
                star1.setImageResource(R.drawable.ic_star_full);
                star2.setImageResource(R.drawable.ic_star_full);
                star3.setImageResource(R.drawable.ic_star_full);
                star4.setImageResource(R.drawable.ic_star_full);
                break;
            case 5:
                star1.setImageResource(R.drawable.ic_star_full);
                star2.setImageResource(R.drawable.ic_star_full);
                star3.setImageResource(R.drawable.ic_star_full);
                star4.setImageResource(R.drawable.ic_star_full);
                star5.setImageResource(R.drawable.ic_star_full);
            default:
                break;
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.buyactivity_buy){
            if (PreferenceManager.getInstance(this).getBoolean("islogin",false)){
                Intent intent=new Intent(this,WebBuyActivity.class);
                intent.putExtra("links",links);
                startActivity(intent);
            }else {
                showToastMsg("请先登录");
                Intent intent=new Intent(this, LoginActivity.class);
                startActivity(intent);
            }

        }

    }
}
