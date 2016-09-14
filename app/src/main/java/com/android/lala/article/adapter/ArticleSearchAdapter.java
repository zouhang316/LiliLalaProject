package com.android.lala.article.adapter;

import android.content.Context;

import com.android.lala.R;
import com.android.lala.article.bean.ArticleSearchBean;
import com.android.lala.base.adapter.BaseListViewAdapter;
import com.android.lala.base.adapter.BaseListViewViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/9/8.
 */
public class ArticleSearchAdapter extends BaseListViewAdapter<ArticleSearchBean> {
    public ArticleSearchAdapter(Context context) {
        super(context);
    }

    public ArticleSearchAdapter(Context context, List<ArticleSearchBean> mLists) {
        super(context, mLists);
    }

    @Override
    public int getLayoutId(int postion) {
        return R.layout.item_search_article;
    }

    @Override
    public void convert(BaseListViewViewHolder holder, ArticleSearchBean articleSearchBean, int position) {
        holder.setText(R.id.search_article_item_sort,articleSearchBean.getSort());
        holder.setText(R.id.search_article_item_author,articleSearchBean.getAuthor());
        holder.setText(R.id.search_article_item_content,articleSearchBean.getTitle());
        holder.setText(R.id.search_article_item_date,articleSearchBean.getDatetime());

    }
}
