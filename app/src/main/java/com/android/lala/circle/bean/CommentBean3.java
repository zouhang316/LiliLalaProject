package com.android.lala.circle.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZH on 2016/12/13.
 * 497239511@qq.com
 */
public class CommentBean3 implements Parcelable {

    /**
     * datetime : 11天前
     * photo : http://lelelala.net/static/img/icon/icon_default.png
     * circle_contentId : 88
     * id : 722
     * autor : 游客26606
     * content : 放低身段，能看到更真实的自己
     * praise : 2
     * commentnum : 2
     */
    private String datetime;
    private String photo;
    private String circle_contentId;
    private String id;
    private String autor;
    private String content;
    private String praise;
    private String commentnum;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCircle_contentId() {
        return circle_contentId;
    }

    public void setCircle_contentId(String circle_contentId) {
        this.circle_contentId = circle_contentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPraise() {
        return praise;
    }

    public void setPraise(String praise) {
        this.praise = praise;
    }

    public String getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(String commentnum) {
        this.commentnum = commentnum;
    }

    @Override
    public String toString() {
        return "CommentBean3{" +
                "datetime='" + datetime + '\'' +
                ", photo='" + photo + '\'' +
                ", circle_contentId='" + circle_contentId + '\'' +
                ", id='" + id + '\'' +
                ", autor='" + autor + '\'' +
                ", content='" + content + '\'' +
                ", praise='" + praise + '\'' +
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
        dest.writeString(this.photo);
        dest.writeString(this.circle_contentId);
        dest.writeString(this.id);
        dest.writeString(this.autor);
        dest.writeString(this.content);
        dest.writeString(this.praise);
        dest.writeString(this.commentnum);
    }

    public CommentBean3() {
    }

    protected CommentBean3(Parcel in) {
        this.datetime = in.readString();
        this.photo = in.readString();
        this.circle_contentId = in.readString();
        this.id = in.readString();
        this.autor = in.readString();
        this.content = in.readString();
        this.praise = in.readString();
        this.commentnum = in.readString();
    }

    public static final Parcelable.Creator<CommentBean3> CREATOR = new Parcelable.Creator<CommentBean3>() {
        @Override
        public CommentBean3 createFromParcel(Parcel source) {
            return new CommentBean3(source);
        }

        @Override
        public CommentBean3[] newArray(int size) {
            return new CommentBean3[size];
        }
    };
}
