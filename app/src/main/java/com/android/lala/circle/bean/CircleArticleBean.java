package com.android.lala.circle.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZH on 2016/9/28.
 * 497239511@qq.com
 */
public class CircleArticleBean implements Parcelable {


    /**
     * datetime : 14天前
     * background : http://lelelala.net/static/upload/201612016094ccef70eb4687bf0c54e5ad13637c.jpg
     * author : 夏未初﹌
     * photo : http://lelelala.net/static\ThirdLogin\changed\201611176c10d737cdda4a898b2f37398dba5206.jpg
     * clicknum : 22
     * id : 82
     * sort : 晒新物
     * title : 带上它们！去过你的精致生活
     * userId : 193
     * content : 1481792473415.html
     * commentnum : 0
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
    private String commentnum;

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

    public String getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(String commentnum) {
        this.commentnum = commentnum;
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
                ", commentnum='" + commentnum + '\'' +
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
        dest.writeString(this.commentnum);
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
        this.commentnum = in.readString();
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
