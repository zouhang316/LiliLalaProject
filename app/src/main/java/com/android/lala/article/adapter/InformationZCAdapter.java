package com.android.lala.article.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.article.activity.ZhongChouActivity;
import com.android.lala.article.bean.ZCBean;
import com.android.lala.base.adapter.BaseRecycleAdapter;
import com.android.lala.base.adapter.BaseRecycleViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ZH on 2016/11/24.
 * 497239511@qq.com
 */
public class InformationZCAdapter extends BaseRecycleAdapter {
    private List<ZCBean> mList;
    private Context context;

    public InformationZCAdapter(List<ZCBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    protected int getDataCount() {
        return mList.size();
    }

    @Override
    protected BaseRecycleViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
        final ImageView background;
        final TextView title;
        View view= LayoutInflater.from(context).inflate(R.layout.item_zhongchou,parent,false);
        background= (ImageView) view.findViewById(R.id.zc_background);
        title= (TextView) view.findViewById(R.id.zc_title);
        BaseRecycleViewHolder holder=new BaseRecycleViewHolder(view) {
            @Override
            public void onBindViewHolder(int position) {
                title.setText(mList.get(position).getTitle());
                Picasso.with(context).load(mList.get(position).getImg_url()).into(background);
            }

            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(context, ZhongChouActivity.class);
                intent.putExtra("url",mList.get(position).getUrl());
                context.startActivity(intent);
            }
        };
        return holder;
    }
}
