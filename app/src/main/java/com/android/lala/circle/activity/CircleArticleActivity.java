package com.android.lala.circle.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.base.BaseActivity;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.circle.adapter.CommentAdapter;
import com.android.lala.circle.bean.CircleArticleBean;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.login.bean.UserBean;
import com.android.lala.utils.LalaLog;
import com.android.lala.view.CircleImageView;
import com.android.lala.view.MyListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ZH on 2016/9/27.
 * 497239511@qq.com
 */
public class CircleArticleActivity extends BaseActivity implements View.OnClickListener{
    private HttpListener<String> httpListener;
    private MyListView commentList;
    private ScrollView scrollView;
    private TextView louzhu;
    private CircleImageView headImage,back;
    private TextView name;
    private TextView date;
    private WebView content;
    private ImageView background;
    private String userID;
    @Override
    protected void initData() {
        //TODO 缺少评论数据
        commDataDao=new CommDataDaoImpl(this,false,"");
        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                Helper helper= JsonResultUtils.helper(response);
                String data=helper.getContentByKey("content");
                CircleArticleBean bean= FastJsonHelper.getObject(data,CircleArticleBean.class);
                LalaLog.i("bean",bean.toString());
                name.setText(bean.getAuthor());
                date.setText(bean.getDatetime());
                userID=bean.getUserId();
                Picasso.with(CircleArticleActivity.this).load(bean.getBackground()).into(background);
                Picasso.with(CircleArticleActivity.this).load(bean.getPhoto()).into(headImage);
                content.loadUrl(ApiContacts.CIRCLECONTENT+"/"+bean.getContent());
            }

            @Override
            public void onFail(String errMsg) {

            }
        };
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_circlearticle);
        louzhu=findView(R.id.circle_louzhu);
        scrollView=findView(R.id.circle_scrollview);
        scrollView.smoothScrollTo(10,10);
        commentList=findView(R.id.circlearticle_commentlist);
        headImage=findView(R.id.circlearticle_author_head);
        name=findView(R.id.circlearticle_author_name);
        date=findView(R.id.circlearticle_author_date);
        back=findView(R.id.back);
        content=findView(R.id.circlearticle_author_webview);
        background=findView(R.id.circlearticle_author_background);
        //TODO 评论功能待接口
//        List<UserBean> userBeenlist=new ArrayList<>();
//        for (int i = 0; i <5 ; i++) {
//            UserBean userBean=new UserBean();
//            userBeenlist.add(userBean);
//        }
//        CommentAdapter adapter=new CommentAdapter(this,userBeenlist);
//        commentList.setAdapter(adapter);
        getDataByVolley();

    }

    @Override
    protected void initListener() {
        louzhu.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }

    public void getDataByVolley(){
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("id",getIntent().getStringExtra("id"));
        VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.GETCONTENT, ApiContacts.CIRCLE_ARITICLE,httpListener,paramers,false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.circle_louzhu :
                Intent intent=new Intent(this,UserHomeActivity.class);
                intent.putExtra("userId",userID);
                startActivity(intent);
                break;
            case R.id.back :
                finish();
                break;
        }

    }
}
