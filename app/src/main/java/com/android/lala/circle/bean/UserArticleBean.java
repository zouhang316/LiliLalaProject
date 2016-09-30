package com.android.lala.circle.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZH on 2016/9/29.
 * 497239511@qq.com
 */
public class UserArticleBean implements Parcelable {

    /**
     * background : http://lelelala.net/static/upload/2016092897ddbfd3bae6491a808d368bad6aef2f.jpg
     * name : sweet home
     * photo : http://lelelala.net/static/upload/20160928d344645e22874b19934b6e45fe1842f9.jpg
     * clicknum : 138
     * id : 10
     * title : 十一长假怎么过？哩啦君给你好建议
     * userId : 2
     * Commentnum : 0
     * praise : 1
     */

    private String background;
    private String name;
    private String photo;
    private String clicknum;
    private String id;
    private String title;
    private String userId;
    private String Commentnum;
    private String praise;

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCommentnum() {
        return Commentnum;
    }

    public void setCommentnum(String Commentnum) {
        this.Commentnum = Commentnum;
    }

    public String getPraise() {
        return praise;
    }

    public void setPraise(String praise) {
        this.praise = praise;
    }

    @Override
    public String toString() {
        return "UserArticleBean{" +
                "background='" + background + '\'' +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", clicknum='" + clicknum + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", userId='" + userId + '\'' +
                ", Commentnum='" + Commentnum + '\'' +
                ", praise='" + praise + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.background);
        dest.writeString(this.name);
        dest.writeString(this.photo);
        dest.writeString(this.clicknum);
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.userId);
        dest.writeString(this.Commentnum);
        dest.writeString(this.praise);
    }

    public UserArticleBean() {
    }

    protected UserArticleBean(Parcel in) {
        this.background = in.readString();
        this.name = in.readString();
        this.photo = in.readString();
        this.clicknum = in.readString();
        this.id = in.readString();
        this.title = in.readString();
        this.userId = in.readString();
        this.Commentnum = in.readString();
        this.praise = in.readString();
    }

    public static final Parcelable.Creator<UserArticleBean> CREATOR = new Parcelable.Creator<UserArticleBean>() {
        @Override
        public UserArticleBean createFromParcel(Parcel source) {
            return new UserArticleBean(source);
        }

        @Override
        public UserArticleBean[] newArray(int size) {
            return new UserArticleBean[size];
        }
    };
}
