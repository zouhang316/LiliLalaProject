package com.android.lala.circle.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
import com.android.lala.circle.bean.CommentBean3;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.login.bean.UserBean;
import com.android.lala.market.bean.CommentBean;
import com.android.lala.utils.LalaLog;
import com.android.lala.utils.PreferenceManager;
import com.android.lala.view.CircleImageView;
import com.android.lala.view.MyListView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private TextView empty;
    private WebView content;
    private ImageView background;
    private String userID;
    private ImageView transition;
    private List<CommentBean3> beanList;
    private Dialog dialog;
    private View inflate;
    private EditText editText;
    private TextView editText_btn;
    private Button commit;
    private TextView commentNum;
    private TextView commentLocation;
    private CommentAdapter adapter;
    @Override
    protected void initData() {

        commDataDao=new CommDataDaoImpl(this,false,"");
        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                Helper helper= JsonResultUtils.helper(response);
                switch (what){
                    case HttpWhatContacts.GETCONTENT:
                        //文章详情数据
                        String data=helper.getContentByKey("content");
                        CircleArticleBean bean= FastJsonHelper.getObject(data,CircleArticleBean.class);
                        LalaLog.i("bean",bean.toString());
                        name.setText(bean.getAuthor());
                        date.setText(bean.getDatetime());
                        userID=bean.getUserId();
                        Picasso.with(CircleArticleActivity.this).load(bean.getBackground()).into(background);
                        Picasso.with(CircleArticleActivity.this).load(bean.getPhoto()).into(headImage);
                        content.loadUrl(ApiContacts.CIRCLECONTENT+"/"+bean.getContent());
                        commentNum.setText(bean.getCommentnum());
                        if (!bean.getCommentnum().equals("0")){
                            empty.setVisibility(View.GONE);
                        }
                        transition.setVisibility(View.GONE);
                        break;
                    case HttpWhatContacts.GETCOMMENT:
                        //获取评论数据
                        String commentData=helper.getContentByKey("comment");
                        beanList=FastJsonHelper.getObjects(commentData,CommentBean3.class);
                        adapter=new CommentAdapter(getApplicationContext(),beanList,0);
                        commentList.setAdapter(adapter);
                        break;
                    case HttpWhatContacts.POSTCOMMENT:
                        //发送评论
                        String commentData2=helper.getContentByKey("comment");
                        beanList=FastJsonHelper.getObjects(commentData2,CommentBean3.class);
                        adapter.replaceAll(beanList);
                        commentList.setSelection(View.FOCUS_DOWN);
                        commentNum.setText(Integer.parseInt(commentNum.getText().toString())+1+"");
                        empty.setVisibility(View.GONE);
                        break;
                }

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
        empty=findView(R.id.empty_comment);
        commentLocation=findView(R.id.commentlocation);
        commentNum=findView(R.id.comment_num);
        editText_btn=findView(R.id.edit_btn);
        content=findView(R.id.circlearticle_author_webview);
        background=findView(R.id.circlearticle_author_background);
        transition=findView(R.id.circle_transition);


        commentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),CommentActivty.class);
                intent.putExtra("data",beanList.get(position));
                startActivityForResult(intent,101);

            }
        });
        getDataByVolley();
        getComment();

    }

    @Override
    protected void initListener() {
        louzhu.setOnClickListener(this);
        back.setOnClickListener(this);
        editText_btn.setOnClickListener(this);
        commentNum.setOnClickListener(this);
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
            case R.id.postcomment :
                sendComment();
                break;
            case R.id.edit_btn:
                showPop();
                break;
            case R.id.comment_num:
                scrollToComment();
                break;
        }

    }

    private void showPop(){
        dialog=new Dialog(this,R.style.MyDialogStyleBottom);
        inflate= LayoutInflater.from(this).inflate(R.layout.pop_comment,null);
        commit= (Button) inflate.findViewById(R.id.postcomment);
        editText= (EditText) inflate.findViewById(R.id.comment_content);
        editText.addTextChangedListener(watcher);

        commit.setEnabled(false);
        commit.setOnClickListener(this);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity( Gravity.BOTTOM);
        dialogWindow.getDecorView().setPadding(0,0,0,0);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;//设置Dialog距离底部的距离
        //设置宽度撑满 不然默认不会沾满
        lp.width=WindowManager.LayoutParams.MATCH_PARENT;
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }
    private void getComment(){
        HashMap<String,String> paremers=new HashMap<>();
        paremers.put("id",getIntent().getStringExtra("id"));
        VolleyHelper.getInstance().add(commDataDao,this,HttpWhatContacts.GETCOMMENT,ApiContacts.CIRCLE_GETCOMMENT,httpListener,paremers,false);
    }
    private void sendComment(){
        PreferenceManager manager=PreferenceManager.getInstance(this);
        boolean isLogin= manager.getBoolean("islogin",false);
        String content=editText.getText().toString();
        HashMap<String,String> paremers=new HashMap<>();
        if (isLogin){
            paremers.put("userId",manager.getString("id",""));
        }
        paremers.put("circleId",getIntent().getStringExtra("id"));
        paremers.put("content",content);
        VolleyHelper.getInstance().add(commDataDao,this,HttpWhatContacts.POSTCOMMENT,ApiContacts.CIRCLE_POSTCOMMENT,httpListener,paremers,false);
        dialog.dismiss();
    }
    TextWatcher watcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (editText.getText().length()!=0){
                commit.setEnabled(true);
            }else {
                commit.setEnabled(false);
            }
        }
        @Override
        public void afterTextChanged(Editable s) {
        }
    };
    private void scrollToComment(){
        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==101){
            getComment();
        }
    }
}
