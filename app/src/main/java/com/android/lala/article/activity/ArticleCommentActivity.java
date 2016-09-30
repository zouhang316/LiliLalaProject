package com.android.lala.article.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.android.lala.login.LoginActivity;
import com.android.lala.utils.LalaLog;
import com.android.lala.utils.PreferenceManager;

import java.util.HashMap;
import java.util.List;

public class ArticleCommentActivity extends BaseActivity implements View.OnClickListener{
    private ListView mListview;
    private HttpListener<String> httpListener;
    private PreferenceManager preferenceManager;
    private String UserID;
    private Button sendComment;
    private String id;
    private EditText mContent;
    private ArticleCommentAdapter adapter;

    @Override
    protected void initData() {
        id=getIntent().getStringExtra("id");
        commDataDao=new CommDataDaoImpl(this,false,"comment.json");
        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                if (what==HttpWhatContacts.GETCOMMENT){
                LalaLog.i("comment",response);
                Helper helper= JsonResultUtils.helper(response);
                String comment=helper.getContentByKey("comment");
                List<ArticleCommentBean> commentBeanList= FastJsonHelper.getObjects(comment,ArticleCommentBean.class);
                 adapter=new ArticleCommentAdapter(getApplicationContext(),commentBeanList);
                mListview.setAdapter(adapter);
                setTitle(commentBeanList.size()+"条评论");
                }

                if (what==HttpWhatContacts.POSTCOMMENT){
                    mContent.setText("");
                    LalaLog.i("comment",response);
                    Helper helper= JsonResultUtils.helper(response);
                    String comment=helper.getContentByKey("comment");
                    List<ArticleCommentBean> commentBeanList= FastJsonHelper.getObjects(comment,ArticleCommentBean.class);
                    adapter=new ArticleCommentAdapter(getApplicationContext(),commentBeanList);
                    mListview.setAdapter(adapter);
                    setTitle(commentBeanList.size()+"条评论");
                }
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
        sendComment=findView(R.id.btn_article_sendcomment);
        mContent=findView(R.id.et_article_comment);
    }

    public void firstGetComment(){
        HashMap<String,String> paramers =new HashMap<>();
        paramers.put("id",id);
        paramers.put("userId",UserID);
        VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.GETCOMMENT, ApiContacts.ARTICLE_GETCOMMENT,httpListener,paramers,false);
    }
    public void sendComment(){
        HashMap<String,String> paramers =new HashMap<>();
        paramers.put("articleId",id);
        paramers.put("userId",UserID);
        paramers.put("content",mContent.getText().toString() );
        VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.POSTCOMMENT, ApiContacts.ARTICLE_POSTCOMMENT,httpListener,paramers,false);
    }
    @Override
    protected void initListener() {
        sendComment.setOnClickListener(this);
    }

    @Override
    protected boolean isShowToolBar() {
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_article_sendcomment){
            if (!preferenceManager.getBoolean("islogin",false)){
                Intent intent=new Intent(this, LoginActivity.class);
                startActivity(intent);
                return;
            }
                if (mContent.getText().toString().equals("")){
                    showToastMsg("评论内容不能为空");
                    return;
                }
            sendComment();
        }
    }
}
