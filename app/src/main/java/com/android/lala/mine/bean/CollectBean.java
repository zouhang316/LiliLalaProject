package com.android.lala.mine.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZH on 2016/11/22.
 * 497239511@qq.com
 */
public class CollectBean implements Parcelable {
    /**
     * datetime : 1小时前
     * channels : 雷锋网
     * background : http://lelelala.net/static/upload/20161122004df3b523394cdba7f2151f04055faa.png
     * sort : 黑科技
     * id : 732
     * title : 现实版「盗梦空间」！这个黑科技就是要让你做白日梦！
     */

    private String datetime;
    private String channels;
    private String background;
    private String sort;
    private String id;
    private String title;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "CollectBean{" +
                "datetime='" + datetime + '\'' +
                ", channels='" + channels + '\'' +
                ", background='" + background + '\'' +
                ", sort='" + sort + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.datetime);
        dest.writeString(this.channels);
        dest.writeString(this.background);
        dest.writeString(this.sort);
        dest.writeString(this.id);
        dest.writeString(this.title);
    }

    public CollectBean() {
    }

    protected CollectBean(Parcel in) {
        this.datetime = in.readString();
        this.channels = in.readString();
        this.background = in.readString();
        this.sort = in.readString();
        this.id = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<CollectBean> CREATOR = new Parcelable.Creator<CollectBean>() {
        @Override
        public CollectBean createFromParcel(Parcel source) {
            return new CollectBean(source);
        }

        @Override
        public CollectBean[] newArray(int size) {
            return new CollectBean[size];
        }
    };
}
