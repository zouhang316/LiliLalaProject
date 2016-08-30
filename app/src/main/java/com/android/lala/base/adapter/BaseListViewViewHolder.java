package com.android.lala.base.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/***
 * @author xiaoshi email:emotiona_xiaoshi@126.com
 * @2015年7月14日
 */
public class BaseListViewViewHolder {
    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private Context mContext;

    /***
     * 构造方法
     *
     * @param position
     * @param context
     * @param parent
     * @param layoutId
     */
    public BaseListViewViewHolder(int position, Context context, ViewGroup parent, int layoutId) {
        this.mViews = new SparseArray<View>();
        this.mPosition = position;
        this.mContext = context;
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    /**
     * 获取ViewHolder
     *
     * @param position
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @return
     */
    public static BaseListViewViewHolder getHolder(int position, Context context, View convertView, ViewGroup parent, int layoutId) {
        if (convertView == null) {
            return new BaseListViewViewHolder(position, context, parent, layoutId);
        } else {
            BaseListViewViewHolder holder = (BaseListViewViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    public View getConvertView() {

        return mConvertView;
    }

    /***
     * 根据id获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        return retrieveView(viewId);
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T retrieveView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 设置TextView文本
     *
     * @param viewId
     * @param text
     * @return
     */
    public BaseListViewViewHolder setText(int viewId, String text) {
        TextView tv = retrieveView(viewId);
        tv.setText(text);
        return this;
    }

    /***
     * @param viewId
     * @param color
     * @return
     * @2015年8月26日下午2:38:29
     * @author emotiona
     * @Email emotiona_xiaoshi@126.com
     */
    public BaseListViewViewHolder setTextColor(int viewId, int color) {
        TextView tv = retrieveView(viewId);
        tv.setTextColor(color);
        return this;
    }

    /***
     * @param viewId
     * @param typeface
     * @return
     * @2015年8月26日下午3:07:32
     * @author emotiona
     * @Email emotiona_xiaoshi@126.com
     */
    public BaseListViewViewHolder setTextTypeface(int viewId, Typeface typeface) {
        TextView view = retrieveView(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        return this;
    }

    /***
     * @param viewId
     * @param resId
     * @return
     * @2015年8月26日下午3:08:05
     * @author emotiona
     * @Email emotiona_xiaoshi@126.com
     */
    public BaseListViewViewHolder setImageViewResource(int viewId, int resId) {
        ImageView iv = retrieveView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    /***
     * @param viewId
     * @param bitmap
     * @return
     * @2015年8月26日下午3:08:30
     * @author emotiona
     * @Email emotiona_xiaoshi@126.com
     */
    public BaseListViewViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView iv = retrieveView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * @param viewId
     * @param url
     * @return
     * @2015年8月26日下午3:09:17
     * @author emotiona
     * @Email emotiona_xiaoshi@126.com
     */
    public BaseListViewViewHolder setImageUrl(int viewId, String url) {
        final ImageView iv = retrieveView(viewId);
         Picasso.with(mContext).load(url).into(iv);
        // 图像处理
//        Picasso.with(mContext).load(url).resize(72, 72)//设置显示大小
//                .centerCrop().into(iv);
        return this;
    }

    /***
     * @param viewId
     * @param requestBuilder
     * @return
     * @2015年8月26日下午2:42:38
     * @author emotiona
     * @Email emotiona_xiaoshi@126.com
     */
    public BaseListViewViewHolder setImageBuilder(int viewId, RequestCreator requestBuilder) {
        ImageView view = retrieveView(viewId);
        requestBuilder.into(view);
        return this;
    }

    /***
     * @param viewId
     * @param value
     * @return
     * @2015年8月26日下午2:47:58
     * @author emotiona
     * @Email emotiona_xiaoshi@126.com
     */
    @SuppressLint("NewApi")
    public BaseListViewViewHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            retrieveView(viewId).setAlpha(value);
        } else {
            AlphaAnimation alpha = new AlphaAnimation(value, value);
            alpha.setDuration(0);
            alpha.setFillAfter(true);
            retrieveView(viewId).startAnimation(alpha);
        }
        return this;
    }

    /***
     * @param viewId
     * @param visible
     * @return
     * @2015年8月26日下午2:51:48
     * @author emotiona
     * @Email emotiona_xiaoshi@126.com
     */
    public BaseListViewViewHolder setVisible(int viewId, Boolean visible) {
        View view = retrieveView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /***
     * @param viewId
     * @return
     * @2015年8月26日下午3:01:50
     * @Email emotiona_xiaoshi@126.com
     */
    public BaseListViewViewHolder linkify(int viewId) {
        TextView view = retrieveView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    /**
     * @param viewId
     * @param progress
     * @return
     * @2015年8月26日下午3:10:07
     * @author emotiona
     * @Email emotiona_xiaoshi@126.com
     */
    public BaseListViewViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = retrieveView(viewId);
        view.setProgress(progress);
        return this;
    }

    /***
     * @param viewId
     * @param progress
     * @param max
     * @return
     * @2015年8月26日下午3:10:40
     * @author emotiona
     * @Email emotiona_xiaoshi@126.com
     */
    public BaseListViewViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = retrieveView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    /***
     * @param viewId
     * @param max
     * @return
     * @2015年8月26日下午3:11:22
     * @author emotiona
     * @Email emotiona_xiaoshi@126.com
     */
    public BaseListViewViewHolder setMax(int viewId, int max) {
        ProgressBar view = retrieveView(viewId);
        view.setMax(max);
        return this;
    }

    /***
     * @param viewId
     * @param rating
     * @return
     * @2015年8月26日下午3:12:03
     * @author emotiona
     * @Email emotiona_xiaoshi@126.com
     */
    public BaseListViewViewHolder setRating(int viewId, float rating) {
        RatingBar view = retrieveView(viewId);
        view.setRating(rating);
        return this;
    }

    /***
     * @param viewId
     * @param rating
     * @param max
     * @return
     * @2015年8月26日下午3:12:35
     * @author emotiona
     * @Email emotiona_xiaoshi@126.com
     */
    public BaseListViewViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = retrieveView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    /***
     * @param viewId
     * @param tag
     * @return
     * @2015年8月26日下午3:13:09
     * @author emotiona
     * @Email emotiona_xiaoshi@126.com
     */
    public BaseListViewViewHolder setTag(int viewId, Object tag) {
        View view = retrieveView(viewId);
        view.setTag(tag);
        return this;
    }

    /***
     * @param viewId
     * @param key
     * @param tag
     * @return
     * @2015年8月26日下午3:13:47
     * @author emotiona
     * @Email emotiona_xiaoshi@126.com
     */
    public BaseListViewViewHolder setTag(int viewId, int key, Object tag) {
        View view = retrieveView(viewId);
        view.setTag(key, tag);
        return this;
    }

    /***
     * @param viewId
     * @param checked
     * @return
     * @2015年8月26日下午3:14:35
     * @author emotiona
     * @Email emotiona_xiaoshi@126.com
     */
    public BaseListViewViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = retrieveView(viewId);
        view.setChecked(checked);
        return this;
    }

    /***
     * @param viewId
     * @param adapter
     * @return
     * @2015年8月26日下午3:15:39
     * @author emotiona
     * @Email emotiona_xiaoshi@126.com
     */
    public BaseListViewViewHolder setAdapter(int viewId, Adapter adapter) {
        AdapterView view = retrieveView(viewId);
        view.setAdapter(adapter);
        return this;
    }

    /**
     * @param viewId
     * @param listener
     * @return
     * @2015年8月26日下午3:16:12
     * @author emotiona
     * @Email emotiona_xiaoshi@126.com
     */
    public BaseListViewViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = retrieveView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * @param viewId
     * @param listener
     * @return
     * @2015年8月26日下午3:16:37
     * @author emotiona
     * @Email emotiona_xiaoshi@126.com
     */
    public BaseListViewViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = retrieveView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    /***
     * @param viewId
     * @param listener
     * @return
     * @2015年8月26日下午3:17:13
     * @author emotiona
     * @Email emotiona_xiaoshi@126.com
     */
    public BaseListViewViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = retrieveView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    /***
     * @param viewId
     * @param color
     * @return
     * @2015年8月26日下午2:33:26
     * @author emotiona
     * @Email emotiona_xiaoshi@126.com
     */
    public BaseListViewViewHolder setBackgroundColor(int viewId, int color) {
        View view = retrieveView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public BaseListViewViewHolder setClickable(int viewId, boolean is){
        View view=retrieveView(viewId);
        view.setClickable(is);
        return this;
    }
}
