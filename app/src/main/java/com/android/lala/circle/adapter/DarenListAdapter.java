package com.android.lala.circle.adapter;

import android.content.Context;

import com.android.lala.R;
import com.android.lala.base.adapter.BaseListViewAdapter;
import com.android.lala.base.adapter.BaseListViewViewHolder;
import com.android.lala.circle.bean.DarenBean;

import java.util.List;

/**
 * Created by ZH on 2016/12/16.
 * 497239511@qq.com
 */
public class DarenListAdapter extends BaseListViewAdapter<DarenBean> {
    public DarenListAdapter(Context context) {
        super(context);
    }

    public DarenListAdapter(Context context, List<DarenBean> mLists) {
        super(context, mLists);
    }

    @Override
    public int getLayoutId(int postion) {
        return R.layout.item_circle_article;
    }

    @Override
    public void convert(BaseListViewViewHolder holder, DarenBean darenBean, int position) {
        holder.setText(R.id.circle_name,"#"+darenBean.getSort());
        holder.setText(R.id.title,darenBean.getTitle());
        holder.setText(R.id.author,darenBean.getAuthor());
        holder.setText(R.id.looknum,darenBean.getClicknum());
        holder.setImageUrl(R.id.background,darenBean.getBackground());
    }
}
