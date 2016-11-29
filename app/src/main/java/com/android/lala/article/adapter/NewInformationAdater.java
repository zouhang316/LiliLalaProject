package com.android.lala.article.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.article.activity.ArticleActivity;
import com.android.lala.article.bean.ArticleViewBean;
import com.android.lala.base.adapter.BaseRecycleAdapter;
import com.android.lala.base.adapter.BaseRecycleViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ZH on 2016/11/15.
 * 497239511@qq.com
 */
public class NewInformationAdater extends BaseRecycleAdapter {
    private List<ArticleViewBean> mList;
    private Context context;

    public NewInformationAdater(List<ArticleViewBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    protected int getDataCount() {
        return mList.size();
    }

    @Override
    protected BaseRecycleViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
        final TextView title,channel,clicknum,datatime,author;
        final ImageView background;
        View view= LayoutInflater.from(context).inflate(R.layout.item_information_article,parent,false);
        title= (TextView) view.findViewById(R.id.information_article_ietm_title);
        channel= (TextView) view.findViewById(R.id.information_article_ietm_channelname);
        clicknum= (TextView) view.findViewById(R.id.nformation_article_ietm_looknum);
        datatime= (TextView) view.findViewById(R.id.information_article_ietm_time);
        background= (ImageView) view.findViewById(R.id.information_article_ietm_image);
        author= (TextView) view.findViewById(R.id.information_article_ietm_channelname);
        final BaseRecycleViewHolder holder=new BaseRecycleViewHolder(view) {
            @Override
            public void onBindViewHolder(int position) {
                title.setText(mList.get(position).getTitle());
                channel.setText(mList.get(position).getChannels());
                clicknum.setText(mList.get(position).getClicknum());
                datatime.setText(mList.get(position).getDatetime());
                Picasso.with(context).load(mList.get(position).getBackground()).into(background);
                if (null != mList.get(position).getAuthor()){
                    author.setText(mList.get(position).getAuthor());
                }
            }
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(context, ArticleActivity.class);
                intent.putExtra("id",mList.get(position).getId());
                intent.putExtra("toppic",mList.get(position).getBackground());
                context.startActivity(intent);
            }
        };

        return holder;
    }
}
