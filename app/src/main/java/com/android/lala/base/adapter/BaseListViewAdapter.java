package com.android.lala.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.util.List;

/***
 * @param <T>
 * @author xiaoshi email:emotiona_xiaoshi@126.com
 * @2015年8月25日
 */
public abstract class BaseListViewAdapter<T> extends BaseAdapter {
    protected List<T> mLists;
    protected Context mContext;
    protected LayoutInflater mInflater;

    public BaseListViewAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }
    public BaseListViewAdapter(Context context, List<T> mLists) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mLists=mLists;
    }
    public List<T> getmLists() {
        return mLists;
    }

    public void setmLists(List<T> mLists) {
        this.mLists = mLists;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mLists.size();
    }

    @Override
    public T getItem(int position) {
        return mLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseListViewViewHolder holder = BaseListViewViewHolder.getHolder(position, mContext, convertView, parent, getLayoutId(position));
        convert(holder, getItem(position), position);
        return holder.getConvertView();
    }

    /***
     * 设置布局文件
     *
     * @return
     */
    public abstract int getLayoutId(int postion);

    /**
     * 处理业务
     *
     * @param holder
     * @param t
     */
    public abstract void convert(BaseListViewViewHolder holder, T t, int position);

    /***
     * 统一Toast
     *
     * @param msg
     */
    public void showToast(String msg) {
        Toast.makeText(mContext, msg + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isEnabled(int position) {
        return position < mLists.size();
    }

    /***
     * @param elem
     * @2015年8月26日下午3:34:12
     * @author emotiona
     */
    public void add(T elem) {
        mLists.add(elem);
        notifyDataSetChanged();
    }

    /***
     * @param elem
     * @2015年8月26日下午3:33:41
     * @author emotiona
     */
    public void addAll(List<T> elem) {
        mLists.addAll(elem);
        notifyDataSetChanged();
    }

    /***
     * @param oldElem
     * @param newElem
     * @2015年8月26日下午3:33:29
     * @author emotiona
     */
    public void set(T oldElem, T newElem) {
        set(mLists.indexOf(oldElem), newElem);
    }

    /***
     * @param index
     * @param elem
     * @2015年8月26日下午3:33:02
     * @author emotiona
     */
    public void set(int index, T elem) {
        mLists.set(index, elem);
        notifyDataSetChanged();
    }

    /***
     * 删除某一个对象
     * @param elem
     */
    public void remove(T elem) {
        mLists.remove(elem);
        notifyDataSetChanged();
    }

    /***
     * 删除数据 根据索引
     * @param index
     */
    public void remove(int index) {
        mLists.remove(index);
        notifyDataSetChanged();
    }

    /***
     * 替换所有数据
     * @param elem
     */
    public void replaceAll(List<T> elem) {
        mLists.clear();
        mLists.addAll(elem);
        notifyDataSetChanged();
    }

    /***
     * 检测是否包含某个对象
     * @param elem
     * @return
     */
    public boolean contains(T elem) {
        return mLists.contains(elem);
    }

    /***
     * 清空数据
     */
    public void clear() {
        mLists.clear();
        notifyDataSetChanged();
    }
}
