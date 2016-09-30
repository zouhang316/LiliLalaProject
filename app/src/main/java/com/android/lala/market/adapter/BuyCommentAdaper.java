package com.android.lala.market.adapter;

import android.content.Context;

import com.android.lala.R;
import com.android.lala.base.adapter.BaseListViewAdapter;
import com.android.lala.base.adapter.BaseListViewViewHolder;
import com.android.lala.market.bean.CommentBean;

import java.util.List;

/**
 * Created by ZH on 2016/9/29.
 * 497239511@qq.com
 */
public class BuyCommentAdaper extends BaseListViewAdapter<CommentBean>{
    public BuyCommentAdaper(Context context) {
        super(context);
    }

    public BuyCommentAdaper(Context context, List<CommentBean> mLists) {
        super(context, mLists);
    }

    @Override
    public int getLayoutId(int postion) {
        return R.layout.item_buy_comment;
    }

    @Override
    public void convert(BaseListViewViewHolder holder, CommentBean commentBean, int position) {
            holder.setText(R.id.buy_comment_name,mLists.get(position).getAutor());
            holder.setText(R.id.buy_comment_date,mLists.get(position).getDatetime());
            holder.setText(R.id.buy_comment_content,mLists.get(position).getContent());
            holder.setImageUrl(R.id.buy_comment_head,mLists.get(position).getPhoto());
    }
}
