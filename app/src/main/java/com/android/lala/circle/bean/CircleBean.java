package com.android.lala.circle.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZH on 2016/9/26.
 * 497239511@qq.com
 */
public class CircleBean implements Parcelable {

    /**
     * datetime : 2016-05-24 17:21:45
     * background : http://lelelala.net/static/upload/2016050612a9e7bdca624d6b9cd3a2b1ca4f56d2.jpg
     * author : 7
     * content_pic : http://lelelala.net/static/upload/20160506c023bc2b6cf14549954d2ad3763c64e5.jpg
     * clicknum : 3
     * id : 1
     * title : 标题
     * content : 内容
     * likes : 1
     */

    private String datetime;
    private String background;
    private String author;
    private String content_pic;
    private String clicknum;
    private String id;
    private String title;
    private String content;
    private String likes;
    private String praise;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPraise() {
        return praise;
    }

    public void setPraise(String praise) {
        this.praise = praise;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "CircleBean{" +
                "datetime='" + datetime + '\'' +
                ", background='" + background + '\'' +
                ", author='" + author + '\'' +
                ", content_pic='" + content_pic + '\'' +
                ", clicknum='" + clicknum + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", likes='" + likes + '\'' +
                ", praise='" + praise + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    public CircleBean() {
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
        dest.writeString(this.clicknum);
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeString(this.likes);
        dest.writeString(this.praise);
        dest.writeString(this.photo);
    }

    protected CircleBean(Parcel in) {
        this.datetime = in.readString();
        this.background = in.readString();
        this.author = in.readString();
        this.content_pic = in.readString();
        this.clicknum = in.readString();
        this.id = in.readString();
        this.title = in.readString();
        this.content = in.readString();
        this.likes = in.readString();
        this.praise = in.readString();
        this.photo = in.readString();
    }

    public static final Creator<CircleBean> CREATOR = new Creator<CircleBean>() {
        @Override
        public CircleBean createFromParcel(Parcel source) {
            return new CircleBean(source);
        }

        @Override
        public CircleBean[] newArray(int size) {
            return new CircleBean[size];
        }
    };
}
