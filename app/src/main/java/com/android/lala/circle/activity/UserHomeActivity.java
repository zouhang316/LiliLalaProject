package com.android.lala.circle.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.base.BaseActivity;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.circle.adapter.UserHomeAdapter;
import com.android.lala.circle.bean.UserArticleBean;
import com.android.lala.circle.bean.UserHomeBean;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.login.bean.UserBean;
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
public class UserHomeActivity extends BaseActivity implements View.OnClickListener{
    private MyListView listView;
    private ScrollView scrollView;
    private HttpListener<String> httpListener;
    private CircleImageView head;
    private TextView name;
    private TextView qianming;
    private TextView sharanum,likenum,fansnum;
    private ImageView back,transition;
    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_userhome);
        listView=findView(R.id.userhome_listview);
        scrollView=findView(R.id.userhome_scrollview);
        scrollView.smoothScrollTo(0,0);
        head=findView(R.id.userhome_head);
        name=findView(R.id.userhome_name);
        sharanum=findView(R.id.share_num);
        likenum=findView(R.id.like_num);
        fansnum=findView(R.id.fans_num);
        qianming=findView(R.id.userhome_qianming);
        back=findView(R.id.back);
        transition=findView(R.id.transition);
        getData();

    }

    @Override
    protected void initData() {
        commDataDao=new CommDataDaoImpl(this,false,"");
        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                switch (what){
                    case HttpWhatContacts.GETUP :
                        Helper helper= JsonResultUtils.helper(response);
                        String data=helper.getContentByKey("homepageA");
                        UserHomeBean bean= FastJsonHelper.getObject(data,UserHomeBean.class);
                        Picasso.with(UserHomeActivity.this).load(bean.getPhoto()).into(head);
                        name.setText(bean.getName());
                        qianming.setText(bean.getGexing());
                        sharanum.setText(bean.getShare()+"");
                        likenum.setText(bean.getAttention()+"");
                        fansnum.setText(bean.getFans()+"");
                        break;
                    case HttpWhatContacts.GETDOWN :
                        Helper helper2=JsonResultUtils.helper(response);
                        String data2=helper2.getContentByKey("homepageB");
                        final List<UserArticleBean> beanList=FastJsonHelper.getObjects(data2,UserArticleBean.class);
                        UserHomeAdapter adapter=new UserHomeAdapter(UserHomeActivity.this,beanList);
                        listView.setAdapter(adapter);
                        transition.setVisibility(View.GONE);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent=new Intent(UserHomeActivity.this,CircleArticleActivity.class);
                                intent.putExtra("id",beanList.get(position).getId());
                                startActivity(intent);
                            }
                        });
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
        back.setOnClickListener(this);

    }

    public void getData(){
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("userId",getIntent().getStringExtra("userId"));
        VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.GETUP, ApiContacts.USERHOME_A,httpListener,paramers,false);
        VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.GETDOWN, ApiContacts.USERHOME_B,httpListener,paramers,false);
    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.back:
                finish();
                break;
        }
    }
}
