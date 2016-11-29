package com.android.lala.article.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lala.R;
import com.android.lala.article.bean.ArticleViewBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ZH on 2016/11/17.
 * 497239511@qq.com
 */
public class TestRecyclerviewAdapter  extends RecyclerView.Adapter<TestRecyclerviewAdapter.ViewHoder>{

    private List<ArticleViewBean> mList;
    private Context context;
    boolean  hasHead;

    public TestRecyclerviewAdapter(List<ArticleViewBean> mList, Context context,boolean hasHead) {
        this.mList = mList;
        this.context = context;
        this.hasHead=hasHead;
    }

    @Override
    public ViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHoder hoder;
        if (hasHead){
             hoder=new ViewHoder(LayoutInflater.from(context).inflate(R.layout.banner,parent,false));
        }else {
             hoder=new ViewHoder(LayoutInflater.from(context).inflate(R.layout.item_information_article,parent,false));
        }
        return hoder;
    }

    @Override
    public void onBindViewHolder(ViewHoder holder, int position) {
       holder.name.setText(mList.get(position).getChannels());
        holder.date.setText(mList.get(position).getDatetime());
        holder.clicknum.setText(mList.get(position).getClicknum());
        holder.title.setText(mList.get(position).getTitle());
        if (null != mList.get(position).getAuthor()){
            holder.author.setText(mList.get(position).getAuthor());
        }
        Picasso.with(context).load(mList.get(position).getBackground()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHoder extends RecyclerView.ViewHolder {
        TextView name,title,date,clicknum,author;
         ImageView imageView,back;
        public ViewHoder(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.information_article_ietm_channelname);
            title= (TextView) itemView.findViewById(R.id.information_article_ietm_title);
            date= (TextView) itemView.findViewById(R.id.information_article_ietm_time);
            clicknum= (TextView) itemView.findViewById(R.id.nformation_article_ietm_looknum);
            author= (TextView) itemView.findViewById(R.id.information_article_ietm_channelname);
            imageView= (ImageView) itemView.findViewById(R.id.information_article_ietm_image);
            back= (ImageView) itemView.findViewById(R.id.testback);
        }
    }
}
