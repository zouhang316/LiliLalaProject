package com.android.lala.circle.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZH on 2016/9/28.
 * 497239511@qq.com
 */
public class CircleArticleBean implements Parcelable {


    /**
     * datetime : 1小时前
     * background : http://lelelala.net/static/upload/201609292215dd9ec70341c89ca3a2fdc9889b13.jpg
     * author : 一吃就胖的胖次
     * photo : http://lelelala.net/static/upload/20160929649112fe09864dfda41eb2c840724ec3.jpg
     * clicknum : 30
     * id : 12
     * sort : 我想买
     * title : 送这些零食来俘获吃货女友的心，她一定会嫁给你
     * userId : 128
     * content : 1475144075121.html
     */

    private String datetime;
    private String background;
    private String author;
    private String photo;
    private String clicknum;
    private String id;
    private String sort;
    private String title;
    private String userId;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CircleArticleBean{" +
                "datetime='" + datetime + '\'' +
                ", background='" + background + '\'' +
                ", author='" + author + '\'' +
                ", photo='" + photo + '\'' +
                ", clicknum='" + clicknum + '\'' +
                ", id='" + id + '\'' +
                ", sort='" + sort + '\'' +
                ", title='" + title + '\'' +
                ", userId='" + userId + '\'' +
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
        dest.writeString(this.photo);
        dest.writeString(this.clicknum);
        dest.writeString(this.id);
        dest.writeString(this.sort);
        dest.writeString(this.title);
        dest.writeString(this.userId);
        dest.writeString(this.content);
    }

    public CircleArticleBean() {
    }

    protected CircleArticleBean(Parcel in) {
        this.datetime = in.readString();
        this.background = in.readString();
        this.author = in.readString();
        this.photo = in.readString();
        this.clicknum = in.readString();
        this.id = in.readString();
        this.sort = in.readString();
        this.title = in.readString();
        this.userId = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<CircleArticleBean> CREATOR = new Parcelable.Creator<CircleArticleBean>() {
        @Override
        public CircleArticleBean createFromParcel(Parcel source) {
            return new CircleArticleBean(source);
        }

        @Override
        public CircleArticleBean[] newArray(int size) {
            return new CircleArticleBean[size];
        }
    };
}
