package com.android.lala.article.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/8/30.
 */
public class ArticleBean implements Parcelable {

    /**
     * datetime : 37天前
     * channels : 嗨！数码控
     * channel_ico : http://lelelala.net/static/upload/20160721e3e3b9e525b04040baf3595f2cd14812.jpg
     * article_num : 10
     * sort : 智能硬件
     * subscription : 0
     * title : 想入手VR装逼，看这一篇就够了（国外篇）
     * content : 1472630348400.html
     */

    private String datetime;
    private String channels;
    private String channel_ico;
    private int article_num;
    private String sort;
    private String subscription;
    private String title;
    private String content;

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

    public String getChannel_ico() {
        return channel_ico;
    }

    public void setChannel_ico(String channel_ico) {
        this.channel_ico = channel_ico;
    }

    public int getArticle_num() {
        return article_num;
    }

    public void setArticle_num(int article_num) {
        this.article_num = article_num;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.datetime);
        dest.writeString(this.channels);
        dest.writeString(this.channel_ico);
        dest.writeInt(this.article_num);
        dest.writeString(this.sort);
        dest.writeString(this.subscription);
        dest.writeString(this.title);
        dest.writeString(this.content);
    }

    public ArticleBean() {
    }

    protected ArticleBean(Parcel in) {
        this.datetime = in.readString();
        this.channels = in.readString();
        this.channel_ico = in.readString();
        this.article_num = in.readInt();
        this.sort = in.readString();
        this.subscription = in.readString();
        this.title = in.readString();
        this.content = in.readString();
    }

    public static final Creator<ArticleBean> CREATOR = new Creator<ArticleBean>() {
        @Override
        public ArticleBean createFromParcel(Parcel source) {
            return new ArticleBean(source);
        }

        @Override
        public ArticleBean[] newArray(int size) {
            return new ArticleBean[size];
        }
    };

    @Override
    public String toString() {
        return "ArticleBean{" +
                "datetime='" + datetime + '\'' +
                ", channels='" + channels + '\'' +
                ", channel_ico='" + channel_ico + '\'' +
                ", article_num=" + article_num +
                ", sort='" + sort + '\'' +
                ", subscription=" + subscription +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
