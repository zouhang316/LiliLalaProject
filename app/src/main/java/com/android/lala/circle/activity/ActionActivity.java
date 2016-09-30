package com.android.lala.circle.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.base.BaseActivity;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.circle.bean.ActionBean;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ZH on 2016/9/26.
 * 497239511@qq.com
 */
public class ActionActivity extends BaseActivity {
    private HttpListener<String> httpListener;
    private TextView mTitle;
    private TextView mContent;
    private ImageView mBackground;
    private List<ActionBean> actionBeanList,mydata;
    @Override
    protected void initData() {

        int id=Integer.parseInt(getIntent().getStringExtra("id"));
        getMyData();
        commDataDao=new CommDataDaoImpl(this,false,"");
        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                Helper helper= JsonResultUtils.helper(response);
                String data=helper.getContentByKey("content");
                actionBeanList= FastJsonHelper.getObjects(data,ActionBean.class);
//                mTitle.setText(actionBeanList.get(0).getTitle());
//                mContent.setText(actionBeanList.get(0).getContent());
//                Picasso.with(ActionActivity.this).load(actionBeanList.get(0).getBackground()).into(mBackground);
//                setTitle(actionBeanList.get(0).getTitle());
                //TODO 暂时使用离线数据 上面注释等待数据后可以直接使用(后期更新再上)
                //功能暂时不用
                mTitle.setText(mydata.get(0).getTitle());
                mContent.setText(mydata.get(0).getContent());
                Picasso.with(ActionActivity.this).load(mydata.get(0).getBackground()).into(mBackground);
                setTitle(mydata.get(0).getTitle());

            }

            @Override
            public void onFail(String errMsg) {

            }
        };

    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_action);
        mTitle=findView(R.id.action_title);
        mContent=findView(R.id.action_content);
        mBackground=findView(R.id.action_background);
        getDataByVolley();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean isShowToolBar() {
        return true;
    }
    public void getDataByVolley(){
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("id",getIntent().getStringExtra("id"));
        VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.GETCONTENT, ApiContacts.ACTION,httpListener,paramers,false);
    }

    public void getMyData(){
        mydata=new ArrayList<>();
        for (int i = 0; i <8 ; i++) {
            ActionBean bean=new ActionBean();
            bean.setBackground("http://www.petmrs.com/uploads/allimg/140624/3-140624113644.jpg");
            bean.setId((i+1)+"");
            bean.setContent("这是离线内容这是离线内容这是离线内容这是离线内容这是离线内容这是离线内容"+i);
            bean.setTitle("这是离线标题"+1);
            mydata.add(bean);
        }
    }
}
