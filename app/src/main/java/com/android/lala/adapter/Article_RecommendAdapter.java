package com.android.lala.adapter;

import android.content.Context;
import android.view.View;

import com.android.lala.R;
import com.android.lala.base.adapter.BaseListViewAdapter;
import com.android.lala.base.adapter.BaseListViewViewHolder;
import com.android.lala.infomation.bean.ArticleViewBean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
public class Article_RecommendAdapter extends BaseListViewAdapter<ArticleViewBean> {
    public Article_RecommendAdapter(Context context) {
        super(context);
    }

    public Article_RecommendAdapter(Context context, List mLists) {
        super(context, mLists);
    }

    @Override
    public int getLayoutId(int postion) {
        return R.layout.item_recommend_articlebottm;
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
