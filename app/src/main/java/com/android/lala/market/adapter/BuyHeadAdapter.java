package com.android.lala.market.adapter;

import android.content.Context;

import com.android.lala.R;
import com.android.lala.base.adapter.BaseListViewAdapter;
import com.android.lala.base.adapter.BaseListViewViewHolder;
import com.android.lala.login.bean.UserBean;

import java.util.List;

/**
 * Created by ZH on 2016/9/23.
 * 497239511@qq.com
 */
public class BuyHeadAdapter extends BaseListViewAdapter<UserBean> {

    public BuyHeadAdapter(Context context) {
        super(context);
    }

    public BuyHeadAdapter(Context context, List<UserBean> mLists) {
        super(context, mLists);
    }

    @Override
    public int getLayoutId(int postion) {
        return R.layout.item_market_buyhead;
    }

    @Override
    public void convert(BaseListViewViewHolder holder, UserBean userBean, int position) {
        holder.setImageUrl(R.id.market_buy_head,userBean.getPhoto());

    }
}
