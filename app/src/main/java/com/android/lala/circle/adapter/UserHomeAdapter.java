package com.android.lala.circle.adapter;

import android.content.Context;

import com.android.lala.R;
import com.android.lala.base.adapter.BaseListViewAdapter;
import com.android.lala.base.adapter.BaseListViewViewHolder;
import com.android.lala.circle.bean.UserArticleBean;
import com.android.lala.login.bean.UserBean;

import java.util.List;

/**
 * Created by ZH on 2016/9/27.
 * 497239511@qq.com
 */
public class UserHomeAdapter extends BaseListViewAdapter<UserArticleBean> {
    public UserHomeAdapter(Context context) {
        super(context);
    }

    public UserHomeAdapter(Context context, List<UserArticleBean> mLists) {
        super(context, mLists);
    }

    @Override
    public int getLayoutId(int postion) {
        return R.layout.item_userhome;
    }

    @Override
    public void convert(BaseListViewViewHolder holder, UserArticleBean bean, int position) {
        holder.setText(R.id.item_userhome_title,bean.getTitle());
        holder.setText(R.id.item_looknum,bean.getClicknum());
        holder.setText(R.id.item_commentnum,bean.getCommentnum());
        holder.setText(R.id.item_zannum,bean.getPraise());
        holder.setImageUrl(R.id.item_userhome_background,bean.getBackground());

    }
}
