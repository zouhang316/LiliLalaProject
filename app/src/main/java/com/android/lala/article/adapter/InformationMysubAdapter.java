package com.android.lala.article.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.view.CircleImageView;

/**
 * Created by Administrator on 2016/9/9.
 * paramers channle_ID name articlenum channle_icon
 */
public class InformationMysubAdapter extends BaseAdapter {
    private Context context;



    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView==null){
            vh=new ViewHolder();
            convertView=View.inflate(context, R.layout.item_mysub,parent);
            vh.aeticlenum= (TextView) convertView.findViewById(R.id.mysubitem_articlenum);
            vh.head= (CircleImageView) convertView.findViewById(R.id.mysubitem_head);
            vh.name= (TextView) convertView.findViewById(R.id.mysubitem_name);
            vh.recyclerView= (RecyclerView) convertView.findViewById(R.id.mysubitem_recycleview);
            convertView.setTag(vh);
        }else {
            vh= (ViewHolder) convertView.getTag();
        }




        return convertView;
    }
    class ViewHolder{
        CircleImageView head;
        TextView name,aeticlenum;
        RecyclerView recyclerView;
    }
}
