package com.android.lala.circle.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lala.R;
import com.android.lala.base.adapter.BaseListViewAdapter;
import com.android.lala.base.adapter.BaseListViewViewHolder;
import com.android.lala.login.bean.UserBean;
import com.android.lala.view.CircleImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZH on 2016/9/28.
 * 497239511@qq.com
 */
public class ShareManAdapter extends BaseAdapter {
    private List<String> stringList;
    private Context context;

    public ShareManAdapter(List<String> stringList, Context context) {
        this.stringList = stringList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {
        return stringList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView==null){
            vh=new ViewHolder();
            convertView=View.inflate(context, R.layout.item_sharemam,null);
            vh.fansnum= (TextView) convertView.findViewById(R.id.shareman_fansnum);
            vh.head= (CircleImageView) convertView.findViewById(R.id.shareman_head);
            vh.name= (TextView) convertView.findViewById(R.id.shareman_name);
            vh.recyclerView= (RecyclerView) convertView.findViewById(R.id.shareman_child_recycleview);
            vh.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }
        List<String> data=new ArrayList<>();
        for (int i = 0; i <3 ; i++) {
            data.add(i+"");
        }
        ShareManChildAdapter adapter=new ShareManChildAdapter(context,data);
        vh.recyclerView.setAdapter(adapter);
        vh.head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击头像",Toast.LENGTH_SHORT).show();
            }
        });



        return convertView;
    }
    class ViewHolder{
        CircleImageView head;
        TextView name,fansnum;
        RecyclerView recyclerView;
    }
}
