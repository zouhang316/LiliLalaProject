package com.android.lala.mine.adapter;

import android.content.Context;

import com.android.lala.R;
import com.android.lala.base.adapter.BaseListViewAdapter;
import com.android.lala.base.adapter.BaseListViewViewHolder;
import com.android.lala.mine.bean.CollectBean;

import java.util.List;

/**
 * Created by ZH on 2016/11/23.
 * 497239511@qq.com
 */
public class CollcetAdapter extends BaseListViewAdapter<CollectBean> {
    public CollcetAdapter(Context context, List<CollectBean> mLists) {
        super(context, mLists);
    }

    public CollcetAdapter(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId(int postion) {
        return R.layout.item_information_article;
    }

    @Override
    public void convert(BaseListViewViewHolder holder, CollectBean collectBean, int position) {
        holder.setText(R.id.information_article_ietm_title,collectBean.getTitle());
        holder.setText(R.id.information_article_ietm_channelname,collectBean.getChannels());
        holder.setText(R.id.information_article_ietm_time,collectBean.getDatetime());
        holder.setImageUrl(R.id.information_article_ietm_image,collectBean.getBackground());
        holder.setVisible(R.id.information_article_ietm_channelname,false);
        holder.setVisible(R.id.nformation_article_ietm_looknum,false);

    }
}
