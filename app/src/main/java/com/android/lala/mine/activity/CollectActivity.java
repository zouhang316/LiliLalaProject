package com.android.lala.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.lala.R;
import com.android.lala.article.activity.ArticleActivity;
import com.android.lala.base.BaseActivity;
import com.android.lala.mine.adapter.CollcetAdapter;
import com.android.lala.mine.bean.CollectBean;
import com.android.lala.utils.LalaLog;

import java.util.List;

/**
 * Created by ZH on 2016/11/22.
 * 497239511@qq.com
 */
public class CollectActivity extends  BaseActivity{
    private ListView listView;
    private List<CollectBean> collectBeenList;
    private CollcetAdapter collcetAdapter;

    @Override
    protected void initData() {
        collectBeenList=getIntent().getParcelableArrayListExtra("collectlist");
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
        setContentView(R.layout.activity_collect);
        View view= LayoutInflater.from(this).inflate(R.layout.listview_footer,null);
        listView=findView(R.id.collect_listview);
        collcetAdapter=new CollcetAdapter(this,collectBeenList);
        listView.setAdapter(collcetAdapter);
        listView.addFooterView(view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(CollectActivity.this, ArticleActivity.class);
                intent.putExtra("id",collectBeenList.get(position).getId());
                intent.putExtra("toppic",collectBeenList.get(position).getBackground());
                startActivity(intent);
            }
        });
        setTitle("我的收藏");

    }
}
