package com.android.lala.article.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/9/8.
 */
public class ArticleCommentBean implements Parcelable {

    /**
     * datetime : 55天前
     * articleId : 370
     * photo : http://lelelala.net/
     * autor : mix~21
     * content : 对于这个表情包，我觉得是属于一半很不错，一半只想问“什么鬼？”
     */

    private String datetime;
    private int articleId;
    private String photo;
    private String autor;
    private String content;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
        return "ArticleCommentBean{" +
                "datetime='" + datetime + '\'' +
                ", articleId=" + articleId +
                ", photo='" + photo + '\'' +
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
        dest.writeInt(this.articleId);
        dest.writeString(this.photo);
        dest.writeString(this.autor);
        dest.writeString(this.content);
    }

    public ArticleCommentBean() {
    }

    protected ArticleCommentBean(Parcel in) {
        this.datetime = in.readString();
        this.articleId = in.readInt();
        this.photo = in.readString();
        this.autor = in.readString();
        this.content = in.readString();
    }

    public static final Parcelable.Creator<ArticleCommentBean> CREATOR = new Parcelable.Creator<ArticleCommentBean>() {
        @Override
        public ArticleCommentBean createFromParcel(Parcel source) {
            return new ArticleCommentBean(source);
        }

        @Override
        public ArticleCommentBean[] newArray(int size) {
            return new ArticleCommentBean[size];
        }
    };
}
