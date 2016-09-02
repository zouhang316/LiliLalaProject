package com.android.lala.article.adapter;

import android.content.Context;

import com.android.lala.R;
import com.android.lala.article.bean.ArticleViewBean;
import com.android.lala.base.adapter.BaseListViewAdapter;
import com.android.lala.base.adapter.BaseListViewViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/9/2.
 */
public class InformationArticleAdapter extends BaseListViewAdapter<ArticleViewBean> {

    public InformationArticleAdapter(Context context) {
        super(context);
    }

    public InformationArticleAdapter(Context context, List<ArticleViewBean> mLists) {
        super(context, mLists);
    }

    @Override
    public int getLayoutId(int postion) {
        return R.layout.information_article_item;
    }

    @Override
    public void convert(BaseListViewViewHolder holder, ArticleViewBean articleViewBean, int position) {
        holder.setText(R.id.information_article_ietm_title,articleViewBean.getTitle());
        holder.setText(R.id.information_article_ietm_channelname,articleViewBean.getChannel());
        holder.setText(R.id.nformation_article_ietm_looknum,articleViewBean.getClicknum());
        holder.setText(R.id.information_article_ietm_time,articleViewBean.getDatetime());
        holder.setImageUrl(R.id.information_article_ietm_image,articleViewBean.getBackground());

    }
}
