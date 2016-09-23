package com.android.lala.article.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lala.R;
import com.android.lala.article.activity.ArticleActivity;
import com.android.lala.article.bean.ArticleViewBean;
import com.android.lala.base.adapter.BaseRecycleAdapter;
import com.android.lala.base.adapter.BaseRecycleViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ZH on 2016/9/21.
 * 497239511@qq.com
 */
public class ChannelAdapter extends BaseRecycleAdapter {
    private List<ArticleViewBean> mList;
    private Context context;

    public ChannelAdapter(List<ArticleViewBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }


    @Override
    protected int getDataCount() {
        return mList.size();
    }

    @Override
    protected BaseRecycleViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
        final TextView name,title,date,clicknum,author;
        final ImageView imageView;
        View view= LayoutInflater.from(context).inflate(R.layout.item_information_article,parent,false);
        name= (TextView) view.findViewById(R.id.information_article_ietm_channelname);
        title= (TextView) view.findViewById(R.id.information_article_ietm_title);
        date= (TextView) view.findViewById(R.id.information_article_ietm_time);
        clicknum= (TextView) view.findViewById(R.id.nformation_article_ietm_looknum);
        author= (TextView) view.findViewById(R.id.information_article_ietm_channelname);
        imageView= (ImageView) view.findViewById(R.id.information_article_ietm_image);
        BaseRecycleViewHolder holder=new BaseRecycleViewHolder(view) {
            @Override
            public void onBindViewHolder(int position) {
                name.setText(mList.get(position).getChannels());
                date.setText(mList.get(position).getDatetime());
                clicknum.setText(mList.get(position).getClicknum());
                title.setText(mList.get(position).getTitle());
                if (null != mList.get(position).getAuthor()){
                    author.setText(mList.get(position).getAuthor());
                }
                Picasso.with(context).load(mList.get(position).getBackground()).into(imageView);
            }

            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(context,ArticleActivity.class);
                intent.putExtra("id",mList.get(position).getId());
                intent.putExtra("toppic",mList.get(position).getBackground());
                view.getContext().startActivity(intent);
            }
        };
        return holder;
    }
}
