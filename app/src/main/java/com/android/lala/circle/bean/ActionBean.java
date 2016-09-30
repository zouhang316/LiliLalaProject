package com.android.lala.circle.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZH on 2016/9/26.
 * 497239511@qq.com
 */
public class ActionBean implements Parcelable {

    /**
     * datetime : 2016-05-24 11:46:09
     * background : http://lelelala.net/static/upload/20160429af285dc9af7649c89ac4a8b1cf488f9c.png
     * author : 作者1
     * content_pic : http://lelelala.net/static/upload/20160506a353b354bec54aa0a9bd5ceb2f4b9c1d.png
     * id : 2
     * title : 这是标题2
     * content : 这是内容1
     */

    private String datetime;
    private String background;
    private String author;
    private String content_pic;
    private String id;
    private String title;
    private String content;

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

    public String getContent_pic() {
        return content_pic;
    }

    public void setContent_pic(String content_pic) {
        this.content_pic = content_pic;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ActionBean{" +
                "datetime='" + datetime + '\'' +
                ", background='" + background + '\'' +
                ", author='" + author + '\'' +
                ", content_pic='" + content_pic + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
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
        dest.writeString(this.content_pic);
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.content);
    }

    public ActionBean() {
    }

    protected ActionBean(Parcel in) {
        this.datetime = in.readString();
        this.background = in.readString();
        this.author = in.readString();
        this.content_pic = in.readString();
        this.id = in.readString();
        this.title = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<ActionBean> CREATOR = new Parcelable.Creator<ActionBean>() {
        @Override
        public ActionBean createFromParcel(Parcel source) {
            return new ActionBean(source);
        }

        @Override
        public ActionBean[] newArray(int size) {
            return new ActionBean[size];
        }
    };
}
