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
 * Created by ZH on 2016/9/20.
 * 497239511@qq.com
 */
public class MarketOldAdapter extends BaseRecycleAdapter {
    private List<MarketBean> mList;
    private Context mContext;


    public MarketOldAdapter(List<MarketBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    protected int getDataCount() {
        return mList.size();
    }

    @Override
    protected BaseRecycleViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
         final TextView title,price,likenum;
         final ImageView image;
        View view= LayoutInflater.from(mContext).inflate(R.layout.market_old_item,parent,false);
        title=(TextView) view.findViewById(R.id.market_recycle_item_title);
        price=(TextView) view.findViewById(R.id.market_recycle_item_price);
       // likenum=(TextView) view.findViewById(R.id.market_recycle_item_likenum);
        image=(ImageView) view.findViewById(R.id.market_recycle_item_image);
        BaseRecycleViewHolder holder=new BaseRecycleViewHolder(view) {
            @Override
            public void onBindViewHolder(int position) {
                title.setText(mList.get(position).getCom_name());
                price.setText(mList.get(position).getPrice());
                Picasso.with(mContext).load(mList.get(position).getShowcase_img1()).into(image);
            }

            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(mContext, BuyActivity.class);
                intent.putExtra("id",mList.get(position).getId());
                view.getContext().startActivity(intent);
            }
        };
        return holder;
    }

}
