package com.android.lala.market.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lala.R;
import com.android.lala.base.adapter.BaseRecycleAdapter;
import com.android.lala.base.adapter.BaseRecycleViewHolder;
import com.android.lala.market.activity.BuyActivity;
import com.android.lala.market.bean.MarketBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ZH on 2016/9/22.
 * 497239511@qq.com
 */
public class MarketNewAdapter extends BaseRecycleAdapter {
    private List<MarketBean> mList;
    private Context context;

    public MarketNewAdapter(List<MarketBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    protected int getDataCount() {
        return mList.size();
    }

    @Override
    protected BaseRecycleViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
        final TextView title,price;
        final ImageView image;
        View view= LayoutInflater.from(context).inflate(R.layout.market_new_item,parent,false);
        title=(TextView) view.findViewById(R.id.market_recycle_item_title);
        price=(TextView) view.findViewById(R.id.market_recycle_item_price);
        image=(ImageView) view.findViewById(R.id.market_recycle_item_image);
        BaseRecycleViewHolder holder=new BaseRecycleViewHolder(view) {
            @Override
            public void onBindViewHolder(int position) {
                title.setText(mList.get(position).getCom_name());
                price.setText(mList.get(position).getPrice());
                Picasso.with(context).load(mList.get(position).getShowcase_img1()).error(R.drawable.erro).into(image);
            }

            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(context, BuyActivity.class);
                intent.putExtra("id",mList.get(position).getId());
                view.getContext().startActivity(intent);
            }
        };
        return holder;
    }
}
