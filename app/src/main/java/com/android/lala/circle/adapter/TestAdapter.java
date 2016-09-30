package com.android.lala.circle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.lala.R;

import java.util.List;

/**
 * Created by ZH on 2016/9/26.
 * 497239511@qq.com
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder>{
    private Context context;
    private List<String> stringList;

    public TestAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder=new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_circle,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
        }

    }
}
