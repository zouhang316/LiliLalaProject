package com.android.lala.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/***
 * 实现 对ViewHolder的统一封装
 */
public abstract class BaseRecycleViewHolder extends RecyclerView.ViewHolder {
    public BaseRecycleViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v, getAdapterPosition());
            }
        });
    }

    public abstract void onBindViewHolder(int position);
    public abstract void onItemClick(View view, int position);
}
