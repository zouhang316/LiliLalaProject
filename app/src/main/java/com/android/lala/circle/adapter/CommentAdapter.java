package com.android.lala.circle.adapter;

import android.content.Context;

import com.android.lala.R;
import com.android.lala.base.adapter.BaseListViewAdapter;
import com.android.lala.base.adapter.BaseListViewViewHolder;
import com.android.lala.circle.bean.CommentBean3;
import com.android.lala.login.bean.UserBean;
import com.android.lala.market.bean.CommentBean;

import java.util.List;

/**
 * Created by ZH on 2016/9/27.
 * 497239511@qq.com
 */
public class CommentAdapter extends BaseListViewAdapter<CommentBean3>{
    //type 0  一级评论
    //type 1  一级评论下的回复
    public CommentAdapter(Context context) {
        super(context);
    }

    public CommentAdapter(Context context, List<CommentBean3> mLists,int type) {
        super(context, mLists,type);
    }

    @Override
    public int getLayoutId(int postion) {
        //TODO 缺少数据
        return R.layout.item_comment_circle;
    }

    @Override
    public void convert(BaseListViewViewHolder holder, CommentBean3 commentBean3, int position) {
        String commentNum=mLists.get(position).getCommentnum();
        holder.setText(R.id.name,mLists.get(position).getAutor());
        holder.setText(R.id.comment_content,mLists.get(position).getContent());
        holder.setText(R.id.zan_num,mLists.get(position).getPraise());
        holder.setText(R.id.time,mLists.get(position).getDatetime());
        if (type==0){
            //
            if (commentNum.equals("0")){
                holder.setText(R.id.reply_num,"回复");
            }else {
                holder.setText(R.id.reply_num,mLists.get(position).getCommentnum()+"条回复");
            }
        }
        holder.setImageUrl(R.id.head,mLists.get(position).getPhoto());
    }


}
