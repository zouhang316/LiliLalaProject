package com.android.lala.article.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/9/8.
 */
public class ArticleSearchBean implements Parcelable {

    /**
     * datetime : 125天前
     * background : http://lelelala.net/static/upload/20160506a353b354bec54aa0a9bd5ceb2f4b9c1d.png
     * author : 哩哩啦啦
     * sort : 智能硬件
     * id : 108
     * title : Google I/O大会带我们见识了哪些VR产品?
     */

    private String datetime;
    private String background;
    private String author;
    private String sort;
    private String id;
    private String title;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
        return "ArticleSearchBean{" +
                "datetime='" + datetime + '\'' +
                ", background='" + background + '\'' +
                ", author='" + author + '\'' +
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
        dest.writeString(this.background);
        dest.writeString(this.author);
        dest.writeString(this.sort);
        dest.writeString(this.id);
        dest.writeString(this.title);
    }

    public ArticleSearchBean() {
    }

    protected ArticleSearchBean(Parcel in) {
        this.datetime = in.readString();
        this.background = in.readString();
        this.author = in.readString();
        this.sort = in.readString();
        this.id = in.readString();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<ArticleSearchBean> CREATOR = new Parcelable.Creator<ArticleSearchBean>() {
        @Override
        public ArticleSearchBean createFromParcel(Parcel source) {
            return new ArticleSearchBean(source);
        }

        @Override
        public ArticleSearchBean[] newArray(int size) {
            return new ArticleSearchBean[size];
        }
    };
}
