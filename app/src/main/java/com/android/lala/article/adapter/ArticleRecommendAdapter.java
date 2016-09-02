package com.android.lala.article.adapter;

import android.content.Context;

import com.android.lala.R;

import com.android.lala.article.bean.ArticleViewBean;
import com.android.lala.base.adapter.BaseListViewAdapter;
import com.android.lala.base.adapter.BaseListViewViewHolder;


import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
public class ArticleRecommendAdapter extends BaseListViewAdapter<ArticleViewBean> {
    public ArticleRecommendAdapter(Context context) {
        super(context);
    }

    public ArticleRecommendAdapter(Context context, List mLists) {
        super(context, mLists);
    }

    @Override
    public int getLayoutId(int postion) {
        return R.layout.recommend_articlebottm_item;
    }

    @Override
    public void convert(BaseListViewViewHolder holder, ArticleViewBean articleViewBean, int position) {
        holder.setText(R.id.article_bottom_title,articleViewBean.getTitle());
        holder.setText(R.id.article_bottom_channelname,articleViewBean.getAuthor());
        holder.setText(R.id.article_bottom_time,articleViewBean.getDatetime());
        holder.setImageUrl(R.id.article_bottom_channelhead,articleViewBean.getChannel_ico());
        holder.setImageUrl(R.id.article_bottom_image,articleViewBean.getBackground());
    }



}
