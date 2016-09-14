package com.android.lala.article.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.article.adapter.ArticleCommentAdapter;
import com.android.lala.article.bean.ArticleCommentBean;
import com.android.lala.base.BaseActivity;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.utils.LalaLog;
import com.android.lala.utils.PreferenceManager;

import java.util.HashMap;
import java.util.List;

public class ArticleCommentActivity extends BaseActivity {
    private ListView mListview;
    private HttpListener<String> httpListener;
    private PreferenceManager preferenceManager;
    private String UserID;

    @Override
    protected void initData() {

        commDataDao=new CommDataDaoImpl(this,false,"comment.json");
        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                LalaLog.i("comment",response);
                Helper helper= JsonResultUtils.helper(response);
                String comment=helper.getContentByKey("comment");
                List<ArticleCommentBean> commentBeanList= FastJsonHelper.getObjects(comment,ArticleCommentBean.class);
                ArticleCommentAdapter adapter=new ArticleCommentAdapter(getApplicationContext(),commentBeanList);
                mListview.setAdapter(adapter);
                setTitle(commentBeanList.size()+"条评论");
            }

            @Override
            public void onFail(String errMsg) {

            }
        };
        preferenceManager=PreferenceManager.getInstance(this);
        UserID=preferenceManager.getString("id","");
        firstGetComment();

    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_article_comment);
        mListview=findView(R.id.commentlistview);
    }

    public void firstGetComment(){
        HashMap<String,String> paramers =new HashMap<>();
        paramers.put("id",getIntent().getStringExtra("id"));
        paramers.put("userId",UserID);
        VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.GETCOMMENT, ApiContacts.ARTICLE_GETCOMMENT,httpListener,paramers,false);
    }
    @Override
    protected void initListener() {

    }

    @Override
    protected boolean isShowToolBar() {
        return true;
    }
}
