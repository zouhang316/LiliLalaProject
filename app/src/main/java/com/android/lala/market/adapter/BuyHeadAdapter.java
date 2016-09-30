package com.android.lala.market.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.lala.R;
import com.android.lala.base.adapter.BaseListViewAdapter;
import com.android.lala.base.adapter.BaseListViewViewHolder;
import com.android.lala.base.adapter.BaseRecycleAdapter;
import com.android.lala.base.adapter.BaseRecycleViewHolder;
import com.android.lala.login.bean.UserBean;
import com.android.lala.view.CircleImageView;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

/**
 * Created by ZH on 2016/9/23.
 * 497239511@qq.com
 */
public class BuyHeadAdapter extends BaseRecycleAdapter {
    private Context context;
    private List<Map<String,String>> mList;

    public BuyHeadAdapter(Context context, List<Map<String,String>> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    protected int getDataCount() {
        if (mList.size()>=8){
            return 8;
        }else {
            return mList.size();
        }
    }

    @Override
    protected BaseRecycleViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_market_buyhead,parent,false);
        final CircleImageView head= (CircleImageView) view.findViewById(R.id.market_buy_head);
        BaseRecycleViewHolder holder=new BaseRecycleViewHolder(view) {
            @Override
            public void onBindViewHolder(int position) {
                Picasso.with(context).load(mList.get(0).get("pic")).into(head);
            }

            @Override
            public void onItemClick(View view, int position) {

            }
        };
        return holder;
    }
}
