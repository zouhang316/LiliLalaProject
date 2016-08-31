package com.android.lala.infomation.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/8/30.
 */
public class ArticleBean implements Parcelable {
    private String id;
    private String title;
    private String channels;
    private String channel_ico;
    private String datetime;
    private String sort;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getmTitle() {
        return title;
    }

    public void setmTitle(String mTitle) {
        this.title = mTitle;
    }

    public String getChannel() {
        return channels;
    }

    public void setChannel(String channel) {
        this.channels = channel;
    }

    public String getChannel_icon() {
        return channel_ico;
    }

    public void setChannel_icon(String channel_icon) {
        this.channel_ico = channel_icon;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "ArticleBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", channels='" + channels + '\'' +
                ", channel_ico='" + channel_ico + '\'' +
                ", datetime='" + datetime + '\'' +
                ", sort='" + sort + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.channels);
        dest.writeString(this.channel_ico);
        dest.writeString(this.datetime);
        dest.writeString(this.sort);
        dest.writeString(this.content);
    }

    public ArticleBean() {
    }

    protected ArticleBean(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.channels = in.readString();
        this.channel_ico = in.readString();
        this.datetime = in.readString();
        this.sort = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<ArticleBean> CREATOR = new Parcelable.Creator<ArticleBean>() {
        @Override
        public ArticleBean createFromParcel(Parcel source) {
            return new ArticleBean(source);
        }

        @Override
        public ArticleBean[] newArray(int size) {
            return new ArticleBean[size];
        }
    };
}
