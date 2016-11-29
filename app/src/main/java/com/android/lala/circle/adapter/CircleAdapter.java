package com.android.lala.circle.adapter;

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
import com.android.lala.circle.activity.CircleActivity;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Map;

/**
 * Created by ZH on 2016/9/26.
 * 497239511@qq.com
 */
public class CircleAdapter extends BaseRecycleAdapter {
    private Context context;
    private List<Map<String,String>> mList;

    public CircleAdapter(Context context, List<Map<String,String>> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    protected int getDataCount() {
        return mList.size();
    }

    @Override
    protected BaseRecycleViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_circle,parent,false);
        final ImageView background= (ImageView) view.findViewById(R.id.circle_background);
        final TextView  sort= (TextView) view.findViewById(R.id.circle_sort);
        ImageView alphabg= (ImageView) view.findViewById(R.id.alpha_bg);
        alphabg.getBackground().setAlpha(50);
        BaseRecycleViewHolder holder=new BaseRecycleViewHolder(view) {
            @Override
            public void onBindViewHolder(int position) {

                switch (position){
                    case 0:
                        //background.setBackgroundResource(R.mipmap.shaixinwu);
                        background.setImageResource(R.mipmap.shaixinwu);
                        sort.setText("晒新物");
                        break;
                    case 1:
                       // background.setBackgroundResource(R.mipmap.wantbuy);
                        background.setImageResource(R.mipmap.wantbuy);
                        sort.setText("我想买");
                        break;
                    case 2:
                       // background.setBackgroundResource(R.mipmap.aifaxian);
                        background.setImageResource(R.mipmap.aifaxian);
                        sort.setText("爱发现");
                        break;
                }
            }
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(context, CircleActivity.class);
                intent.putExtra("sort",mList.get(position).get("sort"));
                view.getContext().startActivity(intent);
            }
        };
        return holder;
    }
}
