package com.android.lala.article.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.api.HttpWhatContacts;
import com.android.lala.article.adapter.ArticleSearchAdapter;
import com.android.lala.article.bean.ArticleSearchBean;
import com.android.lala.base.BaseActivity;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.fastjson.FastJsonHelper;
import com.android.lala.fastjson.Helper;
import com.android.lala.fastjson.JsonResultUtils;
import com.android.lala.http.VolleyHelper;
import com.android.lala.http.listener.HttpListener;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/9/8.
 */
public class SearchArticleActivity extends BaseActivity implements View.OnClickListener{
    private HttpListener<String> httpListener;
    private TextView search;
    private EditText searchview;
    private ListView mList;
    private List<ArticleSearchBean> searchBeanList;
    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_searcharticle);
        searchview=findView(R.id.search_article_searchedit);
        search=findView(R.id.search_article_search);
        mList=findView(R.id.search_articlelv);
        searchview.addTextChangedListener(textWatcher);
    }

    @Override
    protected void initData() {
        commDataDao=new CommDataDaoImpl(this,false,"search.json");
        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                Helper helper= JsonResultUtils.helper(response);
                String content=helper.getContentByKey("channels");
                searchBeanList= FastJsonHelper.getObjects(content,ArticleSearchBean.class);
                ArticleSearchAdapter adapter=new ArticleSearchAdapter(getApplicationContext(),searchBeanList);
                mList.setAdapter(adapter);
                mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(getApplicationContext(),ArticleActivity.class);
                        intent.putExtra("id",searchBeanList.get(position).getId());
                        intent.putExtra("toppic",searchBeanList.get(position).getBackground());
                        startActivity(intent);
                        finish();
                    }
                });
            }

            @Override
            public void onFail(String errMsg) {

            }
        };

    }

    public  void getSearchData(){
        HashMap<String ,String > paramers=new HashMap<>();
        paramers.put("search",searchview.getText().toString().trim());
        VolleyHelper.getInstance().add(commDataDao,this, HttpWhatContacts.GETARTICLE, ApiContacts.ARTICLE_SEARCH,httpListener,paramers,false);
    }

    @Override
    protected void initListener() {
        search.setOnClickListener(this);
    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.search_article_search){
            if (!search.getText().equals("搜索")){
                this.finish();
                return;
            }
            getSearchData();
        }
    }
    private TextWatcher textWatcher=new TextWatcher() {

        private CharSequence temp;
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                search.setText("搜索");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp=s;
        }

        @Override
        public void afterTextChanged(Editable s) {
                if (temp.length()==0){
                    search.setText("取消");
                }

        }
    };
}
