package com.android.lala.article.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.android.lala.R;
import com.android.lala.article.adapter.ArticleRecommendAdapter;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.article.bean.ArticleBean;
import com.android.lala.article.bean.ArticleViewBean;
import com.android.lala.base.BaseActivity;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.yifeiyuan.library.PeriscopeLayout;

/**
 * Created by Administrator on 2016/8/30.
 */
public class ArticleActivity extends BaseActivity implements View.OnClickListener {
    private TextView title, article_class, channelname, date, likenum, channelinfo;
    private ImageView toppic, channel_icon,back,comments,fav,share;
    private HttpListener<String> httpListener;
    private WebView mWebview;
    private ListView listView;
    private CircleImageView channelhead;
    private List<ArticleViewBean> articleviewlist;
    private ArticleRecommendAdapter adapter;
    private String id,toppicurl;
    private Button zan;
    private PeriscopeLayout periscopeLayout;
    private RelativeLayout bottomMenu;
    float x1 = 0;
    float x2 = 0;
    float y1 = 0;
    float y2 = 0;
    ScrollView view;

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_article);
        title = findView(R.id.acricle_title);
        article_class = findView(R.id.article_class);
        channelname = findView(R.id.article_channelname);
        likenum = findView(R.id.article_likenum);
        toppic = findView(R.id.article_toppic);
        mWebview = findView(R.id.article_webview);
        channel_icon = findView(R.id.channelicon);
        channelhead = findView(R.id.article_channelhead);
        listView = findView(R.id.article_recommend_bottom_list);
        date = findView(R.id.article_date);
        likenum = findView(R.id.article_likenum);
        channelinfo = findView(R.id.channelinfo_tv);
        zan=findView(R.id.article_zan);
        back=findView(R.id.article_back);
        comments=findView(R.id.article_comment);
        fav=findView(R.id.article_fav);
        share=findView(R.id.article_share);
        view=findView(R.id.article_scrollview);
        bottomMenu=findView(R.id.article_bottommenu);
        periscopeLayout=findView(R.id.periscopelayout);
        sheAlpha();
        setAnimation();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ArticleActivity.class);
                intent.putExtra("id", articleviewlist.get(position).getId());
                intent.putExtra("toppic",articleviewlist.get(position).getBackground());
                startActivity(intent);
            }
        });
    }

    private void setAnimation() {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    //当手指按下的时候
                    x1 = event.getX();
                    y1 = event.getY();
                }
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    //当手指离开的时候
                    x2 = event.getX();
                    y2 = event.getY();
                    if(y1 - y2 > 50) {
                        bottomMenu.setVisibility(View.INVISIBLE);
                    } else if(y2 - y1 > 50) {
                        bottomMenu.setVisibility(View.VISIBLE);
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        toppicurl=getIntent().getStringExtra("toppic");
        commDataDao = new CommDataDaoImpl(this, false, " ");
        this.httpListener = new HttpListener<String>() {
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
                    Picasso.with(ArticleActivity.this).load(toppicurl).into(toppic);
                    Picasso.with(ArticleActivity.this).load(articleBean.getChannel_ico()).into(channel_icon);
                    Picasso.with(ArticleActivity.this).load(articleBean.getChannel_ico()).into(channelhead);
                    title.setText(articleBean.getTitle());
                    article_class.setText(articleBean.getSort());
                    channelname.setText(articleBean.getChannels());
                    date.setText(articleBean.getDatetime());
                    likenum.setText(articleBean.getSubscription());
                    channelinfo.setText("本文由  " + articleBean.getChannels() + "  发布，已有 " + articleBean.getArticle_num() + " 篇文章");
                    getRecommedData(articleBean.getSort());
                }


            }

            @Override
            public void onFail(String errMsg) {
                showMessageDialog("提示", errMsg);
            }
        };

        getArticle(this.httpListener);
    }

    /***
     * 获取文章
     */
    private void getArticle(HttpListener httpListener) {
        HashMap<String, String> paramers = new HashMap<>();
        paramers.put("id", id);
        LalaLog.i("paramersid",id);
        VolleyHelper.getInstance().add(commDataDao, this, HttpWhatContacts.GETARTICLE, ApiContacts.ARTICLE, httpListener, paramers, true);
    }

    private void getRecommedData(final String sort) {

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, ApiContacts.ARTICLERE_COM, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Helper helper = JsonResultUtils.helper(s);
                String value = helper.getContentByKey("more");
                articleviewlist = FastJsonHelper.getObjects(value, ArticleViewBean.class);
                LalaLog.i("ArticleViewBean", articleviewlist.toString());
                adapter = new ArticleRecommendAdapter(getApplicationContext(), articleviewlist);
                listView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("sort", sort);
                return map;
            }
        };
        queue.add(request);
    }

    @Override
    protected void initListener() {

        zan.setOnClickListener(this);
        back.setOnClickListener(this);
        comments.setOnClickListener(this);
        fav.setOnClickListener(this);
        share.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.article_zan:
                periscopeLayout.addHeart();
                break;
            case R.id.article_comment:
                Intent intent=new Intent(getApplicationContext(),ArticleCommentActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
                break;
            case R.id.article_back:
                finish();
                break;
            case R.id.article_fav:
                //收藏点击事件
                break;
            case  R.id.article_share:
                //分享点击事件
                break;
        }

    }

    private void sheAlpha() {
        back.getBackground().setAlpha(180);
        comments.getBackground().setAlpha(180);
        fav.getBackground().setAlpha(180);
        share.getBackground().setAlpha(180);
    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebview.stopLoading();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);

    }
}
