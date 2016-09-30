package com.android.lala.market.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ZH on 2016/9/29.
 * 497239511@qq.com
 */
public class CommentBean implements Parcelable {

    /**
     * datetime : 1分钟前
     * photo : http://shunxe.oss-cn-shenzhen.aliyuncs.com/image/albums/2016/9/18/2016091859501.jpg
     * commentId : 132
     * autor : Blus
     * content : 感觉高大上的样子 而且价格也不贵哈 试试
     */

    private String datetime;
    private String photo;
    private String commentId;
    private String autor;
    private String content;

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

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
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

    @Override
    public String toString() {
        return "CommentBean{" +
                "datetime='" + datetime + '\'' +
                ", photo='" + photo + '\'' +
                ", commentId='" + commentId + '\'' +
                ", autor='" + autor + '\'' +
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
        dest.writeString(this.photo);
        dest.writeString(this.commentId);
        dest.writeString(this.autor);
        dest.writeString(this.content);
    }

    public CommentBean() {
    }

    protected CommentBean(Parcel in) {
        this.datetime = in.readString();
        this.photo = in.readString();
        this.commentId = in.readString();
        this.autor = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<CommentBean> CREATOR = new Parcelable.Creator<CommentBean>() {
        @Override
        public CommentBean createFromParcel(Parcel source) {
            return new CommentBean(source);
        }

        @Override
        public CommentBean[] newArray(int size) {
            return new CommentBean[size];
        }
    };
}
