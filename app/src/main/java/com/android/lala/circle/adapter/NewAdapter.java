package com.android.lala.circle.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lala.R;
import com.android.lala.base.adapter.BaseRecycleAdapter;
import com.android.lala.base.adapter.BaseRecycleViewHolder;
import com.android.lala.circle.activity.CircleArticleActivity;
import com.android.lala.circle.bean.CircleBean;
import com.android.lala.view.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ZH on 2016/9/26.
 * 497239511@qq.com
 */
public class NewAdapter extends BaseRecycleAdapter {
    private Context context;
    private List<CircleBean> mList;

    public NewAdapter(Context context, List<CircleBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    protected int getDataCount() {
        return mList.size();
    }

    @Override
    protected BaseRecycleViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_new,parent,false);
        final TextView username= (TextView) view.findViewById(R.id.item_hot_username);
        final TextView name= (TextView) view.findViewById(R.id.item_hot_name);
        final TextView looknm=(TextView) view.findViewById(R.id.item_hot_looknum);
        final TextView likenm=(TextView) view.findViewById(R.id.item_hot_likenum);
        final ImageView background= (ImageView) view.findViewById(R.id.item_hot_background);
        final CircleImageView head= (CircleImageView) view.findViewById(R.id.item_hot_headimage);
        BaseRecycleViewHolder holder=new BaseRecycleViewHolder(view) {
            @Override
            public void onBindViewHolder(int position) {
                username.setText(mList.get(position).getAuthor());
                looknm.setText(mList.get(position).getClicknum());
                name.setText(mList.get(position).getTitle());
                likenm.setText(mList.get(position).getPraise());
                Picasso.with(context).load(mList.get(position).getPhoto()).into(head);
                Picasso.with(context).load(mList.get(position).getBackground()).into(background);
            }

            @Override
            public void onItemClick(View view, int position) {
                Intent intent =new Intent(context, CircleArticleActivity.class);
                intent.putExtra("id",mList.get(position).getId());
                view.getContext().startActivity(intent);
            }
        };
        return holder;
    }
}
