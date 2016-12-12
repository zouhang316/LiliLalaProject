package com.android.lala.market.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.lala.R;

import java.util.List;

/**
 * Created by ZH on 2016/12/7.
 * 497239511@qq.com
 */
public class ClassficationAdapter extends BaseAdapter {
    private Context context;
    private String [] tags;

    public ClassficationAdapter(Context context, String[] tags) {
        this.context = context;
        this.tags = tags;
    }

    @Override
    public int getCount() {
        return tags.length;
    }

    @Override
    public Object getItem(int position) {
        return tags[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= View.inflate(context,R.layout.item_classfication,null);
        TextView sort= (TextView) convertView.findViewById(R.id.sort);
        sort.setText(tags[position]);
        return convertView;
    }
}
