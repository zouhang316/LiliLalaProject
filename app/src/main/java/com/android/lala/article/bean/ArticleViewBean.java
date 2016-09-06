package com.android.lala.article.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/9/1.
 */
public class ArticleViewBean implements Parcelable {

    /**
     * datetime : 7天前
     * channel_ico : http://lelelala.net/static/upload/20160721e3e3b9e525b04040baf3595f2cd14812.jpg
     * author : 嗨！数码控
     * background : http://lelelala.net/static/upload/201608259a5f53d86fbe45349045b78783f5cab1.png
     * clicknum : 72
     * id : 464
     * title : 逆天！这款数据线防刀割！还能用来拖车、做引体向上！
     */

    private String datetime;
    private String channel_ico;
    private String author;
    private String background;
    private String clicknum;
    private String id;
    private String title;
    private String channel;
    private String channels;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getChannel_ico() {
        return channel_ico;
    }

    public void setChannel_ico(String channel_ico) {
        this.channel_ico = channel_ico;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getClicknum() {
        return clicknum;
    }

    public void setClicknum(String clicknum) {
        this.clicknum = clicknum;
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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

    @Override
    public String toString() {
        return "ArticleViewBean{" +
                "datetime='" + datetime + '\'' +
                ", channel_ico='" + channel_ico + '\'' +
                ", author='" + author + '\'' +
                ", background='" + background + '\'' +
                ", clicknum='" + clicknum + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", channel='" + channel + '\'' +
                ", channels='" + channels + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.datetime);
        dest.writeString(this.channel_ico);
        dest.writeString(this.author);
        dest.writeString(this.background);
        dest.writeString(this.clicknum);
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.channel);
        dest.writeString(this.channels);
    }

    public ArticleViewBean() {
    }

    protected ArticleViewBean(Parcel in) {
        this.datetime = in.readString();
        this.channel_ico = in.readString();
        this.author = in.readString();
        this.background = in.readString();
        this.clicknum = in.readString();
        this.id = in.readString();
        this.title = in.readString();
        this.channel = in.readString();
        this.channels = in.readString();
    }

    public static final Parcelable.Creator<ArticleViewBean> CREATOR = new Parcelable.Creator<ArticleViewBean>() {
        @Override
        public ArticleViewBean createFromParcel(Parcel source) {
            return new ArticleViewBean(source);
        }

        @Override
        public ArticleViewBean[] newArray(int size) {
            return new ArticleViewBean[size];
        }
    };
}
