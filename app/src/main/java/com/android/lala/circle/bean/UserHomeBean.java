package com.android.lala.circle.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZH on 2016/9/28.
 * 497239511@qq.com
 */
public class UserHomeBean implements Parcelable {

    /**
     * gexing : 这个人很懒，什么都没留下
     * name : sweet home
     * photo : http://lelelala.net/static/upload/20160928d344645e22874b19934b6e45fe1842f9.jpg
     * attention : 0
     * share : 1
     * userId : 2
     * fans : 1
     */

    private String gexing;
    private String name;
    private String photo;
    private int attention;
    private int share;
    private String userId;
    private int fans;

    public String getGexing() {
        return gexing;
    }

    public void setGexing(String gexing) {
        this.gexing = gexing;
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

    public int getAttention() {
        return attention;
    }

    public void setAttention(int attention) {
        this.attention = attention;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    @Override
    public String toString() {
        return "UserHomeBean{" +
                "gexing='" + gexing + '\'' +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", attention=" + attention +
                ", share=" + share +
                ", userId='" + userId + '\'' +
                ", fans=" + fans +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.gexing);
        dest.writeString(this.name);
        dest.writeString(this.photo);
        dest.writeInt(this.attention);
        dest.writeInt(this.share);
        dest.writeString(this.userId);
        dest.writeInt(this.fans);
    }

    public UserHomeBean() {
    }

    protected UserHomeBean(Parcel in) {
        this.gexing = in.readString();
        this.name = in.readString();
        this.photo = in.readString();
        this.attention = in.readInt();
        this.share = in.readInt();
        this.userId = in.readString();
        this.fans = in.readInt();
    }

    public static final Parcelable.Creator<UserHomeBean> CREATOR = new Parcelable.Creator<UserHomeBean>() {
        @Override
        public UserHomeBean createFromParcel(Parcel source) {
            return new UserHomeBean(source);
        }

        @Override
        public UserHomeBean[] newArray(int size) {
            return new UserHomeBean[size];
        }
    };
}
