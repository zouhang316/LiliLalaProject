package com.android.lala.circle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.base.adapter.BaseListViewAdapter;
import com.android.lala.base.adapter.BaseListViewViewHolder;
import com.android.lala.base.adapter.BaseRecycleAdapter;
import com.android.lala.base.adapter.BaseRecycleViewHolder;
import com.android.lala.circle.bean.DarenBean;
import com.android.lala.login.bean.UserBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ZH on 2016/12/16.
 * 497239511@qq.com
 */
public class DarenAdapter extends BaseRecycleAdapter {
    private Context context;
    private List<DarenBean> beanList;

    public DarenAdapter(List<DarenBean> beanList, Context context) {
        this.beanList = beanList;
        this.context = context;
    }

    @Override
    protected int getDataCount() {
        return beanList.size();
    }

    @Override
    protected BaseRecycleViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
        final TextView sort,name,title,lookNum;
        final ImageView background;
        View view= LayoutInflater.from(context).inflate(R.layout.item_circle_article,null);
        sort= (TextView) view.findViewById(R.id.circle_name);
        name= (TextView) view.findViewById(R.id.author);
        title= (TextView) view.findViewById(R.id.title);
        lookNum= (TextView) view.findViewById(R.id.looknum);
        background= (ImageView) view.findViewById(R.id.background);

        final BaseRecycleViewHolder holder=new BaseRecycleViewHolder(view) {
            @Override
            public void onBindViewHolder(int position) {
                sort.setText("#"+beanList.get(position).getSort());
                name.setText(beanList.get(position).getAuthor());
                title.setText(beanList.get(position).getTitle());
                lookNum.setText(beanList.get(position).getClicknum());
                Picasso.with(context).load(beanList.get(position).getBackground()).into(background);
            }

            @Override
            public void onItemClick(View view, int position) {

            }
        };
        return holder;
    }
}
