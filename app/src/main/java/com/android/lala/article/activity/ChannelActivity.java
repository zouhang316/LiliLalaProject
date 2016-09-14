package com.android.lala.article.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.article.adapter.InformationArticleAdapter;
import com.android.lala.article.bean.ArticleViewBean;
import com.android.lala.article.bean.ChannelViewBean;
import com.android.lala.base.BaseActivity;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.utils.LalaLog;
import com.android.lala.view.MyListView;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class ChannelActivity extends BaseActivity implements AdapterView.OnItemClickListener{
       private MyListView listView;
       private InformationArticleAdapter adapter;
       private HttpListener httpListener;
       private String id,toppic;
       private ImageView backgrond;
       private List<ArticleViewBean> channelList;
    @Override
    protected void initData() {

        commDataDao=new CommDataDaoImpl(this,false,"");
        getIntentData();
        this.httpListener=new HttpListener() {
            @Override
            public void onSuccess(int what, Object response) {
                LalaLog.i("channel",response+"");
                Helper helper= JsonResultUtils.helper(response.toString());
                String channel=helper.getContentByKey("article");
                channelList = FastJsonHelper.getObjects(channel,ArticleViewBean.class);
                    if (null !=channelList){
                        adapter=new InformationArticleAdapter(getApplicationContext(),channelList);
                        listView.setAdapter(adapter);
                    }
            }

            @Override
            public void onFail(String errMsg) {}
        };
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("id",id);
        VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.GETARTICLE, ApiContacts.CHANNEL,this.httpListener,paramers,false);
    }

    private void getIntentData() {
        ChannelViewBean bean=getIntent().getParcelableExtra("data");
        id=bean.getId();
        toppic=bean.getChannel_background();
        setTitle(bean.getChannels());
    }

    @Override
    protected void initListener() {
    }


    @Override
    protected boolean isShowToolBar() {
        return true;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_channel);
        listView=findView(R.id.channalactivity_listview);
        listView.setOnItemClickListener(this);
        backgrond=findView(R.id.activity_channeltoppic);
        Picasso.with(this).load(toppic).into(backgrond);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this,ArticleActivity.class);
        intent.putExtra("id",channelList.get(position).getId());
        intent.putExtra("toppic",channelList.get(position).getBackground());
        startActivity(intent);
    }
}
