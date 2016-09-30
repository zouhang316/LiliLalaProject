package com.android.lala.circle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lala.R;
import com.android.lala.base.adapter.BaseRecycleAdapter;
import com.android.lala.base.adapter.BaseRecycleViewHolder;
import com.android.lala.login.bean.UserBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ZH on 2016/9/28.
 * 497239511@qq.com
 */
public class ShareManChildAdapter extends BaseRecycleAdapter {
    private Context context;
    private List<String> mList;

    public ShareManChildAdapter(Context context, List<String> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    protected int getDataCount() {
        return mList.size();
    }

    @Override
    protected BaseRecycleViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_shareman_child,parent,false);
        final ImageView background= (ImageView) view.findViewById(R.id.shareman_child_background);
        TextView title= (TextView) view.findViewById(R.id.shareman_child_title);
        BaseRecycleViewHolder holder=new BaseRecycleViewHolder(view) {
            @Override
            public void onBindViewHolder(int position) {
            }

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(context,position+"",Toast.LENGTH_SHORT).show();
            }
        };
        return holder;
    }
}
