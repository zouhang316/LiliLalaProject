package com.android.lala.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.adapter.Article_RecommendAdapter;
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
import com.android.lala.infomation.bean.ArticleViewBean;
import com.android.lala.utils.LalaLog;
import com.android.lala.view.CircleImageView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/30.
 */
public class ArticleActivity extends BaseActivity implements View.OnClickListener{
    private TextView title,article_class,channelname,date,likenum,channelinfo;
    private ImageView toppic,channel_icon;
    private HttpListener<String> httpListener;
    private WebView mWebview;
    private ListView listView;
    private CircleImageView channelhead;
    private  List<ArticleViewBean> articleviewlist;
    private Article_RecommendAdapter adapter;
    private String id;
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
        listView=findView(R.id.article_recommend_bottom_list);
        date=findView(R.id.article_date);
        likenum=findView(R.id.article_likenum);
        channelinfo=findView(R.id.channelinfo_tv);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),ArticleActivity.class);
                intent.putExtra("id",articleviewlist.get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        id=getIntent().getStringExtra("id");
        showToastMsg(id);
        commDataDao=new CommDataDaoImpl(this,false," ");
        this.httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {

                    Helper helper = JsonResultUtils.helper(response);
                    String article = helper.getContentByKey("article");
                    LalaLog.json("String article", article);
                    List<ArticleBean> articleBeenList = FastJsonHelper.getObjects(article, ArticleBean.class);
                    ArticleBean articleBean = articleBeenList.get(0);
                    if (null != articleBean) {
                        LalaLog.i("articleBean:", articleBean.toString());
                        mWebview.loadUrl(ApiContacts.CONTENTURL + "/" + articleBean.getContent());
                        Picasso.with(ArticleActivity.this).load("http://lelelala.net/static/upload/20160725bd0d3b67c70443b4af173abe0a916e8f.jpg").into(toppic);
                        Picasso.with(ArticleActivity.this).load(articleBean.getChannel_ico()).into(channel_icon);
                        Picasso.with(ArticleActivity.this).load(articleBean.getChannel_ico()).into(channelhead);
                        title.setText(articleBean.getTitle());
                        article_class.setText(articleBean.getSort());
                        channelname.setText(articleBean.getChannels());
                        date.setText(articleBean.getDatetime());
                        likenum.setText(articleBean.getSubscription());
                        channelinfo.setText("本文由  "+articleBean.getChannels()+"  发布，已有 "+articleBean.getArticle_num()+" 篇文章");
                        getRecommedData(articleBean.getSort());
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

    private void getRecommedData(final String sort) {

        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        StringRequest request=new StringRequest(Request.Method.POST, ApiContacts.ARTICLERECOMMENDURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Helper helper=JsonResultUtils.helper(s);
                String value=helper.getContentByKey("more");
                articleviewlist=FastJsonHelper.getObjects(value,ArticleViewBean.class);
                LalaLog.i("ArticleViewBean",articleviewlist.toString());
                adapter=new Article_RecommendAdapter(getApplicationContext(),articleviewlist);
                listView.setAdapter(adapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("sort",sort);
                return map;
            }
        };
        queue.add(request);
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
