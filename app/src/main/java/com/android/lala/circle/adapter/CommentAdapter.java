package com.android.lala.circle.adapter;

import android.content.Context;

import com.android.lala.R;
import com.android.lala.base.adapter.BaseListViewAdapter;
import com.android.lala.base.adapter.BaseListViewViewHolder;
import com.android.lala.login.bean.UserBean;

import java.util.List;

/**
 * Created by ZH on 2016/9/27.
 * 497239511@qq.com
 */
public class CommentAdapter extends BaseListViewAdapter<UserBean>{

    public CommentAdapter(Context context) {
        super(context);
    }

    public CommentAdapter(Context context, List<UserBean> mLists) {
        super(context, mLists);
    }

    @Override
    public int getLayoutId(int postion) {
        //TODO 缺少数据
        return R.layout.item_circle_comment;
    }

    @Override
    public void convert(BaseListViewViewHolder holder, UserBean userBean, int position) {

    }
}
