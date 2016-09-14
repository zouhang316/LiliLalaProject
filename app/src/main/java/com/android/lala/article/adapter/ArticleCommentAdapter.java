package com.android.lala.article.adapter;

import android.content.Context;

import com.android.lala.R;
import com.android.lala.article.bean.ArticleCommentBean;
import com.android.lala.base.adapter.BaseListViewAdapter;
import com.android.lala.base.adapter.BaseListViewViewHolder;
import com.android.lala.utils.LalaLog;

import java.util.List;

/**
 * Created by Administrator on 2016/9/8.
 */
public class ArticleCommentAdapter extends BaseListViewAdapter<ArticleCommentBean> {

    public ArticleCommentAdapter(Context context, List<ArticleCommentBean> mLists) {
        super(context, mLists);
    }

    public ArticleCommentAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId(int postion) {
        return R.layout.item_article_comment;
    }

    @Override
    public void convert(BaseListViewViewHolder holder, ArticleCommentBean articleCommentBean, int position) {
        holder.setText(R.id.article_comment_name,articleCommentBean.getAutor());
        holder.setText(R.id.article_comment_data,articleCommentBean.getDatetime());
        holder.setText(R.id.article_comment_content,articleCommentBean.getContent());
        holder.setImageUrl(R.id.article_comment_head,articleCommentBean.getPhoto());
        LalaLog.i("photo", articleCommentBean.getPhoto());

    }
}
