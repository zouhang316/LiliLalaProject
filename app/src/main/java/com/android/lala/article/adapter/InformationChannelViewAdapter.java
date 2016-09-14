package com.android.lala.article.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.article.activity.ChannelActivity;
import com.android.lala.article.bean.ChannelViewBean;
import com.android.lala.base.adapter.BaseRecycleAdapter;
import com.android.lala.base.adapter.BaseRecycleViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/9/5.
 */
public class InformationChannelViewAdapter extends BaseRecycleAdapter{
    private List<ChannelViewBean> mList;
    private Context context;

    public InformationChannelViewAdapter(Context context,List<ChannelViewBean> mList) {
        this.context = context;
        this.mList=mList;
    }

    @Override
    protected int getDataCount() {
        return mList.size();
    }

    @Override
    protected BaseRecycleViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {

        final TextView title,fansnum;
        final ImageView imageView;
        final View view= LayoutInflater.from(context).inflate(R.layout.item_information_channel,parent,false);
        title= (TextView) view.findViewById(R.id.channal_title);
        fansnum= (TextView) view.findViewById(R.id.channal_fansnum);
        imageView= (ImageView) view.findViewById(R.id.channal_image);
        final BaseRecycleViewHolder holder=new BaseRecycleViewHolder(view) {
            @Override
            public void onBindViewHolder(int position) {
                title.setText(mList.get(position).getChannels());
                fansnum.setText(mList.get(position).getSubscription());
                Picasso.with(context).load(mList.get(position).getChannel_background()).into(imageView);

            }

            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(context, ChannelActivity.class);
                intent.putExtra("data",mList.get(position));
                view.getContext().startActivity(intent);
            }
        };

        return holder;
    }
}
