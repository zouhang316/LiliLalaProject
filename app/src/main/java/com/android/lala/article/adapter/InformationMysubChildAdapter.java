package com.android.lala.article.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.lala.R;
import com.android.lala.article.activity.ArticleActivity;
import com.android.lala.article.bean.ArticleViewBean;
import com.android.lala.base.adapter.BaseRecycleAdapter;
import com.android.lala.base.adapter.BaseRecycleViewHolder;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
public class InformationMysubChildAdapter extends BaseRecycleAdapter{
    private List<ArticleViewBean> mList;
    private Context context;

    public InformationMysubChildAdapter(List<ArticleViewBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    protected int getDataCount() {
        return mList.size();
    }

    @Override
    protected BaseRecycleViewHolder onCreateNormalViewHolder(ViewGroup parent, int viewType) {
        final ImageView background;
        final TextView title,date,looknum;
        View view= LayoutInflater.from(context).inflate(R.layout.item_mysubchild,parent,false);
        background= (ImageView) view.findViewById(R.id.mysubitem_child_background);
        title= (TextView) view.findViewById(R.id.mysubitem_child_title);
        looknum= (TextView) view.findViewById(R.id.mysubitem_child_looknum);
        date= (TextView) view.findViewById(R.id.mysubitem_child_date);

        BaseRecycleViewHolder holder=new BaseRecycleViewHolder(view) {
            @Override
            public void onBindViewHolder(int position) {
                title.setText(mList.get(position).getTitle());
                looknum.setText(mList.get(position).getClicknum());
                date.setText(mList.get(position).getDatetime());
                Picasso.with(context).load(mList.get(position).getBackground()).into(background);

            }

            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(context, ArticleActivity.class);
                intent.putExtra("id",mList.get(position).getId());
                intent.putExtra("toppic",mList.get(position).getBackground());
                context.startActivity(intent);
            }
        };
        return holder;
    }
}
