package com.android.lala.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.base.BaseActivity;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.infomation.bean.ArticleBean;
import com.android.lala.utils.LalaLog;
import com.android.lala.view.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/8/30.
 */
public class ArticleActivity extends BaseActivity implements View.OnClickListener{
    private CircleImageView headview;
    private TextView title,article_class,channelname,date,likenum;
    private ImageView toppic,channel_icon;
    private HttpListener<String> httpListener;
    private WebView mWebview;
    private Picasso picasso;
    private CircleImageView channelhead;
    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_article);
        title= findView(R.id.acricle_title);
        article_class=  findView(R.id.article_class);
        channelname= findView(R.id.article_channelname);
        likenum= findView(R.id.article_likenum);
        toppic=  findView(R.id.article_toppic);
        mWebview= findView(R.id.article_webview);
        channel_icon=  findView(R.id.channelicon);
        channelhead=findView(R.id.article_channelhead);
    }

    @Override
    protected void initData() {
        commDataDao=new CommDataDaoImpl(this,false," ");
        this.httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {

                Helper helper= JsonResultUtils.helper(response);
                String article=helper.getContentByKey("article");
                LalaLog.json("String article",article);
                //ArticleBean articleBean= (ArticleBean) FastJsonHelper.getObjects(article,ArticleBean.class);
                List<ArticleBean> articleBeenList=FastJsonHelper.getObjects(article,ArticleBean.class);
                ArticleBean articleBean=articleBeenList.get(0);
                if(null !=articleBean){
                    LalaLog.i("articleBean:",articleBean.toString());

                    mWebview.loadUrl(ApiContacts.CONTENTURL+"/"+articleBean.getContent());
                    Picasso.with(ArticleActivity.this).load("http://lelelala.net/static/upload/20160725bd0d3b67c70443b4af173abe0a916e8f.jpg").into(toppic);
                    Picasso.with(ArticleActivity.this).load(articleBean.getChannel_ico()).into(channel_icon);
                    LalaLog.i("channelicon",articleBean.getChannel_ico());
                }


            }

            @Override
            public void onFail(String errMsg) {
                showMessageDialog("提示",errMsg);
            }
        };

        getArticle(this.httpListener);
    }

    /***
     * 获取文章
     */
    private void getArticle(HttpListener httpListener) {
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("id","405");
        VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.GETARTICLE, ApiContacts.ARTICLE,httpListener,paramers,true);
    }

    @Override
    protected void initListener() {
        title.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int Viewid=v.getId();

    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }


}
