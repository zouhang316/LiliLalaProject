package com.android.lala.circle.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.lala.R;
import com.android.lala.base.adapter.BaseRecycleAdapter;
import com.android.lala.base.adapter.BaseRecycleViewHolder;
import com.android.lala.circle.activity.ActionActivity;
import com.android.lala.circle.bean.ActionBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ZH on 2016/9/26.
 * 497239511@qq.com
 */
public class ActionAdapter extends BaseRecycleAdapter {
    private Context context;
    private List<ActionBean> mList;

    public ActionAdapter(Context context, List<ActionBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    protected int getDataCount() {
        return mList.size();
    }

    @Override
    protected BaseRecycleViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
        final ImageView imageView;
        View view= LayoutInflater.from(context).inflate(R.layout.item_action,parent,false);
            imageView= (ImageView) view.findViewById(R.id.action_item_image);


        final BaseRecycleViewHolder holder=new BaseRecycleViewHolder(view) {
            @Override
            public void onBindViewHolder(int position) {
                Picasso.with(context).load(mList.get(position).getBackground()).into(imageView);
            }

            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(context, ActionActivity.class);
                intent.putExtra("id",mList.get(position).getId());
                view.getContext().startActivity(intent);
            }
        };
        return holder;
    }
}
